package com.edgedo.sys.service;
		
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpCarInfo;
import com.edgedo.sys.entity.ErpCarSimChangeRec;
import com.edgedo.sys.entity.ErpSim;
import com.edgedo.sys.mapper.ErpCarInfoMapper;
import com.edgedo.sys.mapper.ErpCarSimChangeRecMapper;
import com.edgedo.sys.mapper.ErpSimMapper;
import com.edgedo.sys.mapper.ErpSupplierMapper;
import com.edgedo.sys.queryvo.ErpCarSimChangeRecQuery;
import com.edgedo.sys.queryvo.ErpCarSimChangeRecView;
import com.edgedo.sys.queryvo.ErpSupplierView;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpCarSimChangeRecService {
	
	
	@Autowired
	private ErpCarSimChangeRecMapper mapper;

	@Autowired
	private ErpCarInfoService erpCarInfoService;

	@Autowired
	private ErpSimService erpSimService;

	@Autowired
	private ErpCarInfoMapper erpCarInfoMapper;

	@Autowired
	private ErpSupplierMapper erpSupplierMapper;

	@Autowired
	private ErpSimMapper erpSimMapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpCarSimChangeRecView> listPage(ErpCarSimChangeRecQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpCarSimChangeRec voObj) {
		voObj.setId(Guid.guid());
		voObj.setChangeSimTime(new Date());//换卡时间
		String sim = voObj.getNewSimOperator();
		String[] simArr = sim.split(",");
		voObj.setNewSimOperator(simArr[1]);
		mapper.insert(voObj);
		return "";
	}

	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertNew(ErpCarSimChangeRec voObj) {
		voObj.setId(Guid.guid());
		voObj.setChangeSimTime(new Date());//换卡时间
		voObj.setCreateTime(new Date());
		voObj.setDataState("1");
		mapper.insert(voObj);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpCarSimChangeRec voObj) {
		//判断新卡是否存在
		/*String errInfo = erpCarInfoService.selectByErpSimBysim(voObj.getNewSimNum());//新卡号
		if(errInfo.equals("没有SIM卡，请先添加SIM卡!")){
			return "不存在新的SIM卡,请先添加SIM卡!";
		}else if(errInfo.equals("该sim卡已使用,请更换新卡号!")){
			return errInfo;
		}else {*/
			voObj.setChangeSimTime(new Date());//换卡时间
			//根据车牌号更新新sim卡号
			mapper.updateBySimNum(voObj.getNewSimNum(),voObj.getCarPlateNum());
			//更新车辆信息的sim卡号
			ErpCarInfo erpCarInfo = mapper.selectByCarNum(voObj.getCarPlateNum());
			//更新新sim卡的信息
			erpSimService.updateByErpSimInfo(erpCarInfo);

			//注销原卡号
			String info = "3";
			erpSimService.updateBySimState(info,voObj.getOrgSimNum());
			//新卡使用中
			String info2 = "1";
			erpSimService.updateBySimState(info2,voObj.getNewSimNum());
			//更新换卡表
			String sim = voObj.getNewSimOperator();
			String[] simArr = sim.split(",");
			voObj.setNewSimOperator(simArr[1]);
			mapper.updateById(voObj);
			return "";
		/*}*/
	}


	public String updateNew(ErpCarSimChangeRec voObj) {
		//判断新卡是否存在  1440298864863
		String errInfo = erpCarInfoService.selectByErpSimBysim(voObj.getNewSimNum());//新卡号
		if(errInfo.equals("没有SIM卡，请先添加SIM卡!")){
				ErpSim erpSim1 = new ErpSim();
				erpSim1.setSimNum(voObj.getNewSimNum());
				erpSim1.setSimState("2");
				erpSimService.insert(erpSim1);
		}
		if(errInfo.equals("该sim卡已使用,请更换新卡号!")){
			return errInfo;
		}else {
			voObj.setId(Guid.guid());
			voObj.setChangeSimTime(new Date());//换卡时间
			voObj.setCreateTime(new Date());//创建时间
			//根据车牌号更新新sim卡号
			//String[] str = voObj.getNewSimOperator().split(",");
			Map<String,String> map = new HashMap<>();
			map.put("newSimNum",voObj.getNewSimNum());
			map.put("id",voObj.getOwnerCarInfoId());
			map.put("newSimOperator",voObj.getNewSimOperator());
			erpCarInfoService.updateBySimNum(map);
			//查询
			ErpCarInfo erpCarInfo = mapper.selectByCarNum(voObj.getOwnerCarInfoId());
			//更新新sim卡的信息
			erpSimService.updateByErpSimInfo(erpCarInfo);

			//注销原卡号
			String info = "3";
			erpSimService.updateBySimState(info,voObj.getOrgSimNum());
			//更新换卡表
			voObj.setDataState("1");
			mapper.insert(voObj);
			return "";
		}
	}

	public void insertOrUpdate(ErpCarSimChangeRec erpCarSimChangeRec) {
		//判断车牌号是否已经关联
		String carPlateNum = erpCarSimChangeRec.getCarPlateNum();
		erpSimService.updateByCarPlateNum(carPlateNum);
		//新卡号
		String newSimNum = erpCarSimChangeRec.getNewSimNum();
		//判断新卡号是否存在，没有的话自动创建
		ErpSim sim = erpSimService.selectBySimCode(newSimNum);
		if(sim==null){
			ErpSim erpSim = new ErpSim();
			erpSim.setSimNum(newSimNum);
			erpSim.setSupplierName(erpCarSimChangeRec.getNewSimOperator());
			erpSim.setCarPlateNum(erpCarSimChangeRec.getCarPlateNum());
			erpSim.setCarFrameNum(erpCarSimChangeRec.getCarFrameNum());
			erpSim.setCreateUserId(erpCarSimChangeRec.getCreateUserId());
			erpSim.setCreateUserName(erpCarSimChangeRec.getCreateUserName());
			erpSim.setSimState("2");
			erpSimService.insertNew(erpSim);
		}else {
			sim.setSupplierName(erpCarSimChangeRec.getNewSimOperator());
			sim.setCarPlateNum(erpCarSimChangeRec.getCarPlateNum());
			sim.setCarFrameNum(erpCarSimChangeRec.getCarFrameNum());
			sim.setSimState("2");
		}
		Map<String,String> map = new HashMap<>();
		map.put("newSimNum",newSimNum);
		map.put("id",erpCarSimChangeRec.getOwnerCarInfoId());
		map.put("newSimOperator",erpCarSimChangeRec.getNewSimOperator());
		erpCarInfoService.updateBySimNum(map);
		insertNew(erpCarSimChangeRec);
	}
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpCarSimChangeRec voObj) {
		mapper.updateAllColumnById(voObj);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return mapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		return mapper.deleteByIdAll(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpCarSimChangeRec loadById(String id) {
		return mapper.selectById(id);
	}


	/**
	 * 删除单个
	 * @param
	 */
    public void deleteByIdsOne(String ids) {
    	mapper.deleteByIdsOne(ids);
    }

	/**
	 * 查询所有SIM类型的供应商
	 * @param
	 * @return
	 */
    public List<ErpSim> selectNewSimOperator() {
		return erpSimMapper.selectNewSimOperator();
    }


	public List<ErpCarSimChangeRecView> listAll(ErpCarSimChangeRecQuery query) {
		return  mapper.listByObj(query);
	}
}

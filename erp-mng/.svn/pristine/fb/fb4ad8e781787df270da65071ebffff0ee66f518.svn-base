package com.edgedo.sys.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpSim;
import com.edgedo.sys.entity.ErpSimXufeiRec;
import com.edgedo.sys.mapper.ErpSimMapper;
import com.edgedo.sys.mapper.ErpSimXufeiRecMapper;
import com.edgedo.sys.queryvo.ErpSimXufeiRecQuery;
import com.edgedo.sys.queryvo.ErpSimXufeiRecView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpSimXufeiRecService {
	
	
	@Autowired
	private ErpSimXufeiRecMapper mapper;

	@Autowired
	private ErpCarInfoService erpCarInfoService;

	@Autowired
	private ErpSimMapper erpSimMapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpSimXufeiRecView> listPage(ErpSimXufeiRecQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpSimXufeiRec voObj) {

		//判断用户输入的sim卡是否存在
		String err = erpCarInfoService.selectByErpSimBysim(voObj.getSimNum());
		if(err.equals("没有SIM卡，请先添加SIM卡!")){
			return "SIM卡不存在,请查看是否填写正确!";
		}else if(err.equals("该sim卡已使用,请更换卡号!")){
			voObj.setId(Guid.guid());//主键
			voObj.setCreateTime(new Date());//创建时间
			//根据sim卡号查询sim记录
			ErpSim erpSim = erpSimMapper.selectBySimCode(voObj.getSimNum());
			voObj.setOwnerSimId(erpSim.getId());//所属sim卡的id
			voObj.setXufeiTime(new Date());//续费时间
			voObj.setBeforeXufeiEndTime(erpSim.getXufeiTime());//续费前的到期时间
			voObj.setEndTime(erpSim.getEndTime());//到期时间
			voObj.setDataState("1");
			mapper.insert(voObj);
			return "";
		}else{
			return "该SIM卡未被使用,请先去激活该SIM卡号";
		}

	}


	public String insertNew(ErpSimXufeiRec voObj) {
		voObj.setId(Guid.guid());//主键
		voObj.setCreateTime(new Date());//创建时间
		//根据sim卡号查询sim记录
		ErpSim erpSim = erpSimMapper.selectBySimCode(voObj.getSimNum());
		voObj.setOwnerSimId(erpSim.getId());//所属sim卡的id
		voObj.setXufeiTime(new Date());//续费时间
		voObj.setBeforeXufeiEndTime(erpSim.getXufeiTime());//续费前的到期时间
		voObj.setEndTime(erpSim.getEndTime());//到期时间
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
	public String update(ErpSimXufeiRec voObj) {
		voObj.setXufeiTime(new Date());//续费时间
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpSimXufeiRec voObj) {
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

		return mapper.deleteByIdsAll(ids);
	}

	/**
	 * 删除单个记录
	 *
	 * @param
	 */
	public void deleteByIdsOne(String ids) {
		mapper.deleteByIdsOne(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpSimXufeiRec loadById(String id) {
		return mapper.selectById(id);
	}


	/**
	 * 跳转到续费中 携带参数 插入数据库
	 * @param erpSim
	 * @return
	 */
    public ErpSimXufeiRecView insertByErpSimInfo(ErpSim erpSim) {
		ErpSimXufeiRecView erpSimXufeiRecView = new ErpSimXufeiRecView();
		erpSimXufeiRecView.setId(Guid.guid());//主键
		erpSimXufeiRecView.setCreateTime(new Date());//创建时间
		erpSimXufeiRecView.setOwnerSimId(erpSim.getId());//所属sim卡的id
		erpSimXufeiRecView.setSimNum(erpSim.getSimNum());//sim卡号
		erpSimXufeiRecView.setEndTime(erpSim.getEndTime());//到期时间
		erpSimXufeiRecView.setDataState("1");
		erpSimXufeiRecView.setBeforeXufeiEndTime(erpSim.getXufeiTime());//续费前的到期时间=需要缴费时间
		mapper.insert(erpSimXufeiRecView);
		return erpSimXufeiRecView;
    }
}

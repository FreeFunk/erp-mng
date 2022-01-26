package com.edgedo.sys.service;
		
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpCarInfo;
import com.edgedo.sys.entity.ErpCarSimpleInfo;
import com.edgedo.sys.entity.ErpCarXufeiRec;
import com.edgedo.sys.mapper.ErpCarInfoMapper;
import com.edgedo.sys.mapper.ErpCarXufeiRecMapper;
import com.edgedo.sys.mapper.ErpChannelAgentMapper;
import com.edgedo.sys.mapper.ErpSupplierMapper;
import com.edgedo.sys.queryvo.ErpCarInfoView;
import com.edgedo.sys.queryvo.ErpCarXufeiRecQuery;
import com.edgedo.sys.queryvo.ErpCarXufeiRecView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpCarXufeiRecService {
	
	
	@Autowired
	private ErpCarXufeiRecMapper mapper;

	@Autowired
	private ErpChannelAgentMapper erpChannelAgentMapper;

	@Autowired
	private ErpSupplierMapper erpSupplierMapper;

	@Autowired
	private ErpCarInfoMapper erpCarInfoMapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpCarXufeiRecView> listPage(ErpCarXufeiRecQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpCarXufeiRec voObj) {
		//判断是否有该车
		ErpCarInfo erpCarInfo = erpCarInfoMapper.selectByCarNum(voObj.getCarPlateNum());
		if(erpCarInfo==null){
			return "不存在该车，检查车牌号是否输入正确!";
		}else {
			voObj.setId(Guid.guid());
			//查询渠道代理名
			String channelAgentName = erpChannelAgentMapper.selectByChannelAgentId(voObj.getChannelAgentId());
			//查询供应商名
			String supplierName = erpSupplierMapper.selectBySupplierId(voObj.getSupplierId());
			voObj.setChannelAgentName(channelAgentName);
			voObj.setSupplierName(supplierName);
			//续费时间
			voObj.setXufeiTime(new Date());
			//服务结束时间
			voObj.setServiceEndTime(new Date());
			//更新车辆的上一次的缴费时间

			erpCarInfoMapper.updtaByXuFeiTime(new Date(),voObj.getCarPlateNum());
			voObj.setDataState("1");
			mapper.insert(voObj);
			return "";
		}
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpCarXufeiRec voObj) {
		/*//缴费时间
		voObj.setXufeiTime(new Date());
		//服务末时间
		voObj.setServiceEndTime(new Date());*/
		//查询渠道代理名
		String channelAgentName = erpChannelAgentMapper.selectByChannelAgentId(voObj.getChannelAgentId());
		//查询供应商名
		//String supplierName = erpSupplierMapper.selectBySupplierId(voObj.getSupplierId());
		voObj.setChannelAgentName(channelAgentName);
		//voObj.setSupplierName(supplierName);
		//更新车辆的上一次的缴费时间
		erpCarInfoMapper.updateByGetMoneyNum(voObj);
		erpCarInfoMapper.updtaByXuFeiTime(voObj.getXufeiTime(),voObj.getOwnerCarInfoId());
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpCarXufeiRec voObj) {
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
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpCarXufeiRec loadById(String id) {
		return mapper.selectById(id);
	}

	/**
	 * 删除单个
	 * @param
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByIdsOne(String ids) {
		mapper.deleteByIdsOne(ids);
	}

	/**
	 * 根据车辆点击进行续费跳转 插入
	 * @param erpCarInfo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public ErpCarXufeiRecView insertByErpCarInfo(ErpCarInfo erpCarInfo) {
		//携带车牌号，车颜色，车架号，车对应的id插入到车辆换卡记录
		ErpCarXufeiRecView erpCarXufeiRec = new ErpCarXufeiRecView();
		erpCarXufeiRec.setId(Guid.guid());//主键
		erpCarXufeiRec.setCarFrameNum(erpCarInfo.getCarFrameNum());//车架号
		erpCarXufeiRec.setCarPlateColor(erpCarInfo.getCarPlateColor());//车颜色
		erpCarXufeiRec.setOwnerCarInfoId(erpCarInfo.getId());//车id
		erpCarXufeiRec.setCarPlateNum(erpCarInfo.getCarPlateNum());//车牌号
		erpCarXufeiRec.setDataState("1");//数据状态
		erpCarXufeiRec.setCreateTime(new Date());//创建时间
		erpCarXufeiRec.setChannelAgentId(erpCarInfo.getChannelAgentId());//渠道代理id
		erpCarXufeiRec.setChannelAgentName(erpCarInfo.getChannelAgentName());//渠道代理姓名
		erpCarXufeiRec.setSupplierId(erpCarInfo.getSupplierId());//供应商id
		erpCarXufeiRec.setSupplierName(erpCarInfo.getSupplierName());//供应商名
		erpCarXufeiRec.setDataState("1");
		//插入数据库
		mapper.insert(erpCarXufeiRec);
		return erpCarXufeiRec;
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateNew(ErpCarXufeiRec erpCarXufeiRec) {
		//查询渠道代理名
		String channelAgentIdAndName = erpCarXufeiRec.getChannelAgentId();
		String[] channelAgent = channelAgentIdAndName.split(",");
		erpCarXufeiRec.setChannelAgentId(channelAgent[0]);
		erpCarXufeiRec.setChannelAgentName(channelAgent[1]);
//		erpCarXufeiRec.setChannelAgentName(channelAgentName);
		erpCarXufeiRec.setId(Guid.guid());
		//续费时间
//		erpCarXufeiRec.setXufeiTime(new Date());
		
		//更新车辆的上一次的缴费时间

		erpCarInfoMapper.updateByGetMoneyNum(erpCarXufeiRec);
		erpCarInfoMapper.updtaByXuFeiTime(erpCarXufeiRec.getXufeiTime(),erpCarXufeiRec.getOwnerCarInfoId());
		erpCarXufeiRec.setDataState("1");
		erpCarXufeiRec.setCreateTime(new Date());
		mapper.insert(erpCarXufeiRec);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public List<ErpCarXufeiRec> selectAllCarInfo(Map<String, Object> mapQuery) {
		return mapper.selectAllCarInfo(mapQuery);
	}
}

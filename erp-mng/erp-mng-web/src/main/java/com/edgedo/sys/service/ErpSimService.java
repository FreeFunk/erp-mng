package com.edgedo.sys.service;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpCarInfo;
import com.edgedo.sys.entity.ErpSim;
import com.edgedo.sys.entity.ErpSupplier;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.mapper.ErpSimMapper;
import com.edgedo.sys.mapper.ErpSupplierMapper;
import com.edgedo.sys.queryvo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpSimService {


	@Autowired
	private ErpSimMapper mapper;

	@Autowired
	private ErpSupplierMapper erpSupplierMapper;

	@Autowired
	private ErpCarInfoService erpCarInfoService;

	@Autowired
	private ErpSupplierService erpSupplierService;


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public List<ErpSimView> listPage(ErpSimQuery query) {
		List list = mapper.listPage(query);
		//判断sim卡每天的基本状态是否已经可以自动激活
		for (Object o : list) {
			ErpSimView erpSimNoChange = (ErpSimView) o;
			if (erpSimNoChange.getCarPlateNum() == null && erpSimNoChange.getCarFrameNum() == null && erpSimNoChange.getSimState()!=null && erpSimNoChange.getSimState().equals("0")) {//判断没有人在沉默期间使用这张卡
				//当前时间
				Date currTime = new Date();
				//理论到期时间
				Date dateActiveTime = erpSimNoChange.getActiveTime();
				if (dateActiveTime.getTime() <= currTime.getTime()) {
					//大于等于要更改状态
					erpSimNoChange.setSimState("1");//自动激活
					mapper.updateBySimState(erpSimNoChange);//更改数据库
				} else {
					//否则不变
					continue;
				}
			}
		}
		query.setList(list);
		return list;
	}

	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String insert(ErpSim voObj) {
		//判断是否已经存在的sim卡
		String err = erpCarInfoService.selectByErpSimBysim(voObj.getSupplierName());
		if (err.equals("没有SIM卡，请先添加SIM卡!")) {
			voObj.setId(Guid.guid());
			voObj.setCreateTime(new Date());
			String supplierNames = voObj.getSupplierName();
			erpCarInfoService.selectByCarNum(voObj.getSimNum(),supplierNames);
			//拿到开卡时间  沉默月  到期时间 （开卡时间+沉默月）=理论时间   到期=到期时间-理论时间 初次使用创建车辆管理
			//卡占用人id  数据状态  金额  付费类型(月 年)  供应商id 套餐名 卡的套餐描述
			//计算机理论时间
			Calendar c = Calendar.getInstance();
			if(voObj.getSimStartTime()!=null && voObj.getSimReticentNum()!=null){
				c.setTime(voObj.getSimStartTime());//开卡时间算起
				c.add(Calendar.MONTH, voObj.getSimReticentNum());//沉默月 往后推月
				//创建理论时间
				Date activeNewTime = c.getTime();
				voObj.setActiveTime(activeNewTime);

				//根据Sysuser表查询名字 类型
				String userId = voObj.getSimUserId();//根据卡占用人id 查询
				if(userId.equals("") || userId==null){
					voObj.setSimUserName("");//卡占用人名
					voObj.setUserType("");//卡占用类型
				}else {
					voObj.setSimUserId(userId);//id
					SysUser su = mapper.selectByIdSysUser(userId);
					voObj.setSimUserName(su.getUserName());//卡占用人名
					voObj.setUserType(su.getDefaultRoleName());//卡占用类型
				}
				Date currTime = new Date();//当前创建时间
				voObj.setCreateTime(currTime);
				//判断是否在沉默月
				if (currTime.getTime() >= voObj.getSimStartTime().getTime()
						&& currTime.getTime() <= activeNewTime.getTime()) {
					voObj.setSimState("0");//未激活
				} else if (!(currTime.getTime() < voObj.getSimStartTime().getTime()) && currTime.getTime() > activeNewTime.getTime()) {
					voObj.setSimState("1");//自动激活
				}

				//根据供应商名 查询供应商ID进行关联
				String supplierName= erpSupplierMapper.selectSupplierName(voObj.getSupplierId());
				voObj.setSupplierName(supplierName);
			/*if(voObj.getSupplierId()==null){
				ErpSupplier erpSupplier = new ErpSupplier();
				erpSupplier.setId(Guid.guid());
				erpSupplier.setSupplierName(voObj.getSupplierName());
				erpSupplier.setCreateTime(new Date());
				erpSupplier.setDataState("1");
				erpSupplier.setSupplierType("SIM");
				erpSupplierService.insert(erpSupplier);
				voObj.setSupplierId(erpSupplier.getId());
			}else{
				voObj.setSupplierId(supplierId);
			}*/
				voObj.setDataState("1");//状态
				mapper.insert(voObj);
				return "";
			}
				voObj.setDataState("1");//状态
				mapper.insert(voObj);
				return "";
		}else{
			return "加入的SIM卡已存在，请重新输入新的卡号！";
		}


		/*voObj.setId(Guid.guid());
		voObj.setCreateTime(new Date());
		voObj.setDataState("1");
		mapper.insert(voObj);
		return "";*/
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String insertNew(ErpSim voObj) {
		//判断是否已经存在的sim卡
		voObj.setId(Guid.guid());
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String update(ErpSim voObj) {
		mapper.updateById(voObj);
		return "";
	}


	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String updateHtml(ErpSim voObj) {
		//卡占用人  占用id
		String zhanYong = voObj.getSimUserId();
		if(zhanYong.equals("")){
			voObj.setSimUserId("");//id
			voObj.setSimUserName("");//名字
			voObj.setUserType("");//卡占用类型
		}else {
			String[] zhanYongArr = zhanYong.split(",");
			voObj.setSimUserId(zhanYongArr[0]);//id
			voObj.setSimUserName(zhanYongArr[1]);//名字
			SysUser su = mapper.selectByIdSysUser(zhanYongArr[0]);
			voObj.setUserType(su.getDefaultRoleName());//卡占用类型
		}
		//供应商
		String sup = voObj.getSupplierId();
		if(sup.equals("")){
			voObj.setSupplierName("");
			voObj.setSupplierId("");
		}else {
			String[] supArr = sup.split(",");
			voObj.setSupplierName(supArr[1]);
			voObj.setSupplierId(supArr[0]);
		}
		mapper.updateExcel(voObj);
		return "";
	}



	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String updateAll(ErpSim voObj) {
		mapper.updateAllColumnById(voObj);
		return "";
	}


	/**
	 * 单个删除
	 *
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int delete(String id) {

		return mapper.deleteById(id);
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int deleteByIds(List<String> ids) {

		return mapper.deleteByIdsAll(ids);
	}


	/**
	 * 加载单个
	 *
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
	public ErpSim loadById(String id) {
		return mapper.selectById(id);
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
	 * 查询所有占用人type
	 *
	 * @return
	 */
	public List<SysUserView> selectAllType(SysUserQuery query) {
		return mapper.listBySysUser(query);
	}

	/**
	 * 换卡后注销原卡号
	 *
	 * @param info
	 * @param orgSimNum
	 */
	public void updateBySimState(String info, String orgSimNum) {
		mapper.updateBySimStateChange(info, orgSimNum);
	}

	/**
	 * 更新sim卡信息
	 *
	 * @param voObj
	 */

	public void updateByErpSimInfo(ErpCarInfo voObj) {

		//查询跟据sim卡号查询一条ErpSim记录
		ErpSim erpSim = mapper.selectBySimCode(voObj.getSimCode());
		//车牌号
		erpSim.setCarPlateNum(voObj.getCarPlateNum());
		//车架号
		erpSim.setCarFrameNum(voObj.getCarFrameNum());
		//业户人员
		erpSim.setYehuName(voObj.getYehuName());
		erpSim.setSupplierId(voObj.getSupplierId());
		erpSim.setSupplierName(voObj.getSupplierName());
		//更新初次使用时间
		erpSim.setFirstUseTime(new Date());
		//计算缴费时间
		Calendar c = Calendar.getInstance();
		if(erpSim.getPayType()!= null){
			c.setTime(erpSim.getFirstUseTime());//初次使用时间算起
			if(erpSim.getPayType().equals("0")){
				c.add(Calendar.MONTH, 1);//推一个月
				Date xuFeiNewTime = c.getTime();
				erpSim.setXufeiTime(xuFeiNewTime);
			}else {
				c.add(Calendar.YEAR, 1);//推一年
				Date xuFeiNewTime = c.getTime();
				erpSim.setXufeiTime(xuFeiNewTime);
			}
			//在计算到期时间=到期时间-开卡时间  当前时间+到期时间  不在 自己输入的到期时间 |-----|--------|
			int endDate = (int)((erpSim.getEndTime().getTime()-erpSim.getSimStartTime().getTime())/(3600*1000*24));
			//判断是否在沉默月
			if (erpSim.getSimState().equals("0")) {
				//如果再沉默月，从第一次时间算起
				c.add(Calendar.DAY_OF_MONTH,endDate);//更新的到期时间
				Date endNewTime = c.getTime();
				erpSim.setEndTime(endNewTime);
			} else if (erpSim.getSimState().equals("1")) {
				//如果不再沉默月，从理论时间算起
				Calendar activeTimeCalendar = Calendar.getInstance();
				activeTimeCalendar.setTime(erpSim.getActiveTime());//从理论时间算起
				activeTimeCalendar.add(Calendar.DAY_OF_MONTH,endDate);//更新的到期时间
				Date endNewTime = activeTimeCalendar.getTime();
				erpSim.setEndTime(endNewTime);
			}
			erpSim.setSimState("2");//使用中
		}else{
			erpSim.setSimState("2");
		}
		mapper.updateBySimCode(erpSim);
	}

	/**
	 * 根据sim的id查出一条记录
	 */
	public ErpSim selectByIds(String ids) {
		return mapper.selectByIds(ids);
	}

	public void updateZhuXiao(ErpSim erpSim) {
		mapper.updateZhuXiao(erpSim);
	}

	/**
	 *将excle表数据插入到数据库中
	 * @param attendanceList
	 * @return
	 */
	public void updateSimKa(List<Map<String, String>> attendanceList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//List<ErpSim> erpSimList = new ArrayList<>();
		for(Map<String,String> simKaMap :attendanceList){
			ErpSim erpSim = new ErpSim();
			String simNum = simKaMap.get("simNum");
			if(simNum==null){
				continue;
			}
			erpSim.setSimNum(simNum);
			String yehuName = simKaMap.get("yehuName");
			erpSim.setYehuName(yehuName);
			String carPlateNum = simKaMap.get("carPlateNum");
			erpSim.setCarPlateNum(carPlateNum);
			String supplierName = simKaMap.get("supplierName");
			erpSim.setSupplierName(supplierName);
			String setMealName = simKaMap.get("setMealName");
			erpSim.setSetMealName(setMealName);
			String simState = simKaMap.get("simState");
			if(simState!=null){
				if(simState.equals("未激活")){
					erpSim.setSimState("0");
				}else if(simState.equals("自动激活")){
					erpSim.setSimState("1");
				}else if(simState.equals("使用中")){
					erpSim.setSimState("2");
				}else if(simState.equals("注销")){
					erpSim.setSimState("3");
				}else if(simState.equals("在线")){
					erpSim.setSimState("4");
				}else{
					erpSim.setSimState("");
				}
			}else {
				erpSim.setSimState("");
			}
			/*String outReason = simKaMap.get("outReason");
			erpSim.setOutReason(outReason);*/
			String simReticentNum = simKaMap.get("simReticentNum");
			if(simReticentNum!=null && !simReticentNum.equals("")){
				Integer newSimReticentNum = Integer.valueOf(simReticentNum);
				erpSim.setSimReticentNum(newSimReticentNum);
			}
			String simTaocanDesc = simKaMap.get("simTaocanDesc");
			erpSim.setSimTaocanDesc(simTaocanDesc);
			String payType = simKaMap.get("payType");
			if(payType!=null && !payType.equals("")){
				if(payType.equals("年付")){
					erpSim.setPayType("1");
				}else if(payType.equals("月付")){
					erpSim.setPayType("0");
				}
			}
			String payMoney = simKaMap.get("payMoney");
			if(payMoney!=null && !payMoney.equals("")){
				Double douPayMoney = Double.valueOf(payMoney);
				BigDecimal newPayMoney = BigDecimal.valueOf(douPayMoney);
				erpSim.setPayMoney(newPayMoney);
			}
			String simUserName = simKaMap.get("simUserName");
			erpSim.setSimUserName(simUserName);
			try {
				String endTime = simKaMap.get("endTime");
				String simStartTime = simKaMap.get("simStartTime");
				String xufeiTime = simKaMap.get("xufeiTime");
				if(endTime.matches(".*/.*/.*") && endTime!=null && !endTime.equals("")){
					erpSim.setEndTime(sdf.parse(endTime));
				}
				if(simStartTime.matches(".*/.*/.*") && simStartTime!=null && !simStartTime.equals("")){
					erpSim.setSimStartTime(sdf.parse(simStartTime));
				}
				if(xufeiTime.matches(".*/.*/.*") && xufeiTime!=null && !xufeiTime.equals("")){
					erpSim.setXufeiTime(sdf.parse(xufeiTime));
				}
				/*String firstUseTime = simKaMap.get("firstUseTime");
				erpSim.setFirstUseTime(sdf.parse(firstUseTime));*/
				/*String activeTime = simKaMap.get("activeTime");
				erpSim.setActiveTime(sdf.parse(activeTime));*/
			} catch (ParseException e) {
				e.printStackTrace();
			}

			System.out.println("已插入："+erpSim);
			insertExcel(erpSim);
		}
	}

	public void insertExcel(ErpSim erpSim) {
		erpSim.setId(Guid.guid());
		erpSim.setCreateTime(new Date());
		erpSim.setDataState("1");
		mapper.insert(erpSim);
	}


	public List<ErpSim> selectAllSim() {
		return mapper.selectAllSim();
	}

	public List<ErpSim> selectSelectAllSim(List<String> idList) {
		return mapper.selectSelectAllSim(idList);
	}


	public String isRepeatSimNum(List<String> simNumList) {
		String errMsg = "";
		for(String s:simNumList){
			ErpSim erpSim = mapper.selectBySimCode(s);
			if(erpSim!=null){
				if(errMsg.equals("")){
					errMsg = "卡号："+s;
				}else {
					errMsg = errMsg+"，卡号："+s;
				}
			}
		}
		return errMsg;
	}
	public void updateAndinsert(List<Map<String,String>> simKaList) {
		for(Map<String,String> simKaMap :simKaList){
			String simNum = simKaMap.get("simNum");
			ErpSim erpSim = mapper.selectBySimCode(simNum);
			if(erpSim!=null){//存在，更新
				oldUpdate(simKaMap,erpSim.getId());
			}else {//不存在 插入
				newInsert(simKaMap);
			}

		}
	}


	public void newInsert(Map<String,String> simKaMap){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		ErpSim erpSim = new ErpSim();
		String simNum = simKaMap.get("simNum");
		erpSim.setSimNum(simNum);
		String yehuName = simKaMap.get("yehuName");
		erpSim.setYehuName(yehuName);
		String carPlateNum = simKaMap.get("carPlateNum");
		erpSim.setCarPlateNum(carPlateNum);
		String supplierName = simKaMap.get("supplierName");
		erpSim.setSupplierName(supplierName);
		String setMealName = simKaMap.get("setMealName");
		erpSim.setSetMealName(setMealName);
		String simState = simKaMap.get("simState");
		if(simState!=null){
			if(simState.equals("未激活")){
				erpSim.setSimState("0");
			}else if(simState.equals("自动激活")){
				erpSim.setSimState("1");
			}else if(simState.equals("使用中")){
				erpSim.setSimState("2");
			}else if(simState.equals("注销")){
				erpSim.setSimState("3");
			}else if(simState.equals("在线")){
				erpSim.setSimState("4");
			}else{
				erpSim.setSimState("");
			}
		}else {
			erpSim.setSimState("");
		}
			/*String outReason = simKaMap.get("outReason");
			erpSim.setOutReason(outReason);*/
		String simReticentNum = simKaMap.get("simReticentNum");
		if(simReticentNum!=null && !simReticentNum.equals("")){
			Integer newSimReticentNum = Integer.valueOf(simReticentNum);
			erpSim.setSimReticentNum(newSimReticentNum);
		}
		String simTaocanDesc = simKaMap.get("simTaocanDesc");
		erpSim.setSimTaocanDesc(simTaocanDesc);
		String payType = simKaMap.get("payType");
		if(payType!=null && !payType.equals("")){
			if(payType.equals("年付")){
				erpSim.setPayType("1");
			}else if(payType.equals("月付")){
				erpSim.setPayType("0");
			}
		}
		String payMoney = simKaMap.get("payMoney");
		if(payMoney!=null && !payMoney.equals("")){
			Double douPayMoney = Double.valueOf(payMoney);
			BigDecimal newPayMoney = BigDecimal.valueOf(douPayMoney);
			erpSim.setPayMoney(newPayMoney);
		}
		String simUserName = simKaMap.get("simUserName");
		erpSim.setSimUserName(simUserName);
		try {
			String endTime = simKaMap.get("endTime");
			String simStartTime = simKaMap.get("simStartTime");
			String xufeiTime = simKaMap.get("xufeiTime");
			if(endTime.matches(".*/.*/.*") && endTime!=null && !endTime.equals("")){
				erpSim.setEndTime(sdf.parse(endTime));
			}
			if(simStartTime.matches(".*/.*/.*") && simStartTime!=null && !simStartTime.equals("")){
				erpSim.setSimStartTime(sdf.parse(simStartTime));
			}
			if(xufeiTime.matches(".*/.*/.*") && xufeiTime!=null && !xufeiTime.equals("")){
				erpSim.setXufeiTime(sdf.parse(xufeiTime));
			}
				/*String firstUseTime = simKaMap.get("firstUseTime");
				erpSim.setFirstUseTime(sdf.parse(firstUseTime));*/
				/*String activeTime = simKaMap.get("activeTime");
				erpSim.setActiveTime(sdf.parse(activeTime));*/
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println("已插入："+erpSim);
		insertExcel(erpSim);
	}

	public void oldUpdate(Map<String,String> simKaMap,String simId){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		ErpSim erpSim = new ErpSim();
		String simNum = simKaMap.get("simNum");
		erpSim.setSimNum(simNum);
		String yehuName = simKaMap.get("yehuName");
		erpSim.setYehuName(yehuName);
		String carPlateNum = simKaMap.get("carPlateNum");
		erpSim.setCarPlateNum(carPlateNum);
		String supplierName = simKaMap.get("supplierName");
		erpSim.setSupplierName(supplierName);
		String setMealName = simKaMap.get("setMealName");
		erpSim.setSetMealName(setMealName);
		String simState = simKaMap.get("simState");
		if(simState!=null){
			if(simState.equals("未激活")){
				erpSim.setSimState("0");
			}else if(simState.equals("自动激活")){
				erpSim.setSimState("1");
			}else if(simState.equals("使用中")){
				erpSim.setSimState("2");
			}else if(simState.equals("注销")){
				erpSim.setSimState("3");
			}else if(simState.equals("在线")){
				erpSim.setSimState("4");
			}else{
				erpSim.setSimState("");
			}
		}else {
			erpSim.setSimState("");
		}
			/*String outReason = simKaMap.get("outReason");
			erpSim.setOutReason(outReason);*/
		String simReticentNum = simKaMap.get("simReticentNum");
		if(simReticentNum!=null && !simReticentNum.equals("")){
			Integer newSimReticentNum = Integer.valueOf(simReticentNum);
			erpSim.setSimReticentNum(newSimReticentNum);
		}
		String simTaocanDesc = simKaMap.get("simTaocanDesc");
		erpSim.setSimTaocanDesc(simTaocanDesc);
		String payType = simKaMap.get("payType");
		if(payType!=null && !payType.equals("")){
			if(payType.equals("年付")){
				erpSim.setPayType("1");
			}else if(payType.equals("月付")){
				erpSim.setPayType("0");
			}
		}
		String payMoney = simKaMap.get("payMoney");
		if(payMoney!=null && !payMoney.equals("")){
			Double douPayMoney = Double.valueOf(payMoney);
			BigDecimal newPayMoney = BigDecimal.valueOf(douPayMoney);
			erpSim.setPayMoney(newPayMoney);
		}
		String simUserName = simKaMap.get("simUserName");
		erpSim.setSimUserName(simUserName);
		try {
			String endTime = simKaMap.get("endTime");
			String simStartTime = simKaMap.get("simStartTime");
			String xufeiTime = simKaMap.get("xufeiTime");
			if(endTime.matches(".*/.*/.*") && endTime!=null && !endTime.equals("")){
				erpSim.setEndTime(sdf.parse(endTime));
			}
			if(simStartTime.matches(".*/.*/.*") && simStartTime!=null && !simStartTime.equals("")){
				erpSim.setSimStartTime(sdf.parse(simStartTime));
			}
			if(xufeiTime.matches(".*/.*/.*") && xufeiTime!=null && !xufeiTime.equals("")){
				erpSim.setXufeiTime(sdf.parse(xufeiTime));
			}
				/*String firstUseTime = simKaMap.get("firstUseTime");
				erpSim.setFirstUseTime(sdf.parse(firstUseTime));*/
				/*String activeTime = simKaMap.get("activeTime");
				erpSim.setActiveTime(sdf.parse(activeTime));*/
		} catch (ParseException e) {
			e.printStackTrace();
		}
		erpSim.setId(simId);
		System.out.println("已更新："+erpSim);
		mapper.updateExcel(erpSim);
	}

	public int countByCarPlateNum(String carPlateNum) {
		return mapper.countByCarPlateNum(carPlateNum);
	}

	public void updateByCarPlateNum(String carPlateNum) {
		mapper.updateByCarPlateNum(carPlateNum);
	}

	public ErpSim selectByErpSimNum(String newSimNum) {
		return mapper.selectByErpNum(newSimNum);
	}

	public ErpSim selectBySimCode(String newSimNum) {
		return mapper.selectBySimCode(newSimNum);
	}
}

package com.edgedo.sys.controller;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.*;
import com.edgedo.sys.queryvo.*;
import com.edgedo.sys.service.ErpCarInfoService;
import com.edgedo.sys.service.ErpCarSimChangeRecService;
import com.edgedo.sys.service.ErpCarXufeiRecService;
import com.edgedo.sys.service.ErpOperatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Api(tags = "ErpCarInfo")
@Controller
@RequestMapping("/admin/erpCarInfo")
public class ErpCarInfoController extends BaseController{
	
	@Autowired
	private ErpCarInfoService service;

	@Autowired
	private ErpCarXufeiRecService erpCarXufeiRecService;

	@Autowired
	private ErpOperatorService erpOperatorService;

	@Autowired
	private ErpCarSimChangeRecService erpCarSimChangeRecService;

	//车牌号
	String carNum = "";

	//车架号
	String carFrame = "";

	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpCarInfo", notes = "分页查询ErpCarInfo")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpCarInfoQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpCarInfo", notes = "分页查询ErpCarInfo")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listDeletepage",method = RequestMethod.POST)
	public ModelAndView listDeletepage(@ModelAttribute ErpCarInfoQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("DELETE_TIME desc");
		}
		service.listDeletePage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * 动态查询车队名
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/selectCarTeam",method = RequestMethod.POST)
	public ModelAndView selectCarTeam(){
		ModelAndView modelAndView = new ModelAndView();
		List list = service.selectCarTeam();
		buildResponse(modelAndView,list);
		return modelAndView;
	}


	@RequestMapping(value = "/listEndTimepage",method = RequestMethod.POST)
	public ModelAndView listEndTimepage(@ModelAttribute ErpCarInfoQuery query){
		ModelAndView modelAndView = new ModelAndView();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE,30);
		Date end = calendar.getTime();
		Date first = new Date();
		query.getQueryObj().setSelectTime(first);
		query.getQueryObj().setNewEndTime(end);
		service.endTimelistPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	/**
	 * 展示渠道代理
	 */
	@RequestMapping(value = "/listAllChannelAgentNameList",method = RequestMethod.POST)
	public ModelAndView listAllChannelAgentNameList(){
		ModelAndView modelAndView = new ModelAndView();
		List list = service.selectChannelAgentName();
		buildResponse(modelAndView,list);
		return modelAndView;
	}


	/**
	 * 展示供应商名
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/listAllSupplier",method = RequestMethod.POST)
	public ModelAndView listAllSupplier(){
		ModelAndView modelAndView = new ModelAndView();
		List list = service.selectBySupplierName();
		buildResponse(modelAndView,list);
		return modelAndView;
	}


	/**
	 * 展示业务人员  安装人员 ---- 外勤
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/listAllYeWu",method = RequestMethod.POST)
	public ModelAndView listAllYeWu(){
		ModelAndView modelAndView = new ModelAndView();
		List list = service.selectByYeWu();
		buildResponse(modelAndView,list);
		return modelAndView;
	}

	/**
	 * 展示录入人员  -- 内勤人员
	 */
	@RequestMapping(value = "/listAllbeidouOperator",method = RequestMethod.POST)
	public ModelAndView listAllbeidouOperator(){
		ModelAndView modelAndView = new ModelAndView();
		List list = erpOperatorService.selectBybeidouOperator();
		buildResponse(modelAndView,list);
		return modelAndView;
	}


	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改ErpCarInfo", notes = "新增修改ErpCarInfo")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpCarInfo voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		User user = getLoginUser();
		if(id==null || id.trim().equals("")){
			voObj.setInputUserId(user.getUserId());
			voObj.setInputUserName(user.getUserName());
			errMsg = service.insert(voObj);
			Map<String,Boolean> map = new HashMap<String,Boolean>();
			if(errMsg.equals("没有SIM卡，请先添加SIM卡!")){
				map.put("err",true);
				modelAndView.addObject("data",map);
				modelAndView.addObject("success" ,false );
				modelAndView.addObject("code","0");
				return modelAndView;
			}else if (errMsg.equals("该sim卡已使用,请更换卡号!")){
				map.put("err1",true);
				modelAndView.addObject("data" ,map);
				modelAndView.addObject("success" ,false );
				modelAndView.addObject("code","0");
				return modelAndView;
			}else if (errMsg.equals("终端编号不存在,查看是否输入正确")){
				map.put("err2",true);
				modelAndView.addObject("data" ,map);
				modelAndView.addObject("success" ,false );
				modelAndView.addObject("code","0");
				return modelAndView;
			}
		}else{
			errMsg = service.update(voObj);
		}
		buildResponse(modelAndView);
		return modelAndView;
	}
	
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除ErpCarInfo", notes = "根据ID批量删除ErpCarInfo")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
	public ModelAndView delete(String ids,String simCode,String deleteRemark){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		String[] simCodeArr = simCode.split(",");
		//判断是否进行多个删除还是单个删除
		if(arr.length==1 && simCodeArr.length==1){//删除一个
			service.deleteByIdsOne(arr[0],simCodeArr[0],deleteRemark);
		}else{
			//车辆id
			List<String> list = new ArrayList<String>();
			for(String str : arr){
				list.add(str);
			}
			//sim卡号
			List<String> listSim = new ArrayList<String>();
			for(String str : simCodeArr){
				listSim.add(str);
			}
			service.deleteByIds(list,listSim);
		}
		return buildResponse(modelAndView);
	}
	
	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载ErpCarInfo", notes = "根据ID加载ErpCarInfo")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 执行跳转，插入换卡表
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/changeCarSim",method = RequestMethod.POST)
	public ModelAndView changeCarSim(String ids){
		ModelAndView modelAndView = new ModelAndView();
		//id查询
		ErpCarInfo erpCarInfo = service.selectByIdCarInfo(ids);
		//插入换卡记录
		ErpCarSimChangeRecView erpCarSimChangeRec = service.insertByErpChangeSim(erpCarInfo);
		carNum = erpCarSimChangeRec.getCarPlateNum();//车牌号
		carFrame = erpCarSimChangeRec.getCarFrameNum();//车架号
		//进行查询
		ErpCarSimChangeRecQuery erpCarSimChangeRecQuery = new ErpCarSimChangeRecQuery();
		erpCarSimChangeRecQuery.setQueryObj(erpCarSimChangeRec);
		return  buildResponse(modelAndView,erpCarSimChangeRecQuery);
	}

	/**
	 * 车辆续费，跳转续费
	 * @param ids
	 * @return
	 */

	@RequestMapping(value = "/xuFeiSim",method = RequestMethod.POST)
	public ModelAndView xuFeiSim(String ids){
		ModelAndView modelAndView = new ModelAndView();
		//id查询
		ErpCarInfo erpCarInfo = service.selectByIdCarInfo(ids);
		//将ErpCarXufeiRec set 更新数据库插入
		ErpCarXufeiRecView erpCarXufeiRec = erpCarXufeiRecService.insertByErpCarInfo(erpCarInfo);
		carNum = erpCarXufeiRec.getCarPlateNum();//车牌号
		carFrame = erpCarXufeiRec.getCarFrameNum();//车架号
		//进行渲染
		ErpCarXufeiRecQuery erpCarXufeiRecQuery = new ErpCarXufeiRecQuery();
		erpCarXufeiRecQuery.setQueryObj(erpCarXufeiRec);
		return  buildResponse(modelAndView,erpCarXufeiRecQuery);
	}

	/**
	 * 获取续费跳转的车牌号和车架号参数
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadCarNum",method = RequestMethod.POST)
	public ModelAndView loadCarNum(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		//进行渲染
		ErpCarXufeiRecView erpCarXufeiRec = new ErpCarXufeiRecView();
		erpCarXufeiRec.setCarPlateNum(carNum);
		erpCarXufeiRec.setCarFrameNum(carFrame);
		carNum="";
		carFrame="";
		return  buildResponse(modelAndView,erpCarXufeiRec);
	}

	/**
	 * 获取换卡跳转的车牌号和车架号参数
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadChangeCarNum",method = RequestMethod.POST)
	public ModelAndView loadChangeCarNum(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		//进行渲染
		ErpCarSimChangeRecView erpCarSimChangeRec = new ErpCarSimChangeRecView();
		erpCarSimChangeRec.setCarPlateNum(carNum);
		erpCarSimChangeRec.setCarFrameNum(carFrame);
		//赋值完了之后  将值重新赋值为空字符串
		carNum="";
		carFrame="";
		return  buildResponse(modelAndView,erpCarSimChangeRec);
	}

	/**
	 * 查询外勤人员外勤
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/listAllWaiQin",method = RequestMethod.POST)
	public ModelAndView listAllWaiQin(){
		ModelAndView modelAndView = new ModelAndView();
		//获取当前外勤人员名
		User user = getLoginUser();
		List list = service.selectByYeWu();
		List list1 = new ArrayList();
		for(Object object : list){
			SysUser sysUser = (SysUser)object;
			if(user.getUserName().equals(sysUser.getUserName())){
				continue;
			}else {
				list1.add(object);
			}
		}
		buildResponse(modelAndView,list1);
		return modelAndView;
	}


	/**
	 * 查询sim
	 */
	@RequestMapping(value = "/simList",method = RequestMethod.POST)
	public ModelAndView simList(){
		ModelAndView modelAndView = new ModelAndView();
		List list = service.selectSim();
		buildResponse(modelAndView,list);
		return modelAndView;
	}

	/**
	 * 换卡
	 */
	@RequestMapping(value = "/newSaveChangeCode",method = RequestMethod.POST)
	public ModelAndView newSaveChangeCode(ErpCarSimChangeRec erpCarSimChangeRec){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		erpCarSimChangeRec.setCreateUserId(user.getUserId());
		erpCarSimChangeRec.setCreateUserName(user.getUserName());
		//erpCarSimChangeRec.setChangeSimPerson(user.getUserName());//换卡操作人
		//erpCarSimChangeRecService.updateNew(erpCarSimChangeRec);
		/*
		*修改换卡的方法
		* */
		erpCarSimChangeRec.setCreateTime(new Date());
		erpCarSimChangeRec.setCreateUserId(user.getUserId());
		erpCarSimChangeRec.setCreateUserName(user.getUserName());
		erpCarSimChangeRecService.insertOrUpdate(erpCarSimChangeRec);
		buildResponse(modelAndView);
		return modelAndView;
	}




	@RequestMapping(value = "/newSaveChangeTerminalCode",method = RequestMethod.POST)
	public ModelAndView newSaveChangeTerminalCode(ErpTerminalChangeRec erpTerminalChangeRec){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		erpTerminalChangeRec.setId(Guid.guid());//主键
		erpTerminalChangeRec.setCreateTime(new Date());//创建时间
		erpTerminalChangeRec.setChangeTerminalPerson(user.getUserName());//换卡操作人
		erpTerminalChangeRec.setChangeTerminalTime(new Date());//更换时间
		erpTerminalChangeRec.setDataState("1");//数据类型
		service.updateNewTerminal(erpTerminalChangeRec);
		buildResponse(modelAndView);
		return modelAndView;
	}


	@RequestMapping(value = "/erpCarTechnicalDossierEdit",method = RequestMethod.POST)
	public ModelAndView erpCarTechnicalDossierEdit(String carFrameNum){
		ModelAndView modelAndView = new ModelAndView();
		//1.查询此车架号是否存在
		ErpCarTechnicalDossier erpCarTechnicalDossier = service.selectBycarFrameNum(carFrameNum);
		Map<String,ErpCarTechnicalDossier> map = new HashMap<>();
		//2.返回页面
		if(erpCarTechnicalDossier==null){
			map.put("erpCarTechnicalDossier",erpCarTechnicalDossier);
			buildResponse(modelAndView,map);
		}else {
			map.put("erpCarTechnicalDossier",erpCarTechnicalDossier);
			buildResponse(modelAndView,map);
		}

		return modelAndView;
	}

	/**
	 * 导入excel表格车辆信息
	 */
	@ResponseBody
	@RequestMapping(value = "/carInfoExcelImport",method = RequestMethod.POST)
	public Map<String,Object> carInfoExcelImport(@RequestParam("file") MultipartFile file){
		// 判断文件名是否为空
		Map map = new HashMap<String,Object>();
		if (file == null){
			return map;
		}
		// 获取文件名
		String name = file.getOriginalFilename();
		// 判断文件大小、即名称
		long size = file.getSize();
		if (name == null || ("").equals(name) && size == 0){
			return map;
		}
		try {
			// 把文件转换成字节流形式
			int i = service.batchImport(name, file);
			if (i > 0) {
				String Msg = "批量导入EXCEL成功！";
				map.put("msg", Msg);
				map.put("code", 1);
			}
			else {
				String Msg = "批量导入EXCEL失败！";
				map.put("success", false);
				map.put("code", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}


	@ResponseBody
	@RequestMapping(value = "/carInfoExcel",method = RequestMethod.POST)
	public ModelAndView carInfoExcel(@ModelAttribute ErpCarInfoQuery query) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> mapQuery = new HashMap<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			mapQuery.put("beidouOperator",query.getQueryObj().getBeidouOperator());
			mapQuery.put("yehuName",query.getQueryObj().getYehuName());
			mapQuery.put("carPlateNum",query.getQueryObj().getCarPlateNum());
			mapQuery.put("carFrameNum",query.getQueryObj().getCarFrameNum());
			mapQuery.put("xianquName",query.getQueryObj().getXianquName());
			if(query.getQueryObj().getSelectTimeStr()==null || query.getQueryObj().getSelectTimeStr().equals("")){
				mapQuery.put("selectTime",null);
			}else {
				mapQuery.put("selectTime",simpleDateFormat.parse(query.getQueryObj().getSelectTimeStr()));
			}
			if(query.getQueryObj().getNewEndTimeStr()==null || query.getQueryObj().getNewEndTimeStr().equals("")){
				mapQuery.put("newEndTime",null);
			}else {
				mapQuery.put("newEndTime",simpleDateFormat.parse(query.getQueryObj().getNewEndTimeStr()));
			}
			if(query.getQueryObj().getAdmissionTimeStartStr()==null || query.getQueryObj().getAdmissionTimeStartStr().equals("")){
				mapQuery.put("admissionTimeStart",null);
			}else {
				mapQuery.put("admissionTimeStart",simpleDateFormat.parse(query.getQueryObj().getAdmissionTimeStartStr()));
			}
			if(query.getQueryObj().getAdmissionTimeEndStr()==null || query.getQueryObj().getAdmissionTimeEndStr().equals("")){
				mapQuery.put("admissionTimeEnd",null);
			}else {
				mapQuery.put("admissionTimeEnd",simpleDateFormat.parse(query.getQueryObj().getAdmissionTimeEndStr()));
			}
			if(query.getQueryObj().getLastXuFeiStartTimeStr()==null || query.getQueryObj().getLastXuFeiStartTimeStr().equals("")){
				mapQuery.put("lastXuFeiStartTime",null);
			}else {
				mapQuery.put("lastXuFeiStartTime",simpleDateFormat.parse(query.getQueryObj().getLastXuFeiStartTimeStr()));
			}
			if(query.getQueryObj().getLastXuFeiEndTimeStr()==null || query.getQueryObj().getLastXuFeiEndTimeStr().equals("")){
				mapQuery.put("lastXuFeiEndTime",null);
			}else {
				mapQuery.put("lastXuFeiEndTime",simpleDateFormat.parse(query.getQueryObj().getLastXuFeiEndTimeStr()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<String, List> map = new HashMap<>();
		//1.查询所有车辆信息
		List<ErpCarSimpleInfo> list = service.selectAllCarInfo(mapQuery);
		map.put("list", list);
		return buildResponse(modelAndView, map);
	}

	@ResponseBody
	@RequestMapping(value = "/carInfoExcelNew",method = RequestMethod.POST)
	public ModelAndView carInfoExcelNew(@ModelAttribute ErpCarInfoQuery query) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> mapQuery = new HashMap<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			mapQuery.put("beidouOperator",query.getQueryObj().getBeidouOperator());
			mapQuery.put("yehuName",query.getQueryObj().getYehuName());
			mapQuery.put("carPlateNum",query.getQueryObj().getCarPlateNum());
			mapQuery.put("carFrameNum",query.getQueryObj().getCarFrameNum());
			mapQuery.put("xianquName",query.getQueryObj().getXianquName());
			mapQuery.put("simCode",query.getQueryObj().getSimCode());
			if(query.getQueryObj().getSelectTimeStr()==null || query.getQueryObj().getSelectTimeStr().equals("")){
				mapQuery.put("selectTime",null);
			}else {
				mapQuery.put("selectTime",simpleDateFormat.parse(query.getQueryObj().getSelectTimeStr()));
			}
			if(query.getQueryObj().getNewEndTimeStr()==null || query.getQueryObj().getNewEndTimeStr().equals("")){
				mapQuery.put("newEndTime",null);
			}else {
				mapQuery.put("newEndTime",simpleDateFormat.parse(query.getQueryObj().getNewEndTimeStr()));
			}
			if(query.getQueryObj().getAdmissionTimeStartStr()==null || query.getQueryObj().getAdmissionTimeStartStr().equals("")){
				mapQuery.put("admissionTimeStart",null);
			}else {
				mapQuery.put("admissionTimeStart",simpleDateFormat.parse(query.getQueryObj().getAdmissionTimeStartStr()));
			}
			if(query.getQueryObj().getAdmissionTimeEndStr()==null || query.getQueryObj().getAdmissionTimeEndStr().equals("")){
				mapQuery.put("admissionTimeEnd",null);
			}else {
				mapQuery.put("admissionTimeEnd",simpleDateFormat.parse(query.getQueryObj().getAdmissionTimeEndStr()));
			}
			if(query.getQueryObj().getLastXuFeiStartTimeStr()==null || query.getQueryObj().getLastXuFeiStartTimeStr().equals("")){
				mapQuery.put("lastXuFeiStartTime",null);
			}else {
				mapQuery.put("lastXuFeiStartTime",simpleDateFormat.parse(query.getQueryObj().getLastXuFeiStartTimeStr()));
			}
			if(query.getQueryObj().getLastXuFeiEndTimeStr()==null || query.getQueryObj().getLastXuFeiEndTimeStr().equals("")){
				mapQuery.put("lastXuFeiEndTime",null);
			}else {
				mapQuery.put("lastXuFeiEndTime",simpleDateFormat.parse(query.getQueryObj().getLastXuFeiEndTimeStr()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<String, List> map = new HashMap<>();
		//1.查询所有车辆信息
		List<ErpCarSimpleInfo> list = service.selectAllCarInfoNew(mapQuery);
		map.put("list", list);
		return buildResponse(modelAndView, map);
	}

	@ResponseBody
	@RequestMapping(value = "/selectCarInfoExcel",method = RequestMethod.POST)
	public ModelAndView selectCarInfoExcel(String ids) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, List> map = new HashMap<>();
		String[] arr = ids.split(",");
		List<String> idList = new ArrayList<String>();
		for(String str : arr){
			idList.add(str);
		}
		//1.查询所有车辆信息
		List<ErpCarSimpleInfo> list = service.selectAllById(idList);
		map.put("list", list);
		return buildResponse(modelAndView, map);
	}

	/**
	 *@Author:ZhaoSiDa
	 *@Description: 发送提醒检车定位的通知
	 *@DateTime: 2020/4/28 9:28
	 */
	@ResponseBody
	@RequestMapping(value = "/sendRemindCheckGpsPhoneMsg",method = RequestMethod.POST)
	public ModelAndView sendRemindCheckGpsPhoneMsg(String beidouOperator){
		ModelAndView modelAndView = new ModelAndView();
		service.sendRemindCheckGpsPhoneMsg(beidouOperator);
		return buildResponse(modelAndView);
	}


}

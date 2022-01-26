package com.edgedo.sys.controller;


import java.io.IOException;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.util.HttpRequestUtil;
import com.edgedo.sys.entity.CarSimpleInfoVo;
import com.edgedo.sys.entity.ErpCarSimpleInfo;
import com.edgedo.sys.queryvo.ErpCarSimpleInfoQuery;
import com.edgedo.sys.service.ErpCarSimpleInfoService;
import com.edgedo.sys.service.ErpUserWorkLogService;
import com.edgedo.sys.service.SysConfigService;
import com.edgedo.timedtask.UpdateQghyptCarInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "ErpCarSimpleInfo")
@Controller
@RequestMapping("/admin/erpCarSimpleInfo")
public class ErpCarSimpleInfoController extends BaseController{
	
	@Autowired
	private ErpCarSimpleInfoService service;

//	@Autowired
//	private UpdateQghyptCarInfo updateQghyptCarInfo;

	@Autowired
	private SysConfigService sysConfigService;

	@Autowired
	private ErpUserWorkLogService erpUserWorkLogService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpCarSimpleInfo", notes = "分页查询ErpCarSimpleInfo")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpCarSimpleInfoQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改ErpCarSimpleInfo", notes = "新增修改ErpCarSimpleInfo")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpCarSimpleInfo voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			errMsg = service.insert(voObj);
		}else{
			errMsg = service.update(voObj);
		}
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}
	
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除ErpCarSimpleInfo", notes = "根据ID批量删除ErpCarSimpleInfo")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
	public ModelAndView delete(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		service.deleteByIds(list);		
		return buildResponse(modelAndView);
	}
	
	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载ErpCarSimpleInfo", notes = "根据ID加载ErpCarSimpleInfo")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 更新车辆信息
	 */
/*	@RequestMapping(value = "/updateCarInfo",method = RequestMethod.POST)
	public ModelAndView  updateCarInfo(){
		ModelAndView modelAndView = new ModelAndView();
		//先进行判断  是否已在更新
		String flag = sysConfigService.getValueById("UPDATE_CAR_INFO_DATA_FLAG");
		if(flag.equals("1")){//正在更新
			modelAndView.addObject("success" ,false);
			//和前端配合0
			modelAndView.addObject("code","0");
			return modelAndView;
		}else {
			String err = updateQghyptCarInfo.dynamicLoopNew();
			if(err.equals("请重新登录")){
				return buildErrorResponse(modelAndView,err);
			}
			return buildResponse(modelAndView);
		}
	}*/

	/**
	 * 更新车辆信息
	 */
	@RequestMapping(value = "/updateCarInfoNew1",method = RequestMethod.POST)
	public ModelAndView  updateCarInfoNew1(){
		ModelAndView modelAndView = new ModelAndView();
		//先进行判断  是否已在更新
		String flag = sysConfigService.getValueById("UPDATE_CAR_INFO_DATA_FLAG");
		if(flag.equals("1")){//正在更新
			modelAndView.addObject("success" ,false);
			//和前端配合0
			modelAndView.addObject("code","0");
			return modelAndView;
		}else {
				String changeNetUrl = sysConfigService.getValueById("SELECT_QGHYPT_CAR_INFO");
				String cookieUseridCar = sysConfigService.getValueById("COOKIE_USERID_HD");
				Map<String,String> params = new HashMap<>();
				//拿到20条数据
				//requestParam.page的value值可变  要想一次插入100条数据 要让这个动态加上1
			//清空所有的数据
			service.deleteAll();
			//根据县区查询
			List<String> xianquList = new ArrayList();
			xianquList.add("130302");
			xianquList.add("130303");
			xianquList.add("130304");
			xianquList.add("130321");
			xianquList.add("130322");
			xianquList.add("130323");
			xianquList.add("130324");
			xianquList.add("130355");
			for(int i=0;i<xianquList.size();i++){
				startTb1(xianquList.get(i),changeNetUrl,cookieUseridCar);
			}
//			if(err.equals("请重新登录")){
//				return buildErrorResponse(modelAndView,err);
//			}
			return buildResponse(modelAndView);
		}
	}

	public void startTb1(String xianquId,String changeNetUrl,String cookieUseridCar){

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				Map<String,String> params = new HashMap<>();
				try {
					int t = 1;
					for(int j =0;j<t;j++){
						params.put("requestParam.equal.areaCode","130000");
						params.put("requestParam.equal.cityId","130300");
						params.put("requestParam.equal.county",xianquId);
						params.put("requestParam.equal.orgCode",xianquId);
						params.put("requestParam.equal.orgLevel","3");
						params.put("requestParam.page",(j+1)+"");
						params.put("requestParam.rows","500");
						params.put("sortname","vehicleNo");
						params.put("sortorder","asc");
						Map<String,String> header = new HashMap<>();
						header.put("Cookie",cookieUseridCar);
						String s = "";
						try {
							s = HttpRequestUtil.sendPostRequest(changeNetUrl,params,header);
							JSONObject jsonObject = new JSONObject(s);
							int toTal = Integer.valueOf(jsonObject.optString("Total"));
							t = toTal/500 +1;
							String rows = jsonObject.optString("Rows");
							List<CarSimpleInfoVo> list = com.alibaba.fastjson.JSON.parseArray(rows,CarSimpleInfoVo.class);
							if(list!=null && list.size()>0){
								service.insertAll(list);
							}
							System.out.println(toTal);
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();

	}

	/**
	 * 更新车辆信息
	 */
	@RequestMapping(value = "/updateCarInfoNew",method = RequestMethod.POST)
	public ModelAndView  updateCarInfoNew(){
		ModelAndView modelAndView = new ModelAndView();
		//先进行判断  是否已在更新
		String flag = sysConfigService.getValueById("UPDATE_CAR_INFO_DATA_FLAG");
		if(flag.equals("1")){//正在更新
			modelAndView.addObject("success" ,false);
			//和前端配合0
			modelAndView.addObject("code","0");
			return modelAndView;
		}else {
			String changeNetUrl = sysConfigService.getValueById("SELECT_QGHYPT_CAR_INFO");
			String cookieUseridCar = sysConfigService.getValueById("COOKIE_USERID_HD");
			Map<String,String> params = new HashMap<>();
			//拿到20条数据
			//requestParam.page的value值可变  要想一次插入100条数据 要让这个动态加上1
			//清空所有的数据
			service.deleteAll();
			//根据县区查询
			List<String> xianquList = new ArrayList();
			xianquList.add("130302");
			xianquList.add("130303");
			xianquList.add("130304");
			xianquList.add("130321");
			xianquList.add("130322");
			xianquList.add("130323");
			xianquList.add("130324");
			xianquList.add("130355");
			for(int i=0;i<xianquList.size();i++){
				startTb(xianquList.get(i),changeNetUrl,cookieUseridCar);
			}
//			if(err.equals("请重新登录")){
//				return buildErrorResponse(modelAndView,err);
//			}
			return buildResponse(modelAndView);
		}
	}

	public void startTb(String xianquId,String changeNetUrl,String cookieUseridCar){

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					boolean flag = true;
					int toTal = 0;
					while (flag){
						int t = 1;
						for(int j =0;j<t;j++){
							String params = "";
							params += "requestParam.equal.areaCode=130000";
							params += "&requestParam.equal.cityId=130300";
							params += "&requestParam.equal.county="+xianquId;
							params += "&requestParam.equal.orgCode="+xianquId;
							params += "&requestParam.equal.orgLevel=3";
							params += "&requestParam.page="+(j+1);
							params += "&requestParam.rows=1000";
							params += "&sortname=vehicleNo";
							params += "&sortorder=asc";
							Map<String,String> header = new HashMap<>();
							header.put("Cookie",cookieUseridCar);
							String s = "";
							try {
								s = HttpRequestUtil.sendGetRequestHeader(changeNetUrl,params,header);
								JSONObject jsonObject = new JSONObject(s);
								toTal = Integer.valueOf(jsonObject.optString("Total"));
								t = toTal/1000 +1;
								String rows = jsonObject.optString("Rows");
								List<CarSimpleInfoVo> list = com.alibaba.fastjson.JSON.parseArray(rows,CarSimpleInfoVo.class);
								if(list!=null && list.size()>0){
									service.insertAll(list);
								}
								System.out.println(toTal);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						//统计数量是否全
						int countByXianquId = service.countByXianquId(xianquId);
						if(countByXianquId == toTal){
							flag = false;
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();

	}

	//查询车辆信息是否正在更新中
	@RequestMapping(value = "/checkIsUpdating",method = RequestMethod.POST)
	public ModelAndView checkIsUpdating(){
		ModelAndView modelAndView = new ModelAndView();
		String flag = sysConfigService.getValueById("UPDATE_CAR_INFO_DATA_FLAG");
		if(flag == null){
			flag = "0";
		}
		return buildResponse(modelAndView,flag);
	}

	@Scheduled(cron = "0 20 17 * * ?")//0 20 17 * * ?
	public void triggerAddCarNum(){
		//判断当前时间是否周五
		String weekCurr = erpUserWorkLogService.getWeek(new Date());
		//判断是否已经更新过
		int flag = service.selectByNewTime();
		int flagBeiDou = service.selectByBeiDouNewTime();
		if(weekCurr.equals("星期五")){
			if(flag==0 && flagBeiDou==0){
				service.selectAndInsertCarAddNum();
				service.selectAndInsertCarBeiDouNum();
			}
		}else {

		}
	}
	@ResponseBody
	@RequestMapping(value = "/carInfoExcel",method = RequestMethod.POST)
	public ModelAndView carInfoExcel() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, List> map = new HashMap<>();
		//1.查询所有车辆信息
		List<ErpCarSimpleInfo> list = service.selectAllCarInfo();
		map.put("list", list);
		return buildResponse(modelAndView, map);
	}
}

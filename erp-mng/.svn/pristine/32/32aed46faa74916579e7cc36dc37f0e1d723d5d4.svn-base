package com.edgedo.sys.controller;


import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.sys.entity.ErpAddCarStatistics;
import com.edgedo.sys.entity.ErpBeidouAddStatistics;
import com.edgedo.sys.queryvo.ErpBeidouAddStatisticsQuery;
import com.edgedo.sys.service.ErpBeidouAddStatisticsService;
import com.edgedo.sys.service.ErpUserWorkLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "ErpBeidouAddStatistics")
@Controller
@RequestMapping("/admin/erpBeidouAddStatistics")
public class ErpBeidouAddStatisticsController extends BaseController{
	
	@Autowired
	private ErpBeidouAddStatisticsService service;

	@Autowired
	private ErpUserWorkLogService erpUserWorkLogService;
	/**
	 * 分页查询
	 * @param
	 * @return
	 */
//	@ApiOperation(value = "分页查询ErpBeidouAddStatistics", notes = "分页查询ErpBeidouAddStatistics")
//	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(String statisticsToday){
		ModelAndView modelAndView = new ModelAndView();
		String dateQuJianStr = statisticsToday.replace(" ","");//去掉空格
		//2.根据最新的时间  查询 所有该时间的记录
		List<ErpBeidouAddStatistics> list = service.selectByNewDate(dateQuJianStr);
		//分开多个县区的新增车辆
		List<ErpBeidouAddStatistics> listHGCurr = new ArrayList<ErpBeidouAddStatistics>();//海港区
		List<ErpBeidouAddStatistics> listFNCurr = new ArrayList<ErpBeidouAddStatistics>();//抚宁县
		List<ErpBeidouAddStatistics> listCLCurr = new ArrayList<ErpBeidouAddStatistics>();//昌黎县
		List<ErpBeidouAddStatistics> listLLCurr = new ArrayList<ErpBeidouAddStatistics>();//卢龙县
		List<ErpBeidouAddStatistics> listKFCurr = new ArrayList<ErpBeidouAddStatistics>();//开发区
		List<ErpBeidouAddStatistics> listQLCurr = new ArrayList<ErpBeidouAddStatistics>();//青龙县
		List<ErpBeidouAddStatistics> listSHGCurr = new ArrayList<>();//山海关区
		for(ErpBeidouAddStatistics erpBeidouAddStatistics : list){
			if(erpBeidouAddStatistics.getXianquName().equals("海港区")){
				listHGCurr.add(erpBeidouAddStatistics);
			}else if(erpBeidouAddStatistics.getXianquName().equals("抚宁县")){
				listFNCurr.add(erpBeidouAddStatistics);
			}else if (erpBeidouAddStatistics.getXianquName().equals("昌黎县")){
				listCLCurr.add(erpBeidouAddStatistics);
			}else if (erpBeidouAddStatistics.getXianquName().equals("卢龙县")){
				listLLCurr.add(erpBeidouAddStatistics);
			}else if (erpBeidouAddStatistics.getXianquName().equals("开发区")){
				listKFCurr.add(erpBeidouAddStatistics);
			}else if (erpBeidouAddStatistics.getXianquName().equals("青龙满族自治县")){
				listQLCurr.add(erpBeidouAddStatistics);
			}else if (erpBeidouAddStatistics.getXianquName().equals("山海关区")){
				listSHGCurr.add(erpBeidouAddStatistics);
			}
		}
		//3.上升网页
		Map<String,List> map = new HashMap<String,List>();
		map.put("listHGCurr",listHGCurr);
		map.put("listFNCurr",listFNCurr);
		map.put("listCLCurr",listCLCurr);
		map.put("listLLCurr",listLLCurr);
		map.put("listKFCurr",listKFCurr);
		map.put("listQLCurr",listQLCurr);
		map.put("listSHGCurr",listSHGCurr);
		buildResponse(modelAndView,map);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改ErpBeidouAddStatistics", notes = "新增修改ErpBeidouAddStatistics")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpBeidouAddStatistics voObj){
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
	@ApiOperation(value = "根据ID批量删除ErpBeidouAddStatistics", notes = "根据ID批量删除ErpBeidouAddStatistics")
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
	@ApiOperation(value = "根据ID加载ErpBeidouAddStatistics", notes = "根据ID加载ErpBeidouAddStatistics")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 动态表格赋值
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/setValueTable",method = RequestMethod.POST)
	public ModelAndView setValueTable(){
		ModelAndView modelAndView = new ModelAndView();
		//1.查询最新的记录
		String newDate = service.selectByNewInfo();
		//2.根据最新的时间  查询 所有该时间的记录
		List<ErpBeidouAddStatistics> list = service.selectByNewDate(newDate);
		//分开多个县区的新增车辆
		List<ErpBeidouAddStatistics> listHGCurr = new ArrayList<ErpBeidouAddStatistics>();//海港区
		List<ErpBeidouAddStatistics> listFNCurr = new ArrayList<ErpBeidouAddStatistics>();//抚宁县
		List<ErpBeidouAddStatistics> listSHGurr = new ArrayList<>();//山海关区
		List<ErpBeidouAddStatistics> listCLCurr = new ArrayList<ErpBeidouAddStatistics>();//昌黎县
		List<ErpBeidouAddStatistics> listLLCurr = new ArrayList<ErpBeidouAddStatistics>();//卢龙县
		List<ErpBeidouAddStatistics> listKFCurr = new ArrayList<ErpBeidouAddStatistics>();//开发区
		List<ErpBeidouAddStatistics> listQLCurr = new ArrayList<ErpBeidouAddStatistics>();//青龙县
		for(ErpBeidouAddStatistics erpBeidouAddStatistics : list){
			if(erpBeidouAddStatistics.getXianquName().equals("海港区")){
				listHGCurr.add(erpBeidouAddStatistics);
			}else if(erpBeidouAddStatistics.getXianquName().equals("抚宁县")){
				listFNCurr.add(erpBeidouAddStatistics);
			}else if (erpBeidouAddStatistics.getXianquName().equals("昌黎县")){
				listCLCurr.add(erpBeidouAddStatistics);
			}else if (erpBeidouAddStatistics.getXianquName().equals("卢龙县")){
				listLLCurr.add(erpBeidouAddStatistics);
			}else if (erpBeidouAddStatistics.getXianquName().equals("开发区")){
				listKFCurr.add(erpBeidouAddStatistics);
			}else if (erpBeidouAddStatistics.getXianquName().equals("青龙满族自治县")){
				listQLCurr.add(erpBeidouAddStatistics);
			}else if(erpBeidouAddStatistics.getXianquName().equals("山海关区")){
				listSHGurr.add(erpBeidouAddStatistics);
			}
		}
		//3.上升网页
		Map<String,List> map = new HashMap<String,List>();
		map.put("listHGCurr",listHGCurr);
		map.put("listFNCurr",listFNCurr);
		map.put("listCLCurr",listCLCurr);
		map.put("listLLCurr",listLLCurr);
		map.put("listKFCurr",listKFCurr);
		map.put("listQLCurr",listQLCurr);
		map.put("listSHGCurr",listSHGurr);
		buildResponse(modelAndView,map);
		return modelAndView;
	}

	/**
	 * 设定时间区间
	 * 上周六 到这周五
	 */
	public String getBetweenTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -7);
		date = c.getTime();
		String preDay = sdf.format(date);
		return preDay;
	}

}

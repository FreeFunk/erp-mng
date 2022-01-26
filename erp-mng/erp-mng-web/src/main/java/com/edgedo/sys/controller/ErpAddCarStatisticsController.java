package com.edgedo.sys.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.sys.entity.ErpAddCarStatistics;
import com.edgedo.sys.entity.ErpBeidouAddStatistics;
import com.edgedo.sys.queryvo.ErpAddCarStatisticsQuery;
import com.edgedo.sys.service.ErpAddCarStatisticsService;
import com.edgedo.sys.service.ErpUserWorkLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "ErpAddCarStatistics")
@Controller
@RequestMapping("/admin/erpAddCarStatistics")
public class ErpAddCarStatisticsController extends BaseController{
	
	@Autowired
	private ErpAddCarStatisticsService service;

	@Autowired
	private ErpUserWorkLogService erpUserWorkLogService;
	
	/**
	 * 分页查询
	 * @param
	 * @return
	 */
//	@ApiOperation(value = "分页查询ErpAddCarStatistics", notes = "分页查询ErpAddCarStatistics")
//	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(String statisticsCarToday){
		ModelAndView modelAndView = new ModelAndView();
		//拿到当前时间  定位到上周
		String betDate = service.selectByTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(statisticsCarToday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//查询总数
		int toTal = service.selectByAll();
		List<Integer> listTotal = new ArrayList<Integer>();
		listTotal.add(toTal);
		String betweenTime = getBetweenTime(date);
		//获取到这周和上周 进行查询
		List<ErpAddCarStatistics> listCurr = service.selectByCurrDate(statisticsCarToday);
		List<ErpAddCarStatistics> listBetween = service.selectByCurrDate(betweenTime);
		Map<String,List> map = getMap(listCurr,listBetween);
		map.put("listTotal",listTotal);
		buildResponse(modelAndView,map);
		return modelAndView;
	}

	@RequestMapping(value = "/listXianQu",method = RequestMethod.POST)
	public ModelAndView  listXianQu(){
		ModelAndView modelAndView = new ModelAndView();
		Map<String , String> map = new HashMap<>();
		map.put("hgq","海港区");
		return buildResponse(modelAndView, map);
	}



	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改ErpAddCarStatistics", notes = "新增修改ErpAddCarStatistics")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpAddCarStatistics voObj){
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
	@ApiOperation(value = "根据ID批量删除ErpAddCarStatistics", notes = "根据ID批量删除ErpAddCarStatistics")
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
	@ApiOperation(value = "根据ID加载ErpAddCarStatistics", notes = "根据ID加载ErpAddCarStatistics")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 格式化日期
	 */
	public String getDate(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currDateStr = simpleDateFormat.format(date);
		return currDateStr;
	}

	/**
	 * 动态表格赋值
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/setValueTable",method = RequestMethod.POST)
	public ModelAndView setValueTable(){
		ModelAndView modelAndView = new ModelAndView();
		String currWeek = erpUserWorkLogService.getWeek(new Date());
		//1.判断当前是否到周五
		if(currWeek.equals("星期六")){
			//拿到当前时间  定位到上周
			String currTime = getDate(new Date());
			String betweenTime = getBetweenTime(new Date());
			//获取到这周和上周 进行查询
			List<ErpAddCarStatistics> listCurr = service.selectByCurrDate(currTime);
			List<ErpAddCarStatistics> listBetween = service.selectByCurrDate(betweenTime);
			//查询总数
			int toTal = service.selectByAll();
			List<Integer> listTotal = new ArrayList<Integer>();
			listTotal.add(toTal);
			Map<String,List> map = getMap(listCurr,listBetween);
			map.put("listTotal",listTotal);
			buildResponse(modelAndView,map);
			return modelAndView;
		}else {
			//拿到前两个周的时间  根据这个时间  拿到前两周的数据
			String betDate = service.selectByTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse(betDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//2.查询上一周的时间
			//3.查询本周时间
			String betDate1 = getBetweenTime(date);
			//获取到这周和上周 进行查询
			List<ErpAddCarStatistics> listCurr = service.selectByCurrDate(betDate);
			List<ErpAddCarStatistics> listBetween = service.selectByCurrDate(betDate1);
			//查询总数
			int toTal = service.selectByAll();
			List<Integer> listTotal = new ArrayList<Integer>();//海港区
			listTotal.add(toTal);
			Map<String,List> map = getMap(listCurr,listBetween);
			map.put("listTotal",listTotal);
			buildResponse(modelAndView,map);
			return modelAndView;
		}
	}

	public Map<String,List> getMap(List<ErpAddCarStatistics> listCurr,List<ErpAddCarStatistics> listBetween){
		//分开多个县区的新增车辆
		List<ErpAddCarStatistics> listHGCurr = new ArrayList<ErpAddCarStatistics>();//海港区
		List<Integer> listHGTotal = new ArrayList<Integer>();//海港区总数
		Integer HGTotalCurr=0;
		List<ErpAddCarStatistics> listFNCurr = new ArrayList<ErpAddCarStatistics>();//抚宁县
		List<Integer> listFNTotal = new ArrayList<Integer>();//抚宁县总数
		Integer FNTotalCurr=0;
		List<ErpAddCarStatistics> listCLCurr = new ArrayList<ErpAddCarStatistics>();//昌黎县
		List<Integer> listCLTotal = new ArrayList<Integer>();//昌黎县总数
		Integer CLTotalCurr=0;
		List<ErpAddCarStatistics> listLLCurr = new ArrayList<ErpAddCarStatistics>();//卢龙县
		List<Integer> listLLTotal = new ArrayList<Integer>();//卢龙县总数
		Integer LLTotalCurr=0;
		List<ErpAddCarStatistics> listKFCurr = new ArrayList<ErpAddCarStatistics>();//开发区
		List<Integer> listKFTotal = new ArrayList<Integer>();//开发区总数
		Integer KFTotalCurr=0;
		List<ErpAddCarStatistics> listQLCurr = new ArrayList<ErpAddCarStatistics>();//青龙县
		List<Integer> listQLTotal = new ArrayList<Integer>();//青龙县总数
		Integer QLTotalCurr=0;
		List<ErpAddCarStatistics> listSHCurr = new ArrayList<ErpAddCarStatistics>();//山海关
		List<Integer> listSHTotal = new ArrayList<Integer>();//山海关总数
		Integer SHTotalCurr=0;
		List<ErpAddCarStatistics> listBDCurr = new ArrayList<ErpAddCarStatistics>();//北戴河
		List<Integer> listBDTotal = new ArrayList<Integer>();//北戴河总数
		Integer BDTotalCurr=0;
		for(ErpAddCarStatistics erpAddCarStatistics : listCurr){
			if(erpAddCarStatistics.getXianquName().equals("海港区")){
				HGTotalCurr = HGTotalCurr + erpAddCarStatistics.getCarTodayNum();
				listHGCurr.add(erpAddCarStatistics);
			}else if(erpAddCarStatistics.getXianquName().equals("抚宁县")){
				FNTotalCurr = FNTotalCurr + erpAddCarStatistics.getCarTodayNum();
				listFNCurr.add(erpAddCarStatistics);
			}else if (erpAddCarStatistics.getXianquName().equals("昌黎县")){
				CLTotalCurr = CLTotalCurr + erpAddCarStatistics.getCarTodayNum();
				listCLCurr.add(erpAddCarStatistics);
			}else if (erpAddCarStatistics.getXianquName().equals("卢龙县")){
				LLTotalCurr = LLTotalCurr + erpAddCarStatistics.getCarTodayNum();
				listLLCurr.add(erpAddCarStatistics);
			}else if (erpAddCarStatistics.getXianquName().equals("开发区")){
				KFTotalCurr = KFTotalCurr + erpAddCarStatistics.getCarTodayNum();
				listKFCurr.add(erpAddCarStatistics);
			}else if (erpAddCarStatistics.getXianquName().equals("青龙满族自治县")){
				QLTotalCurr = QLTotalCurr + erpAddCarStatistics.getCarTodayNum();
				listQLCurr.add(erpAddCarStatistics);
			}else if (erpAddCarStatistics.getXianquName().equals("山海关区")){
				SHTotalCurr = SHTotalCurr + erpAddCarStatistics.getCarTodayNum();
				listSHCurr.add(erpAddCarStatistics);
			}else if (erpAddCarStatistics.getXianquName().equals("北戴河区")){
				BDTotalCurr = BDTotalCurr + erpAddCarStatistics.getCarTodayNum();
				listBDCurr.add(erpAddCarStatistics);
			}
		}

		List<ErpAddCarStatistics> listHGBetween = new ArrayList<ErpAddCarStatistics>();//海港区
		List<Integer> listHGTotalBetween = new ArrayList<Integer>();//海港区总数
		Integer HGTotalBetween=0;
		List<ErpAddCarStatistics> listFNBetween = new ArrayList<ErpAddCarStatistics>();//抚宁县
		List<Integer> listFNTotalBetween = new ArrayList<Integer>();//海港区总数
		Integer FNTotalBetween=0;
		List<ErpAddCarStatistics> listCLBetween = new ArrayList<ErpAddCarStatistics>();//昌黎县
		List<Integer> listCLTotalBetween = new ArrayList<Integer>();//海港区总数
		Integer CLTotalBetween=0;
		List<ErpAddCarStatistics> listLLBetween = new ArrayList<ErpAddCarStatistics>();//卢龙县
		List<Integer> listLLTotalBetween = new ArrayList<Integer>();//海港区总数
		Integer LLTotalBetween=0;
		List<ErpAddCarStatistics> listKFBetween = new ArrayList<ErpAddCarStatistics>();//开发区
		List<Integer> listKFTotalBetween = new ArrayList<Integer>();//海港区总数
		Integer KFTotalBetween=0;
		List<ErpAddCarStatistics> listQLBetween = new ArrayList<ErpAddCarStatistics>();//青龙县
		List<Integer> listQLTotalBetween = new ArrayList<Integer>();//海港区总数
		Integer QLTotalBetween=0;
		List<ErpAddCarStatistics> listSHBetween = new ArrayList<ErpAddCarStatistics>();//山海关
		List<Integer> listSHTotalBetween = new ArrayList<Integer>();//海港区总数
		Integer SHTotalBetween=0;
		List<ErpAddCarStatistics> listBDBetween = new ArrayList<ErpAddCarStatistics>();//北戴河
		List<Integer> listBDTotalBetween = new ArrayList<Integer>();//海港区总数
		Integer BDTotalBetween=0;
		for(ErpAddCarStatistics erpAddCarStatistics : listBetween){
			if(erpAddCarStatistics.getXianquName().equals("海港区")){
				HGTotalBetween = HGTotalBetween + erpAddCarStatistics.getCarTodayNum();
				listHGBetween.add(erpAddCarStatistics);
			}else if(erpAddCarStatistics.getXianquName().equals("抚宁县")){
				FNTotalBetween = FNTotalBetween + erpAddCarStatistics.getCarTodayNum();
				listFNBetween.add(erpAddCarStatistics);
			}else if (erpAddCarStatistics.getXianquName().equals("昌黎县")){
				CLTotalBetween = CLTotalBetween + erpAddCarStatistics.getCarTodayNum();
				listCLBetween.add(erpAddCarStatistics);
			}else if (erpAddCarStatistics.getXianquName().equals("卢龙县")){
				LLTotalBetween = LLTotalBetween + erpAddCarStatistics.getCarTodayNum();
				listLLBetween.add(erpAddCarStatistics);
			}else if (erpAddCarStatistics.getXianquName().equals("开发区")){
				KFTotalBetween = KFTotalBetween + erpAddCarStatistics.getCarTodayNum();
				listKFBetween.add(erpAddCarStatistics);
			}else if (erpAddCarStatistics.getXianquName().equals("青龙满族自治县")){
				QLTotalBetween = QLTotalBetween + erpAddCarStatistics.getCarTodayNum();
				listQLBetween.add(erpAddCarStatistics);
			}else if (erpAddCarStatistics.getXianquName().equals("山海关区")){
				SHTotalBetween = SHTotalBetween + erpAddCarStatistics.getCarTodayNum();
				listSHBetween.add(erpAddCarStatistics);
			}else if (erpAddCarStatistics.getXianquName().equals("北戴河区")){
				BDTotalBetween = BDTotalBetween + erpAddCarStatistics.getCarTodayNum();
				listBDBetween.add(erpAddCarStatistics);
			}
		}
		listSHTotal.add(SHTotalCurr);
		listQLTotal.add(QLTotalCurr);
		listKFTotal.add(KFTotalCurr);
		listLLTotal.add(LLTotalCurr);
		listCLTotal.add(CLTotalCurr);
		listFNTotal.add(FNTotalCurr);
		listHGTotal.add(HGTotalCurr);
		listBDTotal.add(BDTotalCurr);
		listHGTotalBetween.add(HGTotalBetween);
		listFNTotalBetween.add(FNTotalBetween);
		listCLTotalBetween.add(CLTotalBetween);
		listLLTotalBetween.add(LLTotalBetween);
		listKFTotalBetween.add(KFTotalBetween);
		listQLTotalBetween.add(QLTotalBetween);
		listSHTotalBetween.add(SHTotalBetween);
		listBDTotalBetween.add(BDTotalBetween);

		Map<String,List> map = new HashMap<String,List>();
		map.put("listHGCurr",listHGCurr);
		map.put("listFNCurr",listFNCurr);
		map.put("listCLCurr",listCLCurr);
		map.put("listLLCurr",listLLCurr);
		map.put("listKFCurr",listKFCurr);
		map.put("listQLCurr",listQLCurr);
		map.put("listSHCurr",listSHCurr);
		map.put("listBDCurr",listBDCurr);

		map.put("listHGBetween",listHGBetween);
		map.put("listFNBetween",listFNBetween);
		map.put("listCLBetween",listCLBetween);
		map.put("listLLBetween",listLLBetween);
		map.put("listKFBetween",listKFBetween);
		map.put("listQLBetween",listQLBetween);
		map.put("listSHBetween",listSHBetween);
		map.put("listBDBetween",listBDBetween);

		map.put("listSHTotal",listSHTotal);
		map.put("listQLTotal",listQLTotal);
		map.put("listKFTotal",listKFTotal);
		map.put("listLLTotal",listLLTotal);
		map.put("listCLTotal",listCLTotal);
		map.put("listFNTotal",listFNTotal);
		map.put("listHGTotal",listHGTotal);
		map.put("listBDTotal",listBDTotal);
		map.put("listHGTotalBetween",listHGTotalBetween);
		map.put("listFNTotalBetween",listFNTotalBetween);
		map.put("listCLTotalBetween",listCLTotalBetween);
		map.put("listLLTotalBetween",listLLTotalBetween);
		map.put("listKFTotalBetween",listKFTotalBetween);
		map.put("listQLTotalBetween",listQLTotalBetween);
		map.put("listSHTotalBetween",listSHTotalBetween);
		map.put("listBDTotalBetween",listBDTotalBetween);
		return map;
	}



	/**
	 * 设定时间区间
	 * 上周六 到这周五
	 */
	public String getBetweenTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -7);
		Date dateNew = c.getTime();
		String preDay = sdf.format(dateNew);
		return preDay;
	}
	
}

package com.edgedo.sys.controller;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.sys.entity.ErpChangeNetCounMonth;
import com.edgedo.sys.entity.ErpChangeNetRec;
import com.edgedo.sys.queryvo.ErpChangeNetCounMonthQuery;
import com.edgedo.sys.queryvo.ErpChangeNetRecQuery;
import com.edgedo.sys.service.ErpChangeNetRecService;
import com.edgedo.sys.service.SysConfigService;
import com.edgedo.timedtask.UpdateChangeNetRecIn;
import com.edgedo.timedtask.UpdateChangeNetRecOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;


@Api(tags = "ErpChangeNetRec")
@Controller
@RequestMapping("/admin/erpChangeNetRec")
public class ErpChangeNetRecController extends BaseController{
	
	@Autowired
	private ErpChangeNetRecService service;
	@Autowired
	private SysConfigService sysConfigService;

	@Autowired
	private UpdateChangeNetRecIn updateChangeNetRecIn;

	@Autowired
	private UpdateChangeNetRecOut updateChangeNetRecOut;

	String month;
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpChangeNetRec", notes = "分页查询ErpChangeNetRec")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpChangeNetRecQuery query){
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
	@ApiOperation(value = "新增修改ErpChangeNetRec", notes = "新增修改ErpChangeNetRec")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpChangeNetRec voObj){
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
	@ApiOperation(value = "根据ID批量删除ErpChangeNetRec", notes = "根据ID批量删除ErpChangeNetRec")
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
	 * @returnn
	 */
	@ApiOperation(value = "根据ID加载ErpChangeNetRec", notes = "根据ID加载ErpChangeNetRec")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}



	/**
	 * 月数统计
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listCountMonth",method = RequestMethod.POST)
	public ModelAndView  listCountMonth(ErpChangeNetCounMonthQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getQueryObj().getChangeNetType() == null && query.getQueryObj().getCountData()==null){
			query.getQueryObj().setChangeNetType("转出");
			Calendar a=Calendar.getInstance();
			String year  = String.valueOf(a.get(Calendar.YEAR));
			int montInt = a.get(Calendar.MONTH)+1;
			if(montInt>10){
				month = String.valueOf(montInt-1);
			}else if(montInt<10&&montInt!=1){
				month = "0"+String.valueOf(montInt-1);
			}else if(montInt==1){
				month = "0"+String.valueOf(montInt);
			}
			query.getQueryObj().setCountData(year+"-"+month);
		}
		service.listCountMonth(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 更新转网信息
	 */
	@RequestMapping(value = "/updateChangeNetRec",method = RequestMethod.POST)
	public ModelAndView  updateCarInfo(){
		ModelAndView modelAndView = new ModelAndView();
		//先进行判断  是否已在更新
		String flag = sysConfigService.getValueById("UPDATE_NOTONLINE_CAR_INFO_FLAG");
		if(flag.equals("1")){//正在更新
			modelAndView.addObject("success" ,false);
			//和前端配合0
			modelAndView.addObject("code","0");
			return modelAndView;
		}else {
			String err = updateChangeNetRecIn.dynamicLoop("开始更新");
			if(err.equals("请重新登录")){
				return buildErrorResponse(modelAndView,err);
			}
			err = updateChangeNetRecOut.dynamicLoopin("开始更新");
			if(err.equals("请重新登录")){
				return buildErrorResponse(modelAndView,err);
			}
			return buildResponse(modelAndView);
		}
	}

	//查询是否处于正在更新状态中
	@RequestMapping(value = "/checkIsUpdating",method = RequestMethod.POST)
	public ModelAndView checkIsUpdating(){
		ModelAndView modelAndView = new ModelAndView();
		//转网记录的标识
		String flag = sysConfigService.getValueById("UPDATE_NOTONLINE_CAR_INFO_FLAG");
		if(flag == null){
			flag = "1";
		}
		return buildResponse(modelAndView,flag);
	}


}

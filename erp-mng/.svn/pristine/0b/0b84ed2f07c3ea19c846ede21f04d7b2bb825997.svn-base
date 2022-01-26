package com.edgedo.sys.controller;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.sys.entity.ErpChangeNetSumMonth;
import com.edgedo.sys.queryvo.ErpChangeNetSumMonthQuery;
import com.edgedo.sys.service.ErpChangeNetSumMonthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "ErpChangeNetSumMonth")
@Controller
@RequestMapping("/admin/erpChangeNetSumMonth")
public class ErpChangeNetSumMonthController extends BaseController{
	
	@Autowired
	private ErpChangeNetSumMonthService service;

	String month;
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpChangeNetSumMonth", notes = "分页查询ErpChangeNetSumMonth")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpChangeNetSumMonthQuery query){
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
	@ApiOperation(value = "新增修改ErpChangeNetSumMonth", notes = "新增修改ErpChangeNetSumMonth")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpChangeNetSumMonth voObj){
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
	@ApiOperation(value = "根据ID批量删除ErpChangeNetSumMonth", notes = "根据ID批量删除ErpChangeNetSumMonth")
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
	@ApiOperation(value = "根据ID加载ErpChangeNetSumMonth", notes = "根据ID加载ErpChangeNetSumMonth")
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
	public ModelAndView  listCountMonth(ErpChangeNetSumMonthQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getQueryObj().getChangeNetType() == null && query.getQueryObj().getCountData()==null){
			query.getQueryObj().setChangeNetType("转出");
			Calendar a=Calendar.getInstance();
			String year  = String.valueOf(a.get(Calendar.YEAR));
			int montInt = a.get(Calendar.MONTH)+1;
			if(montInt>10){
				month = String.valueOf(montInt-1);
			}else if(montInt<=10&&montInt!=1){
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
	
}

package com.edgedo.sys.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.sys.entity.ErpGpsTerminal;
import com.edgedo.sys.entity.ErpSupplier;
import com.edgedo.sys.queryvo.*;
import com.edgedo.sys.service.ErpGpsTerminalService;
import com.edgedo.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.management.Query;
import javax.websocket.Session;


@Api(tags = "ErpGpsTerminal")
@Controller
@RequestMapping("/admin/erpGpsTerminal")
public class ErpGpsTerminalController extends BaseController{
	
	@Autowired
	private ErpGpsTerminalService service;

	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpGpsTerminal", notes = "分页查询ErpGpsTerminal")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpGpsTerminalQuery query){
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
	@ApiOperation(value = "新增修改ErpGpsTerminal", notes = "新增修改ErpGpsTerminal")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpGpsTerminal voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			voObj.setCreateTime(new Date());
			String ids= service.selectSupplierId(voObj.getSupplierName());
			voObj.setSupplierId(ids);
			errMsg = service.insert(voObj);
		}else{
			voObj.setCreateTime(new Date());

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
	 * 查找供应商名称
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/selectBySupplierName",method = RequestMethod.POST)
	public ModelAndView selectBySupplierName(){
		ModelAndView modelAndView = new ModelAndView();
		List<ErpSupplierView> list = service.selectSupplierName();
		buildResponse(modelAndView,list);
		return modelAndView;
	}

	/**
	 * 查找占用人名称
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/listAllUserType",method = RequestMethod.POST)

	public ModelAndView listAllUserType(SysUserQuery query){
		ModelAndView modelAndView = new ModelAndView();
		sysUserService.selectAllType(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除ErpGpsTerminal", notes = "根据ID批量删除ErpGpsTerminal")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
	public ModelAndView delete(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		service.deleteByDataState(list);
		return buildResponse(modelAndView);
	}
	
	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载ErpGpsTerminal", notes = "根据ID加载ErpGpsTerminal")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}
	
	
}

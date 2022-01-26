package com.edgedo.sys.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import com.edgedo.common.base.BaseController;
import com.edgedo.sys.entity.ErpSupplier;
import com.edgedo.sys.queryvo.ErpSupplierQuery;
import com.edgedo.sys.queryvo.ErpSupplierView;
import com.edgedo.sys.service.ErpSupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "ErpSupplier")
@Controller
@RequestMapping("/admin/erpSupplier")
public class ErpSupplierController extends BaseController {

	@Autowired
	private ErpSupplierService service;

	/**
	 * 分页查询
	 *
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpSupplier", notes = "分页查询ErpSupplier")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage", method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpSupplierQuery query) {
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
	@ApiOperation(value = "新增修改ErpSupplier", notes = "新增修改ErpSupplier")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpSupplier voObj){
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
	@ApiOperation(value = "根据ID批量删除ErpSupplier", notes = "根据ID批量删除ErpSupplier")
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
	@ApiOperation(value = "根据ID加载ErpSupplier", notes = "根据ID加载ErpSupplier")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 查找供应商名
	 * @param
	 * @return
	 */
	@ApiOperation(value = "查找供应商名", notes = "查找供应商名")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listSimSupplierName",method = RequestMethod.POST)
	public ModelAndView  listSimSupplierName(){
		ModelAndView modelAndView = new ModelAndView();
		List<ErpSupplier> suppliers = service.selectSupplierName();
		return buildResponse(modelAndView,suppliers);
	}
}

package com.edgedo.sys.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.sys.entity.ErpCarSimChangeRec;
import com.edgedo.sys.entity.ErpSupplier;
import com.edgedo.sys.queryvo.ErpCarSimChangeRecQuery;
import com.edgedo.sys.queryvo.ErpCarSimChangeRecView;
import com.edgedo.sys.service.ErpCarSimChangeRecService;
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


@Api(tags = "ErpCarSimChangeRec")
@Controller
@RequestMapping("/admin/erpCarSimChangeRec")
public class ErpCarSimChangeRecController extends BaseController{
	
	@Autowired
	private ErpCarSimChangeRecService service;

	@Autowired
	private ErpSupplierService erpSupplierService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpCarSimChangeRec", notes = "分页查询ErpCarSimChangeRec")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpCarSimChangeRecQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	@RequestMapping(value = "/changeSimRecExcelOn",method = RequestMethod.POST)
	public ModelAndView changeSimRecExcelOn() {
		ModelAndView modelAndView = new ModelAndView();
		ErpCarSimChangeRecQuery query = new ErpCarSimChangeRecQuery();
		List<ErpCarSimChangeRecView> list = service.listAll(query);
		//1.查询所有车辆信息
		return buildResponse(modelAndView, list);
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改ErpCarSimChangeRec", notes = "新增修改ErpCarSimChangeRec")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpCarSimChangeRec voObj){
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
	@ApiOperation(value = "根据ID批量删除ErpCarSimChangeRec", notes = "根据ID批量删除ErpCarSimChangeRec")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
	public ModelAndView delete(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		//判断是否进行多个删除还是单个删除
		if(arr.length==1){//删除一个
			service.deleteByIdsOne(arr[0]);
		}else{
			List<String> list = new ArrayList<String>();
			for(String str : arr){
				list.add(str);
			}
			service.deleteByIds(list);
		}
		return buildResponse(modelAndView);
	}
	
	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载ErpCarSimChangeRec", notes = "根据ID加载ErpCarSimChangeRec")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/**
	 * 查询所有SIM类型的供应商
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/listNewSimOperator",method = RequestMethod.POST)
	public ModelAndView listNewSimOperator(){
		ModelAndView modelAndView = new ModelAndView();
		List<ErpSupplier> list = erpSupplierService.selectNewSimOperator();
		buildResponse(modelAndView,list);
		return modelAndView;
	}

	
}

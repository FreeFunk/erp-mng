package com.edgedo.sys.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.sys.entity.ErpUserWorkLog;
import com.edgedo.sys.queryvo.ErpUserWorkLogQuery;
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


@Api(tags = "ErpUserWorkLog")
@Controller
@RequestMapping("/admin/erpUserWorkLog")
public class ErpUserWorkLogController extends BaseController{
	
	@Autowired
	private ErpUserWorkLogService service;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpUserWorkLog", notes = "分页查询ErpUserWorkLog")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpUserWorkLogQuery query){
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
	@ApiOperation(value = "新增修改ErpUserWorkLog", notes = "新增修改ErpUserWorkLog")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpUserWorkLog voObj){
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
	@ApiOperation(value = "根据ID批量删除ErpUserWorkLog", notes = "根据ID批量删除ErpUserWorkLog")
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
	@ApiOperation(value = "根据ID加载ErpUserWorkLog", notes = "根据ID加载ErpUserWorkLog")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 根据id修改审核状态
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/adoptVo",method = RequestMethod.POST)
	public ModelAndView  adoptVo(String ids){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		service.updateByShengHe(ids,user.getUserId(),user.getUserName(),new Date());
		return buildResponse(modelAndView);
	}

	/**
	 * 根据id修改审核状态
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/noAdoptVo",method = RequestMethod.POST)
	public ModelAndView  noAdoptVo(String ids){
		ModelAndView modelAndView = new ModelAndView();
		service.updateByShengHeId(ids,new Date());
		return buildResponse(modelAndView);
	}
}

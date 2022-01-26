package com.edgedo.sys.controller;


import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.sys.entity.ErpInstructionsForm;
import com.edgedo.sys.entity.ErpIntoForm;
import com.edgedo.sys.queryvo.ErpInstructionsFormQuery;
import com.edgedo.sys.queryvo.ErpIntoFormQuery;
import com.edgedo.sys.service.ErpIntoFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "ErpIntoForm")
@Controller
@RequestMapping("/erp/erpintoform/erpIntoForm")
public class ErpIntoFormController extends BaseController{
	
	@Autowired
	private ErpIntoFormService service;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpIntoForm", notes = "分页查询ErpIntoForm")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpIntoFormQuery query){
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
	@ApiOperation(value = "新增修改ErpIntoForm", notes = "新增修改ErpIntoForm")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpIntoForm voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			User user = getLoginUser();
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setDataState("1");
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
	@ApiOperation(value = "根据ID批量删除ErpIntoForm", notes = "根据ID批量删除ErpIntoForm")
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
	@ApiOperation(value = "根据ID加载ErpIntoForm", notes = "根据ID加载ErpIntoForm")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	@ResponseBody
	@RequestMapping(value = "/outExcel",method = RequestMethod.POST)
	public ModelAndView outExcel(@ModelAttribute ErpIntoFormQuery query) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> mapQuery = new HashMap<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		mapQuery.put("orgCarPlateNum",query.getQueryObj().getOrgCarPlateNum());
		mapQuery.put("newCarPlateNum",query.getQueryObj().getNewCarPlateNum());
		Map<String, List> map = new HashMap<>();
		List<ErpIntoForm> list = service.selectAllCarInfo(mapQuery);
		map.put("list", list);
		return buildResponse(modelAndView, map);
	}

	/**
	 * 导入excel表格
	 */
	@ResponseBody
	@RequestMapping(value = "/intoExcel",method = RequestMethod.POST)
	public Map<String,Object> intoExcel(@RequestParam("file") MultipartFile file){
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
			User user = getLoginUser();
			int i = service.batchImport(name, file,user);
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

	
}

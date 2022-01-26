package com.edgedo.sys.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edgedo.common.base.BaseController;
import com.edgedo.sys.entity.ErpSimXufeiRec;
import com.edgedo.sys.queryvo.ErpSimXufeiRecQuery;
import com.edgedo.sys.service.ErpSimXufeiRecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "ErpSimXufeiRec")
@Controller
@RequestMapping("/admin/erpSimXufeiRec")
public class ErpSimXufeiRecController extends BaseController{
	
	@Autowired
	private ErpSimXufeiRecService service;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpSimXufeiRec", notes = "分页查询ErpSimXufeiRec")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpSimXufeiRecQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getQueryObj().getSelectTime()!=null && !query.getQueryObj().getSelectTime().equals("")){
			String dateQuJian = query.getQueryObj().getSelectTime();
			String dateQuJianStr = dateQuJian.replace(" ","");//去掉空格
			String[] arr = dateQuJianStr.split("-");//2019 08 16 2019 08 20
			String selectTime = arr[0]+"-"+arr[1]+"-"+arr[2];
			String newEndTime = arr[3]+"-"+arr[4]+"-"+arr[5];
			query.getQueryObj().setSelectTime(selectTime);//开始时间
			query.getQueryObj().setNewEndTime(newEndTime);//结束时间
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改ErpSimXufeiRec", notes = "新增修改ErpSimXufeiRec")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpSimXufeiRec voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			errMsg = service.insert(voObj);
			Map<String,Boolean> map = new HashMap<String,Boolean>();
			if(errMsg.equals("SIM卡不存在,请查看是否填写正确!")){
				map.put("err",true);
				modelAndView.addObject("data",map);
				modelAndView.addObject("success" ,false );
				modelAndView.addObject("code","0");
				return modelAndView;
			}else if (errMsg.equals("该SIM卡未被使用,请先去激活该SIM卡号")){
				map.put("err1",true);
				modelAndView.addObject("data" ,map);
				modelAndView.addObject("success" ,false );
				modelAndView.addObject("code","0");
				return modelAndView;
			}
			buildResponse(modelAndView);
		}else{
			errMsg = service.update(voObj);
			buildResponse(modelAndView);
		}
		return modelAndView;
	}
	
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除ErpSimXufeiRec", notes = "根据ID批量删除ErpSimXufeiRec")
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
	@ApiOperation(value = "根据ID加载ErpSimXufeiRec", notes = "根据ID加载ErpSimXufeiRec")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}
	
	
}

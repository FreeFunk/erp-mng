package com.edgedo.sys.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.sys.entity.ErpCarSimpleInfo;
import com.edgedo.sys.entity.ErpCarXufeiRec;
import com.edgedo.sys.entity.ErpSimXufeiRec;
import com.edgedo.sys.queryvo.ErpCarInfoQuery;
import com.edgedo.sys.queryvo.ErpCarXufeiRecQuery;
import com.edgedo.sys.service.ErpCarXufeiRecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "ErpCarXufeiRec")
@Controller
@RequestMapping("/admin/erpCarXufeiRec")
public class ErpCarXufeiRecController extends BaseController{
	
	@Autowired
	private ErpCarXufeiRecService service;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpCarXufeiRec", notes = "分页查询ErpCarXufeiRec")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpCarXufeiRecQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy()==null || query.getOrderBy().equals("")){
			query.setOrderBy("CREATE_TIME DESC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}



	@RequestMapping(value = "/carInfoExcel",method = RequestMethod.POST)
	public ModelAndView carInfoExcel(@ModelAttribute ErpCarXufeiRecQuery query) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> mapQuery = new HashMap<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			mapQuery.put("carPlateNum",query.getQueryObj().getCarPlateNum());
			mapQuery.put("channelAgentName",query.getQueryObj().getChannelAgentName());

			if(query.getQueryObj().getXufeiTimeStartStr().equals("")){
				mapQuery.put("xufeiTimeStart",null);
			}else {
				mapQuery.put("xufeiTimeStart",simpleDateFormat.parse(query.getQueryObj().getXufeiTimeStartStr()));
			}
			if(query.getQueryObj().getXufeiTimeEndStr().equals("")){
				mapQuery.put("xufeiTimeEnd",null);
			}else {
				mapQuery.put("xufeiTimeEnd",simpleDateFormat.parse(query.getQueryObj().getXufeiTimeEndStr()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<String, List> map = new HashMap<>();
		//1.查询所有车辆信息
		List<ErpCarXufeiRec> list = service.selectAllCarInfo(mapQuery);
		map.put("list", list);
		return buildResponse(modelAndView, map);
	}


	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改ErpCarXufeiRec", notes = "新增修改ErpCarXufeiRec")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpCarXufeiRec voObj){
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
	@ApiOperation(value = "根据ID批量删除ErpCarXufeiRec", notes = "根据ID批量删除ErpCarXufeiRec")
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
	@ApiOperation(value = "根据ID加载ErpCarXufeiRec", notes = "根据ID加载ErpCarXufeiRec")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 续费弹窗
	 * @param erpCarXufeiRec
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateNew",method = RequestMethod.POST)
	public ModelAndView  saveOrUpdateNew(ErpCarXufeiRec erpCarXufeiRec){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		erpCarXufeiRec.setCreateUserId(user.getUserId());
		erpCarXufeiRec.setCreateUserName(user.getUserName());
//		erpCarXufeiRec.setServiceStartTime(new Date());//服务开始时间
		service.updateNew(erpCarXufeiRec);
		return buildResponse(modelAndView);
	}

	
	
}

package com.edgedo.sys.controller;


import java.io.IOException;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.HttpRequestUtil;
import com.edgedo.sys.entity.ErpOutputuserTaskWork;
import com.edgedo.sys.entity.ErpWorkTaskOrder;
import com.edgedo.sys.entity.ErpWorkTaskOrderUser;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.queryvo.ErpCarSimChangeRecView;
import com.edgedo.sys.queryvo.ErpWorkTaskOrderQuery;
import com.edgedo.sys.queryvo.ErpWorkTaskOrderView;
import com.edgedo.sys.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Api(tags = "ErpWorkTaskOrder")
@Controller
@RequestMapping("/admin/erpWorkTaskOrder")
public class ErpWorkTaskOrderController extends BaseController{
	
	@Autowired
	private ErpWorkTaskOrderService service;

	@Autowired
	private ErpWorkTaskService erpWorkTaskService;

	@Autowired
	private ErpWorkTaskOrderUserService erpWorkTaskOrderUserService;

	@Autowired
	private ErpOutputuserTaskWorkService erpOutputuserTaskWorkService;

	ErpWorkTaskOrderView erpWorkTaskOrder;
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpWorkTaskOrder", notes = "分页查询ErpWorkTaskOrder")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpWorkTaskOrderQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//判断当前操作人只能是外勤人员
		User userConfirm = getLoginUser();//获取当前的确定名字和id
		if(userConfirm.getDefaultRole().equals("FIELD")){
			//获取当前外勤人员的id
			List<ErpWorkTaskOrderView> list = erpWorkTaskOrderUserService.selectByUserId(userConfirm.getUserId());
			service.listPage(list,query);
			buildResponse(modelAndView,query);
			return modelAndView;
		}
		buildResponse(modelAndView);
		return modelAndView;
	}

	/**
	 * 查询一个记录
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryOneOrder",method = RequestMethod.POST)
	public ModelAndView queryOneOrder(String ids){
		ModelAndView modelAndView = new ModelAndView();
		erpWorkTaskOrder = service.selectByOrderId(ids);
		buildResponse(modelAndView);
		return modelAndView;
	}

	/**
	 * 获取参数
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/orderOneInfo",method = RequestMethod.POST)
	public ModelAndView orderOneInfo(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		//进行渲染
		return  buildResponse(modelAndView,erpWorkTaskOrder);
	}


	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改ErpWorkTaskOrder", notes = "新增修改ErpWorkTaskOrder")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpWorkTaskOrder voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		User user = getLoginUser();
		//获取转出人姓名
		voObj.setOutUserId(user.getUserId());
		voObj.setOutUserName(user.getUserName());
		if(id==null || id.trim().equals("")){
//			User user = getLoginUser();
//			voObj.setConfirmUserName(user.getUserName());
//			voObj.setConfirmUserId(user.getUserId());
//			voObj.setConfirmTime(new Date());
			errMsg = service.insert(voObj);
		}else{
			if(voObj.getOutUserName().equals("")){
				errMsg = service.update(voObj);
			}else {
				errMsg = service.updateByOutUserName(voObj);
			}
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
	@ApiOperation(value = "根据ID批量删除ErpWorkTaskOrder", notes = "根据ID批量删除ErpWorkTaskOrder")
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
	@ApiOperation(value = "根据ID加载ErpWorkTaskOrder", notes = "根据ID加载ErpWorkTaskOrder")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/**
	 * 点击确认派单
	 * 修改派单状态为进行中
	 * 工单状态已确认
	 * 进行判断是否填写工作人员
	 */
	@RequestMapping(value = "/updateTaskOrder",method = RequestMethod.POST)
	public ModelAndView updateTaskOrder(String ids){
		//			 	* 点击确认派单
		ModelAndView modelAndView = new ModelAndView();
		//1.查询该工单是否已经存在工作人员
		ErpWorkTaskOrder erpWorkTaskOrder = service.selectByIdsOnWorkUser(ids);
		//		* 修改派单状态为进行中
		String state = "2";
		erpWorkTaskService.updateByTaskState(state,erpWorkTaskOrder.getOwnerWorkTaskId());
//				* 工单状态已确认  修改确认时间 确定当前的确定人
		String stateOrder = "1";
		User userConfirm = getLoginUser();//获取当前的确定名字和id
		erpWorkTaskOrder.setTaskOrderState(stateOrder);
		erpWorkTaskOrder.setIsOverWork("0");//一开始无加班
		erpWorkTaskOrder.setConfirmTime(new Date());
		erpWorkTaskOrder.setConfirmUserId(userConfirm.getUserId());
		erpWorkTaskOrder.setConfirmUserName(userConfirm.getUserName());
		service.updateByTaskState(erpWorkTaskOrder);
		//创建员工工单表  -- 根据当前的工单表的id对应的外勤人员
		//erpWorkTaskOrderUserService.insertUser(ids);
		buildResponse(modelAndView);
		return modelAndView;
	}


	/**
	 * 完成派单点击
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/updateCompleteWork",method = RequestMethod.POST)
	public ModelAndView updateCompleteWork(String ids){
		ModelAndView modelAndView = new ModelAndView();
		//1.获取当前人的用户名-完成人
		User userCompleteWork = getLoginUser();
		//2.查询id
		ErpWorkTaskOrder erpWorkTaskOrder = service.selectByIdsOnWorkUser(ids);
		//3.当前完成时间
		erpWorkTaskOrder.setFinishedTime(new Date());
		//4.完成人 id 名字
		erpWorkTaskOrder.setFinishedUserId(userCompleteWork.getUserId());
		erpWorkTaskOrder.setFinishedUserName(userCompleteWork.getUserName());
		service.selectByWorkTaskTime(erpWorkTaskOrder);
		buildResponse(modelAndView);

		return modelAndView;
	}

	/**
	 * 加班记录
	 * @param
	 * @return
	 */
	@ApiOperation(value = "新增修改ErpWorkTaskOrder", notes = "新增修改ErpWorkTaskOrder")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/jiaBanOrder",method = RequestMethod.POST)
	public ModelAndView jiaBanOrder(ErpWorkTaskOrder voObj){
		ModelAndView modelAndView = new ModelAndView();
		//1.更新工单表  加班任务
		ErpWorkTaskOrder erpWorkTaskOrder = service.selectByIdsOnWorkUser(voObj.getId());
		erpWorkTaskOrder.setIsOverWork("1");//加班  是1
		String desc = erpWorkTaskOrder.getOverWorkDesc();//加班描述
		Integer overTime = erpWorkTaskOrder.getOverWorkTime();//时长
		//最新加班时长
		if(overTime==null){
			Integer newOverTime = voObj.getOverWorkTime();
			erpWorkTaskOrder.setOverWorkTime(newOverTime);
		}else {
			Integer newOverTime = overTime+voObj.getOverWorkTime();
			erpWorkTaskOrder.setOverWorkTime(newOverTime);
		}

		//2.可能有多个加班任务，之间加一个逗号
		if(desc==null){
			String newDesc = voObj.getOverWorkDesc();
			erpWorkTaskOrder.setOverWorkDesc(newDesc);
		}else {
			String newDesc = desc+","+voObj.getOverWorkDesc();
			erpWorkTaskOrder.setOverWorkDesc(newDesc);
		}
		service.updateByOverWork(erpWorkTaskOrder);//更新数据库
		buildResponse(modelAndView);
		return modelAndView;
	}


	/**
	 * 展示业务人员  安装人员 ---- 外勤
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/listInput",method = RequestMethod.POST)
	public ModelAndView listInput(){
		ModelAndView modelAndView = new ModelAndView();
		List<SysUser> list = service.selectByInput();
		buildResponse(modelAndView,list);
		return modelAndView;
	}

	/**
	 * 确认内勤人员
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/addInnerUserName",method = RequestMethod.POST)
	public ModelAndView addInnerUserName(ErpWorkTaskOrder erpWorkTaskOrder){
		ModelAndView modelAndView = new ModelAndView();
		//切割字符串
		String[] inputNameArr = erpWorkTaskOrder.getInnerUserName().split("@");
		service.updateByIntput(erpWorkTaskOrder.getId(),inputNameArr[0],inputNameArr[1]);
		buildResponse(modelAndView);
		return modelAndView;
	}





}

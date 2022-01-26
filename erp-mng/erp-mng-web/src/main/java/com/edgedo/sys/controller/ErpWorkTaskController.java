package com.edgedo.sys.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.sys.entity.ErpWorkTask;
import com.edgedo.sys.entity.ErpWorkTaskOrder;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.queryvo.ErpWorkTaskOrderView;
import com.edgedo.sys.queryvo.ErpWorkTaskQuery;
import com.edgedo.sys.queryvo.SysUserView;
import com.edgedo.sys.service.ErpOutputuserTaskWorkService;
import com.edgedo.sys.service.ErpWorkTaskOrderService;
import com.edgedo.sys.service.ErpWorkTaskOrderUserService;
import com.edgedo.sys.service.ErpWorkTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Api(tags = "ErpWorkTask")
@Controller
@RequestMapping("/admin/erpWorkTask")
public class ErpWorkTaskController extends BaseController{
	
	@Autowired
	private ErpWorkTaskService service;

	@Autowired
	private ErpOutputuserTaskWorkService erpOutputuserTaskWorkService;

	@Autowired
	private ErpWorkTaskOrderUserService erpWorkTaskOrderUserService;

	@Autowired
	private ErpWorkTaskOrderService erpWorkTaskOrderService;

	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpWorkTask", notes = "分页查询ErpWorkTask")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpWorkTaskQuery query){
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
	@ApiOperation(value = "新增修改ErpWorkTask", notes = "新增修改ErpWorkTask")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpWorkTask voObj){
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
	 * 点击派单添加外勤人员
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/addUserOutPut",method = RequestMethod.POST)
	public ModelAndView addUserOutPut(ErpWorkTask voObj){
		ModelAndView modelAndView = new ModelAndView();
		//获取到完成人  获取指定的外勤人员
		String waiQinName = voObj.getFinishedUserName();
		//更新工单表的外勤人员
		//User userConfirm = getLoginUser();//获取当前的确定名字和id
		ErpWorkTask erpWorkTask1 = service.selectByIds(voObj.getId());
		//3.同时创建员工工单任务表
		erpWorkTaskOrderService.insertByErpWorkTask(erpWorkTask1);
		erpWorkTaskOrderService.updateByOutPutUserName(waiQinName,voObj.getId());
		//更新关联表
		//任务id
		//外勤人员id
		//erpOutputuserTaskWorkService.insertByNewOne(voObj);
		erpWorkTaskOrderUserService.insertByNewOne(voObj);
		//1.点击触发派单按扭
		String state = "1";
		//2.修改任务状态
		service.updateByIdsTaskState(voObj.getId(),state);
		buildResponse(modelAndView);
		return modelAndView;
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除ErpWorkTask", notes = "根据ID批量删除ErpWorkTask")
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
	@ApiOperation(value = "根据ID加载ErpWorkTask", notes = "根据ID加载ErpWorkTask")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 点击派单按钮  ，修改当前id对应的任务表 的任务状态  同时创建一个任务工单表
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/updateTaskState",method = RequestMethod.POST)
	public ModelAndView updateTaskState(String ids){
		ModelAndView modelAndView = new ModelAndView();
		ErpWorkTask erpWorkTask1 = service.selectByIds(ids);
		//3.同时创建员工工单任务表
		erpWorkTaskOrderService.insertByErpWorkTask(erpWorkTask1);
		buildResponse(modelAndView);

		return modelAndView;
	}


	/**
	 * 展示业务人员  安装人员 ---- 外勤
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/listAllYeWu",method = RequestMethod.POST)
	public ModelAndView listAllYeWu(String ids){
		ModelAndView modelAndView = new ModelAndView();
		List<SysUser> list = service.selectByYeWu();
		//依据id查询工作单表是否存在 外勤人员
		String waiQinId = erpWorkTaskOrderService.selectByIdWork(ids);
		if(waiQinId==null){
			buildResponse(modelAndView,list);
			return modelAndView;
		}else {
			//切割
			String[] waiQinIdArr = waiQinId.split(",");
			for(String id : waiQinIdArr){
				for(int i = 0;i<list.size();i++){
					if(list.get(i).getId().equals(id)){
						list.remove(i);
					}else {
						continue;
					}
				}
			}
			buildResponse(modelAndView,list);
			return modelAndView;
		}
	}



	/**
	 * 派单完成
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/updateCompleteWork",method = RequestMethod.POST)
	public ModelAndView updateCompleteWork(String ids){
		ModelAndView modelAndView = new ModelAndView();
		//1.获取当前操作人id  名字
		User userCompleteWork = getLoginUser();
		ErpWorkTask erpWorkTask = new ErpWorkTask();
		erpWorkTask.setId(ids);//主键
		erpWorkTask.setFinishedUserId(userCompleteWork.getUserId());
		erpWorkTask.setFinishedUserName(userCompleteWork.getUserName());
		erpWorkTask.setFinishedTime(new Date());
		service.updateByWorkTaskTime(erpWorkTask);
		//service.selectByIds
		buildResponse(modelAndView);

		return modelAndView;
	}

	/**
	 * 通过任务ID查找任务工单
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryOneTask",method = RequestMethod.POST)
	public ModelAndView queryOneTask(String id){
		ModelAndView modelAndView = new ModelAndView();

		List<ErpWorkTaskOrderView> erpWorkTaskOrderList= erpWorkTaskOrderService.selectByWorkId(id);
		if(erpWorkTaskOrderList.size()==1){
			buildResponse(modelAndView,erpWorkTaskOrderList);
			return modelAndView;
		}else{
			return modelAndView;
		}

	}
	
}

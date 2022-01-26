package com.edgedo.sys.controller;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.sys.entity.*;
import com.edgedo.sys.queryvo.ErpNotnolineCarInfoQuery;
import com.edgedo.sys.queryvo.ErpUnlocationRemindMsgRecQuery;
import com.edgedo.sys.service.*;
import com.edgedo.timedtask.UpdateNotNoLineCarInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Api(tags = "ErpNotnolineCarInfo")
@Controller
@RequestMapping("/admin/erpNotnolineCarInfo")
public class ErpNotnolineCarInfoController extends BaseController{


	@Autowired
	private ErpNotnolineCarInfoService service;

	@Autowired
	private UpdateNotNoLineCarInfo updateNotNoLineCarInfo;

	@Autowired
	private SysConfigService sysConfigService;


	@Autowired
	private ErpUnlocationRemindMsgRecService erpUnlocationRemindMsgRecService;

	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpNotnolineCarInfo", notes = "分页查询ErpNotnolineCarInfo")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpNotnolineCarInfoQuery query){
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
	@ApiOperation(value = "新增修改ErpNotnolineCarInfo", notes = "新增修改ErpNotnolineCarInfo")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpNotnolineCarInfo voObj){
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
	 * 单个用户发送短信
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/sendOneMessages",method = RequestMethod.POST)
	public ModelAndView sendOneMessages(String carPlateNum,String id,String contactPerson,String contactPhoneNum){
		ModelAndView modelAndView = new ModelAndView();
		//1.将接收的属性分装对象
		if(contactPhoneNum!=null && !contactPhoneNum.equals("")){
			ErpUnlocationRemindMsgRec erpUnlocationRemindMsgRec = new ErpUnlocationRemindMsgRec();
			erpUnlocationRemindMsgRec.setCarPlateNum(carPlateNum);//车牌号
			erpUnlocationRemindMsgRec.setOwnerUnlocationRemindId(id);//所属未定位id
			erpUnlocationRemindMsgRec.setCreateTime(new Date());//创建时间
			String msg = "【轩易行平台】您的车"+carPlateNum+"目前处于离线状态,出车时请及时检修,以免被查罚款,北斗电话:15133532012。";
			erpUnlocationRemindMsgRec.setMsgText(msg);//短信内容
			erpUnlocationRemindMsgRec.setContactPerson(contactPerson);//联系人
			erpUnlocationRemindMsgRec.setContactPhoneNum(contactPhoneNum);//联系电话
			erpUnlocationRemindMsgRec.setSendState("0");
			erpUnlocationRemindMsgRec.setDataState("1");
			erpUnlocationRemindMsgRec.setSendType("OFFLINE_TZ_MSG");
			erpUnlocationRemindMsgRecService.insert(erpUnlocationRemindMsgRec);
		}
		return buildResponse(modelAndView);
	}
	/*public ModelAndView sendOneMessages(String carPlateNum,String id,String contactPerson,String contactPhoneNum){
		ModelAndView modelAndView = new ModelAndView();
		//1.将接收的属性分装对象
		ErpUnlocationRemindMsgRec erpUnlocationRemindMsgRec = new ErpUnlocationRemindMsgRec();
		erpUnlocationRemindMsgRec.setCarPlateNum(carPlateNum);//车牌号
		erpUnlocationRemindMsgRec.setId(Guid.guid());//主键
		erpUnlocationRemindMsgRec.setOwnerUnlocationRemindId(id);//所属未定位id
		erpUnlocationRemindMsgRec.setCreateTime(new Date());//创建时间
		//erpUnlocationRemindMsgRec.setSendType("0");//单发  --0   群发---1
		String msg = "【轩易行平台】"+carPlateNum+"已离线,请及时检修,以免被罚款,电话:15133532012";

		erpUnlocationRemindMsgRec.setMsgText(msg);//短信内容
		//erpUnlocationRemindMsgRec
		erpUnlocationRemindMsgRec.setContactPerson(contactPerson);//联系人
		erpUnlocationRemindMsgRec.setContactPhoneNum(contactPhoneNum);//联系电话
		erpUnlocationRemindMsgRec.setSendState("0");
		//erpUnlocationRemindMsgRec.setSendTime(new Date());//发送时间
		erpUnlocationRemindMsgRec.setDataState("1");
		//2.调用发送短信方法  单个  将联系电话 联系人  车牌号
		//3.上升services
		erpUnlocationRemindMsgRecMapper.insert(erpUnlocationRemindMsgRec);
		erpUnlocationRemindMsgRecService.sendOneMessages(erpUnlocationRemindMsgRec);
		//4.并记录当前用户已经发过信息
		return buildResponse(modelAndView);
	}*/

	/**
	 * 多个用户发送短信  0 2已发送
	 * 多选
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/sendManyMessages",method = RequestMethod.POST)
	public ModelAndView sendManyMessages(String ids){
		ModelAndView modelAndView = new ModelAndView();
		//1.多条id 按逗号切割
		String[] arr = ids.split(",");
		List<String> listId = new ArrayList<String>();
		for(String str : arr){
			listId.add(str);
		}
		//2.根据唯一id 查询多个未上线车辆
		List<ErpNotnolineCarInfo> list = service.selectByListId(listId);
		//3.将多个要发的短信用户插入一个新表中
		erpUnlocationRemindMsgRecService.insertSendManyMessageNew(list);
		return buildResponse(modelAndView);
	}

	/**
	 * 所有用户发送短信  1 2已发送
	 * 7 30
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/sendAllMessages",method = RequestMethod.POST)
	public ModelAndView sendAllMessages(String unlocatonDay){
		ModelAndView modelAndView = new ModelAndView();
		//1.先从数据库中为冀CunlocatonDay未上线的车辆
		List<ErpNotnolineCarInfo> list = service.selectByAllInfo(unlocatonDay);//冀c  1000
		//2.将所有的发短信信息  插入新表
		erpUnlocationRemindMsgRecService.insertSendManyMessageNew(list);
		//3.依据定时任务将所有信息一次发送
		return buildResponse(modelAndView);
	}




	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除ErpNotnolineCarInfo", notes = "根据ID批量删除ErpNotnolineCarInfo")
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
	@ApiOperation(value = "根据ID加载ErpNotnolineCarInfo", notes = "根据ID加载ErpNotnolineCarInfo")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/**
	 * 更新车辆信息
	 */
	@RequestMapping(value = "/updateCarInfo",method = RequestMethod.POST)
	public ModelAndView  updateCarInfo(){
		ModelAndView modelAndView = new ModelAndView();
		//先进行判断  是否已在更新
		String flag = sysConfigService.getValueById("UPDATE_NOTONLINE_CAR_INFO_FLAG");
		if(flag.equals("1")){//正在更新
			modelAndView.addObject("success" ,false);
			//和前端配合0
			modelAndView.addObject("code","0");
			return modelAndView;
		}else {
			String err = updateNotNoLineCarInfo.insertNotLineCar();
			//String err = updateNotNoLineCarInfo.dynamicLoop();
			if(err.equals("请重新登录")){
				return buildErrorResponse(modelAndView,err);
			}
			return buildResponse(modelAndView);
		}

	}

	/**
	 * 查询未上线车辆是否正在更新中
	 * @return
	 */
	@RequestMapping(value = "/checkIsUpdating",method = RequestMethod.POST)
	public ModelAndView checkIsUpdating(){
		ModelAndView modelAndView = new ModelAndView();
		String flag = sysConfigService.getValueById("UPDATE_NOTONLINE_CAR_INFO_FLAG");
		if(flag == null){
			flag = "1";
		}
		return buildResponse(modelAndView,flag);
	}


	@RequestMapping(value = "/erpNotnolineCarSendMessageInfo",method = RequestMethod.POST)
	public ModelAndView  erpNotnolineCarSendMessageInfo(String ownerUnlocationRemindId){
		ModelAndView modelAndView = new ModelAndView();
		//1.查询最新的发送记录
		ErpUnlocationRemindMsgRec erpUnlocationRemindMsgRec = service.selectBySendMessageId(ownerUnlocationRemindId);
		//2.传到map对象
		Map<String,ErpUnlocationRemindMsgRec> map = new HashMap<>();
		map.put("ErpUnlocationRemindMsgRec",erpUnlocationRemindMsgRec);
		return buildResponse(modelAndView,map);
	}


	@RequestMapping(value = "/sendListInfo",method = RequestMethod.POST)
	public ModelAndView  sendListInfo(String ids, ErpUnlocationRemindMsgRecQuery erpUnlocationRemindMsgRecQuery){
		ModelAndView modelAndView = new ModelAndView();
		//1.查询车辆信息的所有记录
		List<ErpUnlocationRemindMsgRec> list = erpUnlocationRemindMsgRecService.selectByCarId(ids);
		//2.传到页面
		erpUnlocationRemindMsgRecQuery.setList(list);
		buildResponse(modelAndView,erpUnlocationRemindMsgRecQuery);
		return modelAndView;
	}

	/**
	 * 接收短信落地状态
	 * @param request
	 */
	@RequestMapping(value = "/sendMessageState")
	@Pass
	public void sendMessageState(HttpServletRequest request){
		//解析Jsonss
		InputStream is = null;//接收流
		String reqBody=null;
		try {
			is = request.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line=reader.readLine())!=null){
				sb.append(line);
			}
			reqBody = sb.toString();
			System.out.println(reqBody);
			erpUnlocationRemindMsgRecService.judgeSmsStatus(reqBody);
			//获取到的信息进行匹配
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

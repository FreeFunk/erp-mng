package com.edgedo.sys.controller;

import java.io.*;
import java.util.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.common.sequrity.CheckPermission;
import com.edgedo.common.shiro.JwtRsaSecKey;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.*;
import com.edgedo.sys.entity.*;
import com.edgedo.sys.queryvo.*;
import com.edgedo.sys.service.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/sysUser")
public class SysUserController extends BaseController {//
	@Autowired
	JwtRsaSecKey secKey;
	@Autowired
	private SysUserService service;
	
	@Autowired
	private SysPowerService sysPowerService;
	
	@Autowired
	private SysOrgLevelRoleService sysOrgLevelRoleService ;

	@Autowired
	private SysMenueService sysMenueService ;
	@Autowired
	private SysXianquService xianquService;
	@Autowired
	private SysCityService cityService;
	@Autowired
	private SysProviceService proviceService;
	@Autowired
	private SysConfigService sysConfigService;
	/**
	 * 根据登录用户初始化主页
	 *
	 * @return
	 */
	@RequestMapping("/loadCurrUser")
	public ModelAndView loadCurrUser(HttpServletRequest request){

		ModelAndView modelAndView = new ModelAndView();
		try {
			User shiroUser = getLoginUser();
			return buildResponse(modelAndView,shiroUser);
		} catch (Exception e) {
			return buildErrorResponse(modelAndView,"获取用户信息错误");
		}
	}

	/**
	 * 加载当前登录用户的信息携带着省份县市
	 *
	 * @return
	 */
	@RequestMapping("/loadCurrUserWithArea")
	public ModelAndView loadCurrUserWithArea(){

		ModelAndView modelAndView = new ModelAndView();
		try {
			Map<String,Object> result = new HashMap<String,Object>();
			User shiroUser = getLoginUser();
			String xianquId = shiroUser.getXianquId();

			if(xianquId!=null){
				SysXianqu xianqu = xianquService.loadById(xianquId);
				if(xianqu!=null){
					result.put("xianquId",xianqu.getId());
					result.put("xianquName",xianqu.getName());
					result.put("cityId",xianqu.getCityId());
					result.put("cityName",xianqu.getCityName());
					result.put("provinceId",xianqu.getProvinceId());
					result.put("provinceName",xianqu.getProvinceName());
				}

			}else{
				String cityId = shiroUser.getCityId();
				if(cityId != null){
					SysCity city = cityService.loadById(cityId);
					if(city != null){
						result.put("cityId",city.getId());
						result.put("cityName",city.getName());
						result.put("provinceId",city.getProvinceId());
						result.put("provinceName",city.getProvinceNane());
					}
				}else {
					String provincId = shiroUser.getProvinceId();
					if(provincId!=null){
						SysProvice provice = proviceService.loadById(provincId);
						if(provice != null){
							result.put("provinceId",provice.getId());
							result.put("provinceName",provice.getName());
						}
					}
				}
			}
			return buildResponse(modelAndView,result);
		} catch (Exception e) {
			return buildErrorResponse(modelAndView,"获取用户信息错误");
		}
	}

	/**
	 * 根据登录用户初始化主页
	 *
	 * @return
	 */
	@RequestMapping("/findHome")
	public ModelAndView findHome(){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String defaultRoleId = user.getDefaultRole();
		String homePath = service.findHomeId(defaultRoleId);
		buildResponse(modelAndView,homePath);
		return modelAndView;
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@RequestMapping("/listpage")
	public ModelAndView listpage(@ModelAttribute SysUserQuery query){
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
	@RequestMapping("/saveOrUpdate")
	public ModelAndView saveOrUpdate(SysUser voObj){
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
	@RequestMapping("/deleteByIds")
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
	 * 批量删除
	 * @return
	 */
	@RequestMapping("/jugeUserUpPwd")
	public ModelAndView jugeUserUpPwd(){
		ModelAndView modelAndView = new ModelAndView();
		SysUser user = null;//AdminUserLoginIntercepter.getCurrentUser();
		String userId = user.getId();
		SysUser dbUser = service.loadById(userId);
		String isUpPwd = dbUser.getIsUpPwd();
		return buildResponse(modelAndView,isUpPwd);
	}

	
	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@RequestMapping("/loadById")
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}
	
	/**
	 *  管理员重置用户密码
	 * @param userId 密码
	 * @return
	 */
	@RequestMapping("/resetPwd")
	@ResponseBody
	public ModelAndView resetPwd(@RequestParam String userId){
		ModelAndView modelAndView = new ModelAndView();
		SysUser user = null;//AdminUserLoginIntercepter.getCurrentUser();
		if(user.getId().equals(userId)){
			buildErrorResponse(modelAndView,"请使用修改密码,修改自己的密码!");
			return modelAndView;
		}
		service.updateResetPwd(userId);
		//ptOpLoger.addLog(PtUserOpLogService.UPDATE, "PtUser", user.getId(), "系统用户修改自己密码", getIpAddr(), getDiviceType());
		return buildResponse(modelAndView);
	}
	
	/***
	 * 用户登录的方法
	 * @param userCode
	 * @param password
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	@Pass
	public ModelAndView login(@RequestParam(required=true) String userCode,@RequestParam(required=true) String password,
							 // @RequestParam(required=true) String imgCode,@RequestParam(required=true) String imgCode2,
							  final HttpSession session,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		//登录全国货运平台
		/*String loginErrMsg = loginGghypt(imgCode,imgCode2,session);
		if(loginErrMsg!=null && !loginErrMsg.equals("")){
			buildErrorResponse(modelAndView, loginErrMsg);
			return modelAndView;
		}*/
		final SysUserView user = service.getAdminUserByCode(userCode);
		if(user==null){
			buildErrorResponse(modelAndView, "用户不存在");
			return modelAndView;
		}
		String userState = user.getUserState();
		if(userState==null || userState.equals("LOCK")){
			buildErrorResponse(modelAndView, "用户已被锁定！");
			return modelAndView;
		}
		String pwd = MD5.encode(MD5.encode(password)+user.getId());
		if(!pwd.equals(user.getPassword())){
			buildErrorResponse(modelAndView, "密码错误！");
			return modelAndView;
		}
		//设置未读站内信的数量
		Integer num = service.getMsgNum(user);
		session.setAttribute("msg",num);
		//修改用户登录状态和本次登录时间
		String ipAddress = getIpAddr();
		final String[] token = new String[1];
		service.updateLoginStateLogin(user, ipAddress, new CallBackInterface() {
			@Override
			public void callBack() {
				User shiroUser = new User();
				shiroUser.setUserId(user.getId());
				shiroUser.setUserName(user.getUserName());
				shiroUser.setProvinceId(user.getProvinceId());
				shiroUser.setCityId(user.getCityId());
				shiroUser.setXianquId(user.getXianquId());
				shiroUser.setDefaultRole(user.getDefaultRoleId());


				//角色首页
				String defaultRoleId = user.getDefaultRoleId();
				if(defaultRoleId!=null && defaultRoleId.length()>0){
					SysOrgLevelRole role = sysOrgLevelRoleService.loadById(defaultRoleId);
//					if(role != null){
//						shiroUser.setHomePage(role.getRoleHomePagePath());
//						shiroUser.setHomePageTitle(role.getRoleHomePage());
//					}
				}
				//获得所有角色
//				List<SysOrgLevelRoleView> roles = sysOrgLevelRoleService.selectAllUserRolesByUserId(shiroUser.getUserId());
//				String[] roleStrs = new String[roles.size()];
//				int i =0;
//				for(SysOrgLevelRoleView roleObj :roles ){
//					roleStrs[i++] = roleObj.getId();
//				}
//				shiroUser.setUserRoles(roleStrs);
				String userInfo = JSON.toJSONString(shiroUser);
				//将字符串使用RSA加密
				try {
					String userInfoRsa = RSAUtil.encode(userInfo,secKey);
					token[0] = JWTUtil.sign(userInfoRsa,secKey.getJwtSecretkey());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		//操作日志
		buildResponse(modelAndView,token[0]);

		return modelAndView;
	}
	//登录全国货运平台
	private String loginGghypt(String imgCode, String imgCode2,final HttpSession session) {
		//获取登录地址
		String gghyptLoginUrl = sysConfigService.getValueById("GGHYPT_LOGIN_URL");
		//获取第一个登录账号和密码
		String userName1 = sysConfigService.getValueById("USER_NAME_1");
		String password1 = sysConfigService.getValueById("PASSWORD_1");
		Map<String,String> params1 = new HashedMap();
		params1.put("userName",userName1);
		params1.put("requestParam.equal.opLoginname",userName1);
		params1.put("requestParam.equal.opPass",password1);
		params1.put("imgCode",imgCode);
		String cookies = (String)session.getAttribute("imgcookie");
		Map<String,String> header1  =new HashMap<>();
		header1.put("Cookie",cookies);
		Map<String,String> result1  =new HashMap<>();
		result1 = HttpRequestUtil.sendPostRequestHeader(gghyptLoginUrl,params1,header1);
		String msg1 = result1.get("msg");
		Map<String,String> map1  =new HashMap<>();
		map1 = ObjectUtil.jsonToMap(msg1);
		if(map1.size()==1){
			return "验证码错误";
		}else {
			String flag = map1.get("flag");
			if(flag!=null && flag.equals("true")){
				//更新cookie
				String COOKIE_USERID_CAR = result1.get("cookies");
				SysConfig sysConfig1 = new SysConfig();
				sysConfig1.setId("COOKIE_USERID_CAR");
				sysConfig1.setValue(COOKIE_USERID_CAR);
				sysConfig1.setUpdateTime(new Date());
				sysConfigService.update(sysConfig1);
			}else {
				return "登录失败！";
			}
		}
		//获取第二个登录账号和密码
		String userName2 = sysConfigService.getValueById("USER_NAME_2");
		String password2 = sysConfigService.getValueById("PASSWORD_2");
		Map<String,String> params2 = new HashedMap();
		params2.put("userName",userName2);
		params2.put("requestParam.equal.opLoginname",userName2);
		params2.put("requestParam.equal.opPass",password2);
		params2.put("imgCode",imgCode2);
		String cookies2 = (String)session.getAttribute("imgcookie2");
		Map<String,String> header2  =new HashMap<>();
		header2.put("Cookie",cookies2);
		Map<String,String> result2  =new HashMap<>();
		result2 = HttpRequestUtil.sendPostRequestHeader(gghyptLoginUrl,params2,header2);
		String msg2 = result2.get("msg");
		Map<String,String> map2  =new HashMap<>();
		map2 = ObjectUtil.jsonToMap(msg2);
		if(map2.size()==1){
			return "验证码错误";
		}else {
			String flag = map2.get("flag");
			if(flag!=null && flag.equals("true")){
				//更新cookie
				String COOKIE_USERID_HD = result2.get("cookies");
				SysConfig sysConfig2 = new SysConfig();
				sysConfig2.setId("COOKIE_USERID_HD");
				sysConfig2.setValue(COOKIE_USERID_HD);
				sysConfig2.setUpdateTime(new Date());
				sysConfigService.update(sysConfig2);
			}else {
				return "登录失败！";
			}
		}
		return "";
	}

	//更新大平台车辆数据
	@RequestMapping("/updateErpCarSimpleInfo")
	@ResponseBody
	@Pass
	public ModelAndView updateErpCarSimpleInfo(@RequestParam(required=true) String imgCode,final HttpSession session,HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		//获取登录地址
		String gghyptLoginUrl = sysConfigService.getValueById("GGHYPT_LOGIN_URL");
		//获取账号和密码
		String userName = sysConfigService.getValueById("USER_NAME_2");
		String password = sysConfigService.getValueById("PASSWORD_2");
		Map<String,String> params = new HashedMap();
		params.put("userName",userName);
		params.put("requestParam.equal.opLoginname",userName);
		params.put("requestParam.equal.opPass",password);
		params.put("imgCode",imgCode);
		String cookies2 = (String)session.getAttribute("imgcookie2");
		Map<String,String> header  =new HashMap<>();
		header.put("Cookie",cookies2);
		Map<String,String> result  =new HashMap<>();
		result = HttpRequestUtil.sendPostRequestHeader(gghyptLoginUrl,params,header);
		String msg = result.get("msg");
		Map<String,String> map  =new HashMap<>();
		map = ObjectUtil.jsonToMap(msg);
		if(map.size()==1){
			buildErrorResponse(modelAndView,"验证码错误");
			return modelAndView;
		}else {
			String flag = map.get("flag");
			if(flag!=null && flag.equals("true")){
				//更新cookie
				String COOKIE_USERID_HD = result.get("cookies");
				SysConfig sysConfig = new SysConfig();
				sysConfig.setId("COOKIE_USERID_HD");
				sysConfig.setValue(COOKIE_USERID_HD);
				sysConfig.setUpdateTime(new Date());
				sysConfigService.update(sysConfig);
			}else {
				buildErrorResponse(modelAndView,"登录失败！");
				return modelAndView;
			}
		}
		return buildResponse(modelAndView);
	}

	//获取第一个平台需要的验证码
	@RequestMapping("/updateErpNotOnlineCarInfo")
	@ResponseBody
	@Pass
	public ModelAndView updateErpNotOnlineCarInfo(@RequestParam(required=true) String imgCode,final HttpSession session,HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		//获取登录地址
		String gghyptLoginUrl = sysConfigService.getValueById("GGHYPT_LOGIN_URL");
		//获取登录账号和密码
		String userName = sysConfigService.getValueById("USER_NAME_1");
		String password = sysConfigService.getValueById("PASSWORD_1");
		Map<String,String> params = new HashedMap();
		params.put("userName",userName);
		params.put("requestParam.equal.opLoginname",userName);
		params.put("requestParam.equal.opPass",password);
		params.put("imgCode",imgCode);
		String cookies = (String)session.getAttribute("imgcookie");
		Map<String,String> header  =new HashMap<>();
		header.put("Cookie",cookies);
		Map<String,String> result  =new HashMap<>();
		result = HttpRequestUtil.sendPostRequestHeader(gghyptLoginUrl,params,header);
		String msg1 = result.get("msg");
		Map<String,String> map1  =new HashMap<>();
		map1 = ObjectUtil.jsonToMap(msg1);
		if(map1.size()==1){
			return buildErrorResponse(modelAndView,"验证码错误");
		}else {
			String flag = map1.get("flag");
			if(flag!=null && flag.equals("true")){
				//更新cookie
				String COOKIE_USERID_CAR = result.get("cookies");
				SysConfig sysConfig = new SysConfig();
				sysConfig.setId("COOKIE_USERID_CAR");
				sysConfig.setValue(COOKIE_USERID_CAR);
				sysConfig.setUpdateTime(new Date());
				sysConfigService.update(sysConfig);
			}else {
				return buildErrorResponse(modelAndView,"登录失败！");
			}
		}
		return buildResponse(modelAndView);
	}

	@RequestMapping("/getImageCode")
	@ResponseBody
	@Pass
	public ModelAndView getImageCode(final HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String url = "https://www.gghypt.net/sysbasic/rondamImage.action?requestParam.equal.randomType=index" + "&d" + new Date().getTime();
		try {
			Map<String, String> result = new HashMap<String, String>();
			result = HttpRequestUtil.sendPostRequestStream1(url,response.getOutputStream());
			session.setAttribute("imgcookie",result.get("cookies"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		buildResponse(modelAndView,"");
		return modelAndView;

	}

	@RequestMapping("/getImageCode2")
	@ResponseBody
	@Pass
	public ModelAndView getImageCode2(final HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String url = "https://www.gghypt.net/sysbasic/rondamImage.action?requestParam.equal.randomType=index" + "&d" + new Date().getTime();
		try {
			Map<String, String> result = new HashMap<String, String>();
			result = HttpRequestUtil.sendPostRequestStream1(url,response.getOutputStream());
			session.setAttribute("imgcookie2",result.get("cookies"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		buildResponse(modelAndView,"");
		return modelAndView;

	}

	@RequestMapping("/loadUserMenueAll")
	@ResponseBody
//	@CheckPermission("sss")
	public ModelAndView loadUserMenueAll(HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		List<SysMenueViewSyn> menueList = new ArrayList<SysMenueViewSyn>();
		//从数据库中读取用户菜单
		User user = User.getCurrentUser();
		List<SysMenueView> list = sysMenueService.getUserMenuByPidAndUserid(null, user.getUserId());
		Map<String,List<SysMenueViewSyn>> temMap = new HashMap<String,List<SysMenueViewSyn>>();
		menueList  = new ArrayList<SysMenueViewSyn>();
		for(SysMenueView menue : list){
			String parentId = menue.getParentId();
			if(parentId!=null && parentId.equals("ROOT")){
				menueList.add(SysMenueViewSyn.genSysMenueViewSyn(menue));
			}else{
				List<SysMenueViewSyn> listChild = temMap.get(parentId);
				if(listChild==null){
					listChild = new ArrayList<SysMenueViewSyn>();
				}
				listChild.add(SysMenueViewSyn.genSysMenueViewSyn(menue));
				temMap.put(parentId,listChild);
			}
		}
		recursive(menueList,temMap);
		buildResponse(modelAndView,menueList);
		return modelAndView;
	}
	
	/**
	 * 递归组装菜单树
	 * @param menueList
	 * @param temMap
	 */
	public void recursive(List<SysMenueViewSyn> menueList, Map<String,List<SysMenueViewSyn>> temMap){
		if(menueList==null){
			return;
		}
		for(SysMenueViewSyn pmv : menueList){
			String id = pmv.getId();
			pmv.setLabel();
			List<SysMenueViewSyn> children = temMap.get(id);
			if(children != null){
				pmv.setChildren(children);
			}
			temMap.remove(id);
			recursive(children,temMap);
		}

	}
	
	/**
	 * 用户访问菜单数据
	 * @return
	 */
	@RequestMapping("/nav")
	public ModelAndView nav(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("tpl/blocks/nav");
		return modelAndView;
	}
	
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public ModelAndView logout(HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		SysUser user = null;//AdminUserLoginIntercepter.getCurrentUser();

		return buildResponse(modelAndView);
	}
	
	/**
	 * 用户修改密码
	 * @param pwd 密码
	 * @return
	 */
	@RequestMapping("/updatePwd")
	@ResponseBody
	public ModelAndView updatePwd(@RequestParam String oldpwd,String pwd){
		ModelAndView modelAndView = new ModelAndView();
		SysUser user = null;//AdminUserLoginIntercepter.getCurrentUser();
		SysUserView thisUser = service.getAdminUserByCode(user.getUserCode());
		oldpwd = MD5.encode(MD5.encode(oldpwd)+user.getId());
		if(oldpwd.equals(thisUser.getPassword())) {
			/*pwd = MD5.encode(MD5.encode(pwd)+user.getId());*/
			service.updateUserPwd(user.getId(), pwd);
			buildResponse(modelAndView);
		}else {
			buildErrorResponse(modelAndView, "原密码错误！");
		}
		//ptOpLoger.addLog(PtUserOpLogService.UPDATE, "PtUser", user.getId(), "系统用户修改自己密码", getIpAddr(), getDiviceType());
		return modelAndView;
	}

	//测试未上线车辆登录状态
	@RequestMapping("/checkLoginStateCAR")
	@ResponseBody
	public ModelAndView checkLoginStateCAR(){
		ModelAndView modelAndView = new ModelAndView();
		String changeNetUrl = sysConfigService.getValueById("CHANGE_NET_URL_2");
		String cookieUseridCar = sysConfigService.getValueById("COOKIE_USERID_CAR");
		Map<String,String> params = new HashMap<>();
		params.put("requestParam.equal.platformId","70859928-7aae-4842-b67a-60f2b7ff8f77");
		params.put("requestParam.equal.createTimeBegin","1561910401000");
		params.put("requestParam.equal.createTimeEnd","1564588799000");
		params.put("requestParam.page","1");
		params.put("requestParam.rows","1");
		params.put("requestParam.like.vehicleNo","冀C");
		Map<String,String> header = new HashMap<>();
		header.put("Cookie",cookieUseridCar);
		String s = "";
		String toTal = "";
		try {
			s = HttpRequestUtil.sendPostRequest(changeNetUrl,params,header);
			if(s != null && (s.equals("") || s.indexOf("账号异地登录") != -1)){
				return buildResponse(modelAndView,"未登录状态");
			}else{
				//总数
				JSONObject jsonObject = new JSONObject(s);
				toTal = jsonObject.optString("Total");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buildResponse(modelAndView,toTal);
	}

	//测试大平台车辆登录状态
	@RequestMapping("/checkLoginStateHD")
	@ResponseBody
	public ModelAndView checkLoginStateHD(){
		ModelAndView modelAndView = new ModelAndView();
		String changeNetUrl = sysConfigService.getValueById("SELECT_QGHYPT_CAR_INFO");
		String cookieUseridCar = sysConfigService.getValueById("COOKIE_USERID_HD");
		Map<String,String> params = new HashMap<>();
		//拿到20条数据
		//requestParam.page的value值可变  要想一次插入100条数据 要让这个动态加上1
		params.put("requestParam.equal.areaCode","130000");
		params.put("requestParam.equal.cityId","130300");
		params.put("requestParam.equal.orgCode","130300");
		params.put("requestParam.equal.orgLevel","2");
		params.put("requestParam.page","1");
		params.put("requestParam.rows","20");
		params.put("sortname","vehicleNo");
		params.put("sortorder","asc");
		Map<String,String> header = new HashMap<>();
		header.put("Cookie",cookieUseridCar);
		String s = "";
		String toTal = "";
		try {
			s = HttpRequestUtil.sendPostRequest(changeNetUrl,params,header);
			if(s != null && (s.equals("") || s.indexOf("账号异地登录") != -1)){
				return buildResponse(modelAndView,"未登录状态");
			}else{
				//总数
				JSONObject jsonObject = new JSONObject(s);
				toTal = jsonObject.optString("Total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buildResponse(modelAndView,toTal);
	}

}

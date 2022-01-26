package com.edgedo.sys.controller;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.base.annotation.Pass;
import com.edgedo.common.util.HttpRequestUtil;
import com.edgedo.common.util.ObjectUtil;
import com.edgedo.sys.entity.SysConfig;
import com.edgedo.sys.service.SysConfigService;
import io.swagger.annotations.Api;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName loginController
 * @Description TODO
 * @Author 床前明月光
 * @Date 2019/7/20 9:38
 **/
@Api(tags = "ErpCarInfo")
@Controller
@RequestMapping("/admin/Login")
public class loginController extends BaseController {

    @Autowired
    SysConfigService sysConfigService;

    /*
    * 获取登录的验证码
    * */
    @RequestMapping("/getImageCode")
    @ResponseBody
    @Pass
    public ModelAndView getImageCode(final HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

    /*
    * 获取登录的验证码
    * */
    @RequestMapping("/getImageCode2")
    @ResponseBody
    @Pass
    public ModelAndView getImageCode2(final HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

    //测试未上线车辆登录状态
    @RequestMapping("/checkLoginStateCAR")
    @ResponseBody
    public ModelAndView checkLoginStateCAR(){
        ModelAndView modelAndView = new ModelAndView();
        String changeNetUrl = sysConfigService.getValueById("NOTONLINE_CAR_INFO");
        String cookieUseridCar = sysConfigService.getValueById("COOKIE_USERID_CAR");
        String params = "requestParam.equal.type=1"+"&requestParam.page=1"
                +"&requestParam.rows=100"+"&sortname=sysutc"+"&sortorder=asc";
        Map<String,String> header = new HashMap<>();
        header.put("Cookie",cookieUseridCar);
        String s = "";
        String toTal = "";
        try {
            s = HttpRequestUtil.sendGetRequestHeader(changeNetUrl,params,header);
            if(s != null && (s.equals("") || s.indexOf("账号异地登录") != -1)){
                return buildResponse(modelAndView,"未登录状态");
            }else{
                //总数
                JSONObject jsonObject = new JSONObject(s);
                toTal = jsonObject.optString("Total");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return buildResponse(modelAndView,"未登录状态");
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
        String params = "requestParam.equal.areaCode=130000"+"&requestParam.equal.cityId=130300"+"&requestParam.equal.orgCode=130300"
                +"&requestParam.equal.orgLevel=2"+"&requestParam.page=1"+"&requestParam.rows=20"+"&sortname=vehicleNo"+"&sortorder=asc";
        Map<String,String> header = new HashMap<>();
        header.put("Cookie",cookieUseridCar);
        String s = "";
        String toTal = "";
        try {
            s = HttpRequestUtil.sendGetRequestHeader(changeNetUrl,params,header);
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


    //获取第一个平台需要的验证码
    @RequestMapping("/updateErpNotOnlineCarInfo")
    @ResponseBody
    @Pass
    public ModelAndView updateErpNotOnlineCarInfo(@RequestParam(required=true) String imgCode, final HttpSession session, HttpServletRequest request) throws Exception{
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
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(msg1);
        String error = jsonObject.getString("error");
        if(error!=null){
            return buildErrorResponse(modelAndView,"验证码错误");
        }
        String flag = jsonObject.getString("flag");
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
        return buildResponse(modelAndView);
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
        String msg1 = result.get("msg");
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(msg1);
        String error = jsonObject.getString("error");
        if(error!=null){
            return buildErrorResponse(modelAndView,"验证码错误");
        }
        String flag = jsonObject.getString("flag");
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
        return buildResponse(modelAndView);
    }

}

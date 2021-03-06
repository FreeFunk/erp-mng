package com.edgedo.sys.controller;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.FileUtil;
import com.edgedo.sys.entity.ErpCarSimpleInfo;
import com.edgedo.sys.entity.ErpCompCode;
import com.edgedo.sys.queryvo.ErpCarInfoQuery;
import com.edgedo.sys.queryvo.ErpCompCodeQuery;
import com.edgedo.sys.service.ErpCompCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "ErpCompCode")
@Controller
@RequestMapping("/erp/erpcompcode/erpCompCode")
public class ErpCompCodeController extends BaseController{
	
	@Autowired
	private ErpCompCodeService service;

	@Value("${app.temFileForder}")
	private String temFileForder;

	@Value("${app.uploadTemFile}")
	private String uploadTemFile;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询ErpCompCode", notes = "分页查询ErpCompCode")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute ErpCompCodeQuery query){
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
	@ApiOperation(value = "新增修改ErpCompCode", notes = "新增修改ErpCompCode")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(ErpCompCode voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			//信用代码上传
			String imgUrl = voObj.getImageUrl();
			if(imgUrl!=null && !imgUrl.equals("")){
				String filePath = "";
				String rpath = "/gsxydm";
				File file = new File(imgUrl);
				try {
					filePath = rpath+FileUtil.saveFile(file,uploadTemFile+rpath,true);
					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				voObj.setImageUrl(filePath);
			}
			//身份证上传
			String idCardUrl = voObj.getImageIdCardUrl();
			if(idCardUrl!=null && !idCardUrl.equals("")){
				String filePath = "";
				String rpath = "/idcard";
				File file = new File(idCardUrl);
				try {
					filePath = rpath+FileUtil.saveFile(file,uploadTemFile+rpath,true);
					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				voObj.setImageIdCardUrl(filePath);
			}
			User user = getLoginUser();
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setDataState("1");
			errMsg = service.insert(voObj);
		}else{
			//信用代码上传
			String imgUrl = voObj.getImageUrl();
			if(imgUrl!=null && !imgUrl.equals("")){
				String filePath = "";
				String rpath = "/gsxydm";
				File file = new File(imgUrl);
				try {
					filePath = rpath+FileUtil.saveFile(file,uploadTemFile+rpath,true);
					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				voObj.setImageUrl(filePath);
			}
			//身份证上传
			String idCardUrl = voObj.getImageIdCardUrl();
			if(idCardUrl!=null && !idCardUrl.equals("")){
				String filePath = "";
				String rpath = "/idcard";
				File file = new File(imgUrl);
				try {
					filePath = rpath+FileUtil.saveFile(file,uploadTemFile+rpath,true);
					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				voObj.setImageIdCardUrl(filePath);
			}
			errMsg = service.update(voObj);
		}
		if(errMsg!=null && !errMsg.equals("")){
			modelAndView.addObject("code", "0");
			modelAndView.addObject("success", false);
			modelAndView.addObject("errMsg", errMsg);
			return modelAndView;
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
	@ApiOperation(value = "根据ID批量删除ErpCompCode", notes = "根据ID批量删除ErpCompCode")
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
	@ApiOperation(value = "根据ID加载ErpCompCode", notes = "根据ID加载ErpCompCode")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	@ResponseBody
	@RequestMapping(value = "/outExcel",method = RequestMethod.POST)
	public ModelAndView outExcel(@ModelAttribute ErpCompCodeQuery query) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String,Object> mapQuery = new HashMap<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		mapQuery.put("compName",query.getQueryObj().getCompName());
		mapQuery.put("chargePerson",query.getQueryObj().getChargePerson());
		Map<String, List> map = new HashMap<>();
		List<ErpCompCode> list = service.selectAllCarInfo(mapQuery);
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

	/*
	 * 文件上传到临时文件目录
	 * */
	@RequestMapping(value = "/uploadTempFile",method = RequestMethod.POST)
	public ModelAndView uploadTempFile(MultipartFile file){
		ModelAndView modelAndView = new ModelAndView();
		String tempFilePath = "";
		try{
			//保存到临时文件目录
			tempFilePath = FileUtil.saveFile(file,temFileForder,true);
		}catch (Exception e){
			e.printStackTrace();
		}
		return buildResponse(modelAndView,temFileForder+tempFilePath);
	}
	
}

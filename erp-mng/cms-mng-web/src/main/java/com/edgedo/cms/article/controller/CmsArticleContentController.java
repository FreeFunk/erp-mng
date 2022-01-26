package com.edgedo.cms.article.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.cms.article.entity.CmsArticleContent;
import com.edgedo.cms.article.queryvo.CmsArticleContentQuery;
import com.edgedo.cms.article.service.CmsArticleContentService;
import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.Guid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "CmsArticleContent")
@Controller
@RequestMapping("/cms/article/cmsArticleContent")
public class CmsArticleContentController extends BaseController{
	@Value("${app.fileupload.forder}")
	String fileuploadForder;

	@Autowired
	private CmsArticleContentService service;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询CmsArticleContent", notes = "分页查询CmsArticleContent")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute CmsArticleContentQuery query){
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
	@ApiOperation(value = "新增修改CmsArticleContent", notes = "新增修改CmsArticleContent")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(CmsArticleContent voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			voObj.setDataState("1");
			voObj.setCreateTime(new Date());
			String artState = voObj.getArtState();
			if(artState!=null && artState.equals("1")){
				voObj.setPublishTime(new Date());
			}
			errMsg = service.insert(voObj);
		}else{
			voObj.setDataState(null);
			voObj.setArtState(null);
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
	@ApiOperation(value = "根据ID批量删除CmsArticleContent", notes = "根据ID批量删除CmsArticleContent")
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
	@ApiOperation(value = "根据ID加载CmsArticleContent", notes = "根据ID加载CmsArticleContent")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/**
	 * 发布文章
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "发布文章", notes = "发布文章")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/publish",method = RequestMethod.POST)
	public ModelAndView  publish(String id){
		ModelAndView modelAndView = new ModelAndView();
		CmsArticleContent voObj = new CmsArticleContent();
		voObj.setId(id);
		voObj.setArtState("1");
		voObj.setPublishTime(new Date());
		service.update(voObj);
		return buildResponse(modelAndView);
	}

	/**
	 * 发布文章
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "取消发布", notes = "取消发布")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/unpublish",method = RequestMethod.POST)
	public ModelAndView  unpublish(String id){
		ModelAndView modelAndView = new ModelAndView();
		CmsArticleContent voObj = new CmsArticleContent();
		voObj.setId(id);
		voObj.setArtState("0");
		service.update(voObj);
		return buildResponse(modelAndView);
	}

	/**
	 * 根据主键加载
	 * @param upload
	 * @return
	 */
	@ApiOperation(value = "上传图片", notes = "上传图片")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/uploadImgOfCms",method = RequestMethod.POST)
	public ModelAndView  uploadImgOfCms(MultipartFile upload,String ckCsrfToken){
		ModelAndView mav = new ModelAndView();
		String extName = FileUtil.getFileExtend(upload);
		File forder = new File(fileuploadForder);
		File target = new File(forder, Guid.guid()+"." + extName);
		try {
			upload.transferTo(target);
		} catch (IOException e) {
			return buildErrorResponse(mav,"上传失败!");
		}
		mav.addObject("uploaded",1);
		mav.addObject("url","/");

		return mav;

	}

	
	
}

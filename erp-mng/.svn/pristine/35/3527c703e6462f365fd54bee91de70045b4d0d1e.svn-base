package com.edgedo.cms.article.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.cms.article.entity.CmsArticleContent;
import com.edgedo.cms.article.mapper.CmsArticleContentMapper;
import com.edgedo.cms.article.queryvo.CmsArticleContentQuery;
import com.edgedo.cms.article.queryvo.CmsArticleContentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class CmsArticleContentService {
	
	
	@Autowired
	private CmsArticleContentMapper cmsArticleContentMapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<CmsArticleContentView> listPage(CmsArticleContentQuery cmsArticleContentQuery){
		List list = cmsArticleContentMapper.listPage(cmsArticleContentQuery);
		cmsArticleContentQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(CmsArticleContent cmsArticleContent) {
		cmsArticleContent.setId(Guid.guid());
		cmsArticleContentMapper.insert(cmsArticleContent);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(CmsArticleContent cmsArticleContent) {
		cmsArticleContentMapper.updateById(cmsArticleContent);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(CmsArticleContent cmsArticleContent) {
		cmsArticleContentMapper.updateAllColumnById(cmsArticleContent);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return cmsArticleContentMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return cmsArticleContentMapper.deleteBatchIdsLogic(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public CmsArticleContent loadById(String id) {
		return cmsArticleContentMapper.selectById(id);
	}
	

}

package com.edgedo.cms.article.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.cms.article.entity.CmsArticleContent;
import com.edgedo.cms.article.queryvo.CmsArticleContentQuery;
import com.edgedo.cms.article.queryvo.CmsArticleContentView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface CmsArticleContentMapper  extends BaseMapper<CmsArticleContent>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<CmsArticleContentView> listPage(CmsArticleContentQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<CmsArticleContentView> listByObj(CmsArticleContentQuery query);

	/**
	 * @Author WangZhen
	 * @Description 逻辑批量删除
	 * @Date 2020/3/30 15:50
	 **/
    int deleteBatchIdsLogic(List<String> ids);

}
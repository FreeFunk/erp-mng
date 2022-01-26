package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpSimXufeiRec;
import com.edgedo.sys.queryvo.ErpSimXufeiRecQuery;
import com.edgedo.sys.queryvo.ErpSimXufeiRecView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpSimXufeiRecMapper  extends BaseMapper<ErpSimXufeiRec>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpSimXufeiRecView> listPage(ErpSimXufeiRecQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpSimXufeiRecView> listByObj(ErpSimXufeiRecQuery query);

	/**
	 * 删除单个
	 * @param ids
	 */
	void deleteByIdsOne(String ids);

	/**
	 * 删除多个
	 * @param ids
	 * @return
	 */
	int deleteByIdsAll(List<String> ids);

}
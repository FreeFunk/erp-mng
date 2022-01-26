package com.edgedo.sys.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpCarSimpleInfo;
import com.edgedo.sys.entity.ErpCarXufeiRec;
import com.edgedo.sys.queryvo.ErpCarXufeiRecQuery;
import com.edgedo.sys.queryvo.ErpCarXufeiRecView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpCarXufeiRecMapper  extends BaseMapper<ErpCarXufeiRec>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarXufeiRecView> listPage(ErpCarXufeiRecQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarXufeiRecView> listByObj(ErpCarXufeiRecQuery query);

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

    List<ErpCarXufeiRec> selectAllCarInfo(Map<String, Object> mapQuery);
}
package com.edgedo.sys.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpIntoForm;
import com.edgedo.sys.queryvo.ErpIntoFormQuery;
import com.edgedo.sys.queryvo.ErpIntoFormView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpIntoFormMapper  extends BaseMapper<ErpIntoForm>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpIntoFormView> listPage(ErpIntoFormQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpIntoFormView> listByObj(ErpIntoFormQuery query);


    List<ErpIntoForm> selectAllCarInfo(Map<String, Object> mapQuery);

    int updateByIdDelete(List<String> ids);
}
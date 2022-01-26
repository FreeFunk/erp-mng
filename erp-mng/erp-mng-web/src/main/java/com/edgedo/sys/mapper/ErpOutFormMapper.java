package com.edgedo.sys.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpOutForm;
import com.edgedo.sys.queryvo.ErpOutFormQuery;
import com.edgedo.sys.queryvo.ErpOutFormView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpOutFormMapper  extends BaseMapper<ErpOutForm>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpOutFormView> listPage(ErpOutFormQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpOutFormView> listByObj(ErpOutFormQuery query);


    List<ErpOutForm> selectAllCarInfo(Map<String, Object> mapQuery);

    int updateByIdDelete(List<String> ids);
}
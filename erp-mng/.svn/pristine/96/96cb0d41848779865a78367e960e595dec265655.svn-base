package com.edgedo.sys.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpInstructionsForm;
import com.edgedo.sys.queryvo.ErpInstructionsFormQuery;
import com.edgedo.sys.queryvo.ErpInstructionsFormView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpInstructionsFormMapper  extends BaseMapper<ErpInstructionsForm>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpInstructionsFormView> listPage(ErpInstructionsFormQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpInstructionsFormView> listByObj(ErpInstructionsFormQuery query);


    List<ErpInstructionsForm> selectAllCarInfo(Map<String, Object> mapQuery);

    int updateByIdDelete(List<String> ids);
}
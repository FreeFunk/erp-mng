package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpOperator;
import com.edgedo.sys.queryvo.ErpOperatorQuery;
import com.edgedo.sys.queryvo.ErpOperatorView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpOperatorMapper  extends BaseMapper<ErpOperator>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpOperatorView> listPage(ErpOperatorQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpOperatorView> listByObj(ErpOperatorQuery query);


    List selectBybeidouOperator();

	ErpOperator selectByIdOne(String beidouOperator);

    List<ErpOperator> selectByList();

}
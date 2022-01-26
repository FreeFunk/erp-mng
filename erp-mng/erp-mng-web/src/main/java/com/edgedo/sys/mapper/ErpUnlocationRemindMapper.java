package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpUnlocationRemind;
import com.edgedo.sys.queryvo.ErpUnlocationRemindQuery;
import com.edgedo.sys.queryvo.ErpUnlocationRemindView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpUnlocationRemindMapper  extends BaseMapper<ErpUnlocationRemind>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpUnlocationRemindView> listPage(ErpUnlocationRemindQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpUnlocationRemindView> listByObj(ErpUnlocationRemindQuery query);
	
	

}
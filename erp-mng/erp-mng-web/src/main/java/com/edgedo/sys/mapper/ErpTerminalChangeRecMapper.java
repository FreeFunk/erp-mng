package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpTerminalChangeRec;
import com.edgedo.sys.queryvo.ErpTerminalChangeRecQuery;
import com.edgedo.sys.queryvo.ErpTerminalChangeRecView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpTerminalChangeRecMapper  extends BaseMapper<ErpTerminalChangeRec>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpTerminalChangeRecView> listPage(ErpTerminalChangeRecQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpTerminalChangeRecView> listByObj(ErpTerminalChangeRecQuery query);
	
	

}
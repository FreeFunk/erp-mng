package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpAddCarStatistics;
import com.edgedo.sys.queryvo.ErpAddCarStatisticsQuery;
import com.edgedo.sys.queryvo.ErpAddCarStatisticsView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpAddCarStatisticsMapper  extends BaseMapper<ErpAddCarStatistics>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpAddCarStatisticsView> listPage(ErpAddCarStatisticsQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpAddCarStatisticsView> listByObj(ErpAddCarStatisticsQuery query);


    List<ErpAddCarStatistics> selectByCurrDate(String currWeek);

	String selectByTime();

    int selectByFlag(String time);
}
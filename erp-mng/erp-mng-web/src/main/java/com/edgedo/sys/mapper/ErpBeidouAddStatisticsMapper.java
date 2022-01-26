package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpBeidouAddStatistics;
import com.edgedo.sys.queryvo.ErpBeidouAddStatisticsQuery;
import com.edgedo.sys.queryvo.ErpBeidouAddStatisticsView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpBeidouAddStatisticsMapper  extends BaseMapper<ErpBeidouAddStatistics>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpBeidouAddStatisticsView> listPage(ErpBeidouAddStatisticsQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpBeidouAddStatisticsView> listByObj(ErpBeidouAddStatisticsQuery query);


    String selectByNewInfo();

	List<ErpBeidouAddStatistics> selectByNewDate(String newDate);

    int selectByFlag(String time);
}
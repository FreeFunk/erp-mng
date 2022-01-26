package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpChangeNetSumMonth;
import com.edgedo.sys.queryvo.ErpChangeNetSumMonthQuery;
import com.edgedo.sys.queryvo.ErpChangeNetSumMonthView;
import com.edgedo.sys.queryvo.ErpUserWorkLogView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpChangeNetSumMonthMapper  extends BaseMapper<ErpChangeNetSumMonth>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpChangeNetSumMonthView> listPage(ErpChangeNetSumMonthQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpChangeNetSumMonthView> listByObj(ErpChangeNetSumMonthQuery query);


	public List<ErpChangeNetSumMonthView> selectByList(ErpChangeNetSumMonthQuery query);

    List<ErpChangeNetSumMonthView> queryAll(String newdata);
}
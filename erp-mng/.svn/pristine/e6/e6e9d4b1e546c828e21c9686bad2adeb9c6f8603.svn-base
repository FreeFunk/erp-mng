package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpChangeNetRec;
import com.edgedo.sys.queryvo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ErpChangeNetRecMapper  extends BaseMapper<ErpChangeNetRec>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpChangeNetRecView> listPage(ErpChangeNetRecQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpChangeNetRecView> listByObj(ErpChangeNetRecQuery query);

	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	public int deleteByDataState (List<String> ids);


    //ErpChangeNetRec selectByCarFrameNum(String carPlateNum);

	ErpChangeNetRec selectByPlatformID(@Param("platformID") String platformID);


	List<ErpChangeNetRecView> selectAll();

	List<ErpChangeNetRecView> selectByNowPt(ErpChangeNetCounMonthQuery query);

	void deleteRepeat();

	List<ErpChangeNetRecView> selectAlls(String ids);
}
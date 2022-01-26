package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpCarDetailedInfo;
import com.edgedo.sys.queryvo.ErpCarDetailedInfoQuery;
import com.edgedo.sys.queryvo.ErpCarDetailedInfoView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpCarDetailedInfoMapper  extends BaseMapper<ErpCarDetailedInfo>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarDetailedInfoView> listPage(ErpCarDetailedInfoQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarDetailedInfoView> listByObj(ErpCarDetailedInfoQuery query);

	/**
	 * 根据车架号查询
	 * @param carFrameNum
	 * @return
	 */
	int selectByCarNum(String carFrameNum);

	void insertByList(List<ErpCarDetailedInfo> list);

	void updateByList(List<ErpCarDetailedInfo> list);

	List<ErpCarDetailedInfo> selectByFrameNum(List<ErpCarDetailedInfo> list);
}
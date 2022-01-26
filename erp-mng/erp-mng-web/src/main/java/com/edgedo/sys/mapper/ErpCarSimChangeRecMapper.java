package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpCarInfo;
import com.edgedo.sys.entity.ErpCarSimChangeRec;
import com.edgedo.sys.entity.ErpSim;
import com.edgedo.sys.queryvo.ErpCarSimChangeRecQuery;
import com.edgedo.sys.queryvo.ErpCarSimChangeRecView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpCarSimChangeRecMapper  extends BaseMapper<ErpCarSimChangeRec>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarSimChangeRecView> listPage(ErpCarSimChangeRecQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarSimChangeRecView> listByObj(ErpCarSimChangeRecQuery query);


	/**
	 * 查询新卡号
	 * @param newSimNum
	 * @return
	 */
    ErpSim selectByNewSimCode(String newSimNum);

	/**
	 * 查询车辆信息
	 * @param id
	 * @return
	 */
	ErpCarInfo selectByCarNum(String id);

	/**
	 * 删除单个
	 * @param ids
	 */
    void deleteByIdsOne(String ids);

	/**
	 * 删除多个
	 * @param ids
	 * @return
	 */
	int deleteByIdAll(List<String> ids);

	/**
	 * 根据车牌号更新新sim卡号
	 * @param newSimNum
	 * @param carPlateNum
	 */
    void updateBySimNum(String newSimNum, String carPlateNum);
}
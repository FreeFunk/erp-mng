package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.CarSimpleInfoVo;
import com.edgedo.sys.entity.ErpCarSimpleInfo;
import com.edgedo.sys.queryvo.ErpCarSimpleInfoQuery;
import com.edgedo.sys.queryvo.ErpCarSimpleInfoView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpCarSimpleInfoMapper  extends BaseMapper<ErpCarSimpleInfo>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarSimpleInfoView> listPage(ErpCarSimpleInfoQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarSimpleInfoView> listByObj(ErpCarSimpleInfoQuery query);


	/**
	 * 根据车架号查询
	 * @param carFrameNum
	 * @return
	 */
    int selectByCarNum(String carFrameNum);

	/**
	 * 查询当前有多少条数据
	 * @return
	 */
	int selectTotalNum();

	/**
	 * 插入
	 */
    void insertByList(List<ErpCarSimpleInfo> erpCarSimpleInfo);

	/**
	 * 更新
	 * @param list
	 */
	void updateByList(List<ErpCarSimpleInfo> list);

    List<ErpCarSimpleInfo> selectByFrameNum(List<ErpCarSimpleInfo> list);

	/**
	 * 查询总数
	 * @return
	 */
	int selectAll(String betweenDateStr,String currDateStr);

	/**
	 * 先删除
	 * @param list
	 */
    void deteleByAll(List<ErpCarSimpleInfo> list);

	/**
	 * 删除重复
	 */
	void deleteByAllCarFrame();

	/**
	 * 删除前一天的时间
	 * @param currDateStr
	 */
    void deleteByTime(String betweenDateStr,String currDateStr);

	/**
	 * 县区  供应商名 查询
	 * @return
	 */
	int selectBySupplierNameNum(String xianQu,String supplierName);

	/**
	 * 时间区间   县区  供应商名 查询
	 * @param xianQu
	 * @param supplierName
	 * @param betweenDate
	 * @param currDate
	 * @return
	 */
	int selectByRuWangTime(String xianQu, String supplierName, String betweenDate, String currDate);

    int selectByAll();

    ErpCarSimpleInfoView selectByCarPlateNum(String carPlateNum);

	ErpCarSimpleInfo selectByCarFrameNumNew(String carFrameNum);

	void insertAll(List<CarSimpleInfoVo> list);

	void deleteAll();

	int countByXianquId(String xianquId);

    List<ErpCarSimpleInfo> selectAllCarInfo();
}
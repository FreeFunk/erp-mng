package com.edgedo.sys.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.*;
import com.edgedo.sys.queryvo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ErpCarInfoMapper  extends BaseMapper<ErpCarInfo>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarInfoView> listPage(ErpCarInfoQuery query);
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarInfoView> deletelistPage(ErpCarInfoQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarInfoView> listByObj(ErpCarInfoQuery query);


	/**
	 * 动态查询车队名
	 * @param
	 * @return
	 */
    List<ErpCarTeamView> selectCarTeam();

	/**
	 * 展示供应商名
	 */
    List<ErpSupplierView> selectBySupplierName();

	/**
	 * 查询业务人员---外勤
	 * @return
	 */
	List<SysUserView> selectByYeWu();

	/**
	 * 展示录入人员  -- 内勤人员
	 */
	List<SysUserView> selectByInputUser();

	/**
	 * 展示渠道代理
	 * @return
	 */
    List<ErpChannelAgentView> selectChannelAgentName();



	/**
	 * 查选sim卡
	 * @param simCode
	 * @return
	 */
	int selectByErpSimByNum(String simCode);

	/**
	 * 根据id查询车辆信息
	 * @param ids
	 * @return
	 */
	ErpCarInfo selectByIdCarInfo(String ids);

	public void insertByErpSimChangeRec(ErpCarSimChangeRec erpCarInfo);


	/**
	 * 关联的sim卡号一起注销
	 * @param simCode
	 */
	void updateBySimCodeErpSim(String simCode);

	/**
	 * 删除单个
	 * @param ids
	 */
	void deleteByIdsOne(@Param("ids") String ids,
						@Param("deleteRemark")String deleteRemark,
						@Param("deleteTime")Date deleteTime);
	/**
	 * 多个关联的sim卡号一起注销
	 * @param listSim
	 */
	void updateBySimCodeList(List<String> listSim);

	/**
	 * 删除多个
	 * @param ids
	 * @return
	 */
	int deleteByIdsAll(List<String> ids);

	/**
	 * 判断是否在使用
	 * @param simCode
	 * @return
	 */
	String selectByErpSimNum(String simCode);


	/**
	 * 查询车辆关联的sim卡
	 * @param simCode
	 * @return
	 */
	ErpSim selectByErpSimBysimCode(String simCode);


	/**
	 * 更新卡号
	 * @param erpSim
	 */
	void updateBySimCode(ErpSim erpSim);

	/**
	 * 更新sim卡号
	 * @param
	 */
	void updateBySimCodeChange(ErpSim erpSim);

	ErpCarInfoView selectByCarPlateNumCarPlateColor(@Param("carPlateNum") String carPlateNum, @Param("carPlateColor") String carPlateColor);

	/**
	 * 缴费之后更新车辆的上一次缴费时间
	 * @param date
	 */
    void updtaByXuFeiTime(@Param("date")Date date,@Param("carNumId")String carNumId);

	/**
	 * 根据车牌号查询车的信息
	 * @param carPlateNum
	 * @return
	 */
	ErpCarInfo selectByCarNum(String carPlateNum);

	ErpCarInfo selectByCarPlateNum(String carPlateNum);


	List<ErpCarInfo> selectAll();

   void updateBySimNum(Map<String,String> map);

    List<ErpCarSimpleInfo> selectAllCarInfo(Map<String,Object> mapQuery);

	List<ErpCarSimpleInfo> selectAllCarInfoNew(Map<String,Object> mapQuery);

    void updateByTerminal(ErpCarInfo erpCarInfo);

	void selectByCarNums(@Param("simNum")String simNum,@Param("supplierName") String supplierName);

	void updateByTerminalChangshang(@Param("carPlateNum")String carPlateNum, @Param("terminalChangshang")String terminalChangshang);

	void updateBySimCodes(Map<String,String> map);

	void updateByBeidouOperator(@Param("carPlateNum")String carPlateNum,@Param("beidouOperator")String beidouOperator);

	List<ErpCarInfo> selectByCarPlateNumlist(@Param("carPlateNum")String carPlateNum);

	List<ErpCarSimpleInfo> selectAllById(List<String> idList);

	void updateByGetMoneyNum(ErpCarXufeiRec erpCarXufeiRec);

	void updateBySimNumAndSupplierName(Map<String, String> map);

    void insertListErpCarInfo(List<ErpCarInfo> listErpCarInfo);

    void updateByCarInfoId(ErpCarInfo erpCarInfo);

	List<ErpCarInfoView> endTimelistPage(ErpCarInfoQuery query);

    int countSumNum();

	List<ErpCarInfo> selectOrderById(Map<String, Object> map);

	void sendRemindCheckGpsPhoneMsg(Map<String, Object> map);

}
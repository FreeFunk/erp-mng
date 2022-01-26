package com.edgedo.sys.mapper;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpNotnolineCarInfo;
import com.edgedo.sys.entity.ErpUnlocationRemindMsgRec;
import com.edgedo.sys.entity.SysPhoneMsgRec;
import com.edgedo.sys.queryvo.ErpNotnolineCarInfoView;
import com.edgedo.sys.queryvo.ErpUnlocationRemindMsgRecQuery;
import com.edgedo.sys.queryvo.ErpUnlocationRemindMsgRecView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ErpUnlocationRemindMsgRecMapper  extends BaseMapper<ErpUnlocationRemindMsgRec>{

	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpUnlocationRemindMsgRecView> listPage(ErpUnlocationRemindMsgRecQuery query);

	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpUnlocationRemindMsgRecView> listByObj(ErpUnlocationRemindMsgRecQuery query);

	//根据车辆id查询是否存在短信
	ErpUnlocationRemindMsgRec selectByCarId(String ownerUnlocationRemindId);

	/**
	 * 插入多条发送记录
	 * @param list
	 */
	void insertNewSendManyMessage(List<ErpUnlocationRemindMsgRec> list);

	/**
	 * 根据发送状态是否为空
	 * @return
	 */
	List<ErpUnlocationRemindMsgRec> selectByAllNewManyInfo();

	/**
	 * 批量更新
	 * @param listUpdate
	 */
	void updateManyMsg(List<ErpUnlocationRemindMsgRec> listUpdate);

	/**
	 * 插入所有发送记录
	 * @param list
	 */
	void insertNewSendAllMessage(List<ErpUnlocationRemindMsgRec> list);

	/**
	 * 查询所有发送短信记录
	 * @return
	 */
	List<ErpUnlocationRemindMsgRec> selectByAllNewAllInfo();

	/**
	 * 删除多个
	 * @param listDetele
	 */
	void deleteByList(List<ErpUnlocationRemindMsgRec> listDetele);

	/**
	 * 所有车辆短信发送更新记录
	 * @param list
	 */
	void updateAllMessage(List<ErpUnlocationRemindMsgRec> list);

	/**
	 * 未定位车辆被发短信的次数
	 * @param id
	 * @return
	 */
	int selectByCarIdNum(String id);

	/**
	 * 根据状态查询
	 * @param ownerUnlocationRemindId
	 * @return
	 */
	ErpUnlocationRemindMsgRec selectByCarIdState(String ownerUnlocationRemindId);

	ErpUnlocationRemindMsgRec selectBySendMessageId(String ownerUnlocationRemindId);

	List<ErpUnlocationRemindMsgRec> selectByCarIdList(String ids);

	int selectByCarPlateNumDate(String carPlateNum);

	void updateByState(@Param("erpUnlocationRemindMsgRec") ErpUnlocationRemindMsgRec erpUnlocationRemindMsgRec);

	List<ErpUnlocationRemindMsgRec> selectByPhoneNum(String moblie);

	List<ErpUnlocationRemindMsgRec> selectBySendState();

	void updateBySendState(ErpUnlocationRemindMsgRec erpUnlocationRemindMsgRec);

	void updateBySendStateNew(ErpUnlocationRemindMsgRec erpUnlocationRemindMsgRec);

	int selectByCarPlateNum(String carPlateNum);

	int countSendNum(Map<String, Object> map);

	List<ErpUnlocationRemindMsgRec> selectBySendTypeGroupByPhoneNum(String sendType);

	List<ErpUnlocationRemindMsgRec> selectBySendType(String sendType);

    void insertAll(List<ErpNotnolineCarInfo> list);
}
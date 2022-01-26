package com.edgedo.sys.mapper;

import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpCarSimpleInfo;
import com.edgedo.sys.entity.ErpNotnolineCarInfo;
import com.edgedo.sys.entity.ErpNotnolineCarInfoVo;
import com.edgedo.sys.queryvo.ErpNotnolineCarInfoQuery;
import com.edgedo.sys.queryvo.ErpNotnolineCarInfoView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpNotnolineCarInfoMapper  extends BaseMapper<ErpNotnolineCarInfo>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpNotnolineCarInfoView> listPage(ErpNotnolineCarInfoQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpNotnolineCarInfoView> listByObj(ErpNotnolineCarInfoQuery query);


	/**
	 * 根据车牌号和最后定位时间来判断是否有重复
	 * @param arg1
	 * @param arg0
	 * @return
	 */
    int selectByCarNumAndLastTime(String arg1, String arg0);

	/**
	 * 批量插入
	 * @param list
	 */
	void insertByList(List<ErpNotnolineCarInfo> list);

	/**
	 * 批量更新
	 * @param list
	 */
	void updateByList(List<ErpNotnolineCarInfo> list);

	/**
	 * 根据唯一id 查询多个未上线车辆
	 * @param listId
	 * @return
	 */
    List<ErpNotnolineCarInfo> selectByListId(List<String> listId);


	/**
	 * 数据库中查出所有的用户信息
	 * @return
	 */
	List<ErpNotnolineCarInfo> selectByAllInfo(String unlocatonDay);

	/**
	 * 查询短信表中类型为0 的信息
	 * @return
	 */
    List<ErpNotnolineCarInfo> selectByAllNewManyInfo();

	/**
	 * 根据未定位id查询一辆车
	 * @param ownerUnlocationRemindId
	 * @return
	 */
	ErpNotnolineCarInfo selectByMsgId(String ownerUnlocationRemindId);

	/**
	 * 查询所有的未定位车辆信息
	 * @return
	 */
	List<ErpNotnolineCarInfo> selectByAll();

	/**
	 * 更新未定位车辆短信次数
	 * @param listNew
	 */
	void updateByAllSendMsgNum(List<ErpNotnolineCarInfo> listNew);
	/**
	 * 先删除
	 * @param list
	 */
	void deteleByAll(List<ErpNotnolineCarInfo> list,String todayNum);

	/**
	 * 删除前一天的记录
	 * @param arg1
	 */
    void deleteByTime(String arg1,String arg0);

	int countByTypeId(String type);

	void insertType1(List<ErpNotnolineCarInfoVo> list);
	void insertType2(List<ErpNotnolineCarInfoVo> list);
	void insertType3(List<ErpNotnolineCarInfoVo> list);
}
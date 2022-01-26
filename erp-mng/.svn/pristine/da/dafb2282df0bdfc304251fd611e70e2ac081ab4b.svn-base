package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpNotnolineCarInfo;
import com.edgedo.sys.entity.ErpNotnolineCarSendMessage;
import com.edgedo.sys.queryvo.ErpNotnolineCarSendMessageQuery;
import com.edgedo.sys.queryvo.ErpNotnolineCarSendMessageView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpNotnolineCarSendMessageMapper  extends BaseMapper<ErpNotnolineCarSendMessage>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpNotnolineCarSendMessageView> listPage(ErpNotnolineCarSendMessageQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpNotnolineCarSendMessageView> listByObj(ErpNotnolineCarSendMessageQuery query);

	/**
	 * 将多条短信的记录插入到新表中
	 * @param list
	 */
	void insertNewSendManyMessage(List<ErpNotnolineCarInfo> list);
	/**
	 * 查询发送多个短信的车辆   0
	 * @return
	 */
	List<ErpNotnolineCarSendMessage> selectByAllNewManyInfo();
	/**
	 * 将所有短信的记录插入到新表中
	 * @param list
	 */
	void insertNewSendAllMessage(List<ErpNotnolineCarInfo> list);

	/**
	 * 查询发送所有短信的车辆   1
	 * @return
	 */
	List<ErpNotnolineCarSendMessage> selectByAllNewAllInfo();

	/**
	 * 发送完信息  将信息设置为已发送 0- 2
	 * @param list
	 */
	void updateAllManyMessage(List<ErpNotnolineCarSendMessage> list);

	/**
	 * 发送完信息  将信息设置为已发送 1-  2
	 * @param list
	 */
	void updateAllMessage(List<ErpNotnolineCarSendMessage> list);
}
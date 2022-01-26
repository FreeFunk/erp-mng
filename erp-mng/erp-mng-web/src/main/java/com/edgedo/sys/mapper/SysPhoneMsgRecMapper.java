package com.edgedo.sys.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpCarInfo;
import com.edgedo.sys.entity.SysPhoneMsgRec;
import com.edgedo.sys.queryvo.SysPhoneMsgRecQuery;
import com.edgedo.sys.queryvo.SysPhoneMsgRecView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SysPhoneMsgRecMapper  extends BaseMapper<SysPhoneMsgRec>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysPhoneMsgRecView> listPage(SysPhoneMsgRecQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysPhoneMsgRecView> listByObj(SysPhoneMsgRecQuery query);


    SysPhoneMsgRec selectBySmsid(String smsid);

	List<SysPhoneMsgRec> selectUnSendList();

}
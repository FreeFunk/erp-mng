package com.edgedo.sys.service;
		
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpCarInfo;
import com.edgedo.sys.entity.SysPhoneMsgRec;
import com.edgedo.sys.mapper.SysPhoneMsgRecMapper;
import com.edgedo.sys.queryvo.SysPhoneMsgRecQuery;
import com.edgedo.sys.queryvo.SysPhoneMsgRecView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SysPhoneMsgRecService {
	
	
	@Autowired
	private SysPhoneMsgRecMapper mapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<SysPhoneMsgRecView> listPage(SysPhoneMsgRecQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SysPhoneMsgRec voObj) {
		voObj.setId(Guid.guid());
		voObj.setCreateTime(new Date());
		mapper.insert(voObj);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SysPhoneMsgRec voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SysPhoneMsgRec voObj) {
		mapper.updateAllColumnById(voObj);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return mapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return mapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public SysPhoneMsgRec loadById(String id) {
		return mapper.selectById(id);
	}


	public SysPhoneMsgRec selectBySmsid(String smsid) {
		return mapper.selectBySmsid(smsid);
	}


	public List<SysPhoneMsgRec> selectUnSendList() {
		return mapper.selectUnSendList();
	}
}

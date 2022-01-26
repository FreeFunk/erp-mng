package com.edgedo.sys.service;
		
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpNotnolineCarInfo;
import com.edgedo.sys.entity.ErpNotnolineCarSendMessage;
import com.edgedo.sys.mapper.ErpNotnolineCarSendMessageMapper;
import com.edgedo.sys.queryvo.ErpNotnolineCarSendMessageQuery;
import com.edgedo.sys.queryvo.ErpNotnolineCarSendMessageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpNotnolineCarSendMessageService {
	
	
	@Autowired
	private ErpNotnolineCarSendMessageMapper mapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpNotnolineCarSendMessageView> listPage(ErpNotnolineCarSendMessageQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpNotnolineCarSendMessage voObj) {
		voObj.setId(Guid.guid());
		mapper.insert(voObj);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpNotnolineCarSendMessage voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpNotnolineCarSendMessage voObj) {
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
	public ErpNotnolineCarSendMessage loadById(String id) {
		return mapper.selectById(id);
	}

}

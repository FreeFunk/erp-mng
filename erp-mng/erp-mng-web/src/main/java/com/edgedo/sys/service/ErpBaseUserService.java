package com.edgedo.sys.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpBaseUser;
import com.edgedo.sys.mapper.ErpBaseUserMapper;
import com.edgedo.sys.queryvo.ErpBaseUserQuery;
import com.edgedo.sys.queryvo.ErpBaseUserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpBaseUserService {
	
	
	@Autowired
	private ErpBaseUserMapper erpBaseUserMapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpBaseUserView> listPage(ErpBaseUserQuery erpBaseUserQuery){
		List list = erpBaseUserMapper.listPage(erpBaseUserQuery);
		erpBaseUserQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpBaseUser erpBaseUser) {
		erpBaseUser.setId(Guid.guid());
		erpBaseUserMapper.insert(erpBaseUser);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpBaseUser erpBaseUser) {
		erpBaseUserMapper.updateById(erpBaseUser);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpBaseUser erpBaseUser) {
		erpBaseUserMapper.updateAllColumnById(erpBaseUser);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return erpBaseUserMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return erpBaseUserMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpBaseUser loadById(String id) {
		return erpBaseUserMapper.selectById(id);
	}
	

}

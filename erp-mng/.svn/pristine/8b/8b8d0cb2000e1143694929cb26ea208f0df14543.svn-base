package com.edgedo.sys.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpSupplier;
import com.edgedo.sys.mapper.ErpSupplierMapper;
import com.edgedo.sys.queryvo.ErpSupplierQuery;
import com.edgedo.sys.queryvo.ErpSupplierView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpSupplierService {


	@Autowired
	private ErpSupplierMapper mapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpSupplierView> listPage(ErpSupplierQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpSupplier voObj) {
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
	public String update(ErpSupplier voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpSupplier voObj) {
		mapper.updateAllColumnById(voObj);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int  delete(String id) {
		
		return mapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByDataState(List<String> ids) {
		mapper.deleteByDataState(ids);

	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpSupplier loadById(String id) {
		return mapper.selectById(id);
	}

    public List<ErpSupplier> selectSupplierName() {
		return mapper.selectSupplierNames();
    }

	public List<ErpSupplier> selectNewSimOperator() {
		return mapper.selectNewSimOperator();
	}
}

package com.edgedo.sys.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpCarTechnicalDossier;
import com.edgedo.sys.mapper.ErpCarTechnicalDossierMapper;
import com.edgedo.sys.queryvo.ErpCarTechnicalDossierQuery;
import com.edgedo.sys.queryvo.ErpCarTechnicalDossierView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpCarTechnicalDossierService {
	
	
	@Autowired
	private ErpCarTechnicalDossierMapper mapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpCarTechnicalDossierView> listPage(ErpCarTechnicalDossierQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpCarTechnicalDossier voObj) {
		voObj.setId(Guid.guid());
		voObj.setDataState("1");
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
	public String update(ErpCarTechnicalDossier voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpCarTechnicalDossier voObj) {
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
		
		return mapper.deleteByIdsAll(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpCarTechnicalDossier loadById(String id) {
		return mapper.selectById(id);
	}


	/**
	 * 删除单个
	 * @param s
	 */
    public void deleteByIdsOne(String ids) {
		mapper.deleteByIdsOne(ids);
    }
}

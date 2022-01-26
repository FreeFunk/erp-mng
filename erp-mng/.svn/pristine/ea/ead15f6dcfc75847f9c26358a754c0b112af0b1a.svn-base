package com.edgedo.sys.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpBeidouAddStatistics;
import com.edgedo.sys.mapper.ErpBeidouAddStatisticsMapper;
import com.edgedo.sys.queryvo.ErpBeidouAddStatisticsQuery;
import com.edgedo.sys.queryvo.ErpBeidouAddStatisticsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpBeidouAddStatisticsService {
	
	
	@Autowired
	private ErpBeidouAddStatisticsMapper mapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpBeidouAddStatisticsView> listPage(ErpBeidouAddStatisticsQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpBeidouAddStatistics voObj) {
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
	public String update(ErpBeidouAddStatistics voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpBeidouAddStatistics voObj) {
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
	public ErpBeidouAddStatistics loadById(String id) {
		return mapper.selectById(id);
	}


    public String selectByNewInfo() {
		return mapper.selectByNewInfo();
    }

	public List<ErpBeidouAddStatistics> selectByNewDate(String newDate) {
		return mapper.selectByNewDate(newDate);
	}
}

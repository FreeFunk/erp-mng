package com.edgedo.sys.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpAddCarStatistics;
import com.edgedo.sys.mapper.ErpAddCarStatisticsMapper;
import com.edgedo.sys.mapper.ErpCarSimpleInfoMapper;
import com.edgedo.sys.queryvo.ErpAddCarStatisticsQuery;
import com.edgedo.sys.queryvo.ErpAddCarStatisticsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpAddCarStatisticsService {
	
	
	@Autowired
	private ErpAddCarStatisticsMapper mapper;

	@Autowired
	private ErpCarSimpleInfoMapper erpCarSimpleInfoMapper;


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpAddCarStatisticsView> listPage(ErpAddCarStatisticsQuery query){
		List list = mapper.listPage(query);

		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpAddCarStatistics voObj) {
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
	public String update(ErpAddCarStatistics voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpAddCarStatistics voObj) {
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
	public ErpAddCarStatistics loadById(String id) {
		return mapper.selectById(id);
	}


	public List<ErpAddCarStatistics> selectByCurrDate(String currWeek) {
		return mapper.selectByCurrDate(currWeek);
	}

	public String selectByTime() {
		return mapper.selectByTime();
	}

	public int selectByAll() {
		return erpCarSimpleInfoMapper.selectByAll();
	}
}

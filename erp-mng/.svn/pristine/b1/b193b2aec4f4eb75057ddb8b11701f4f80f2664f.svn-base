package com.edgedo.sys.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpWorkTask;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.mapper.ErpWorkTaskMapper;
import com.edgedo.sys.queryvo.ErpCarInfoView;
import com.edgedo.sys.queryvo.ErpWorkTaskQuery;
import com.edgedo.sys.queryvo.ErpWorkTaskView;
import com.edgedo.sys.queryvo.SysUserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpWorkTaskService {
	
	
	@Autowired
	private ErpWorkTaskMapper mapper;

	@Autowired
	private ErpWorkTaskMapper erpWorkTaskMapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpWorkTaskView> listPage(ErpWorkTaskQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}


	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpWorkTask voObj) {
		//主键
		voObj.setId(Guid.guid());
		//创建时间
		voObj.setCreateTime(new Date());
		//任务状态  未派单 0，已派单 1，进行中2，失败 3，完成 4，超时完成 5
		voObj.setTaskState("0");
		//数据状态
		voObj.setDataState("1");
		mapper.insert(voObj);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpWorkTask voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpWorkTask voObj) {
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

		return mapper.deleteByIdAll(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpWorkTask loadById(String id) {
		return mapper.selectById(id);
	}


	/**
	 * 单个删除
	 */
	public void deleteByIdsOne(String ids) {
		mapper.deleteByIdsOne(ids);
	}

	/**
	 * 触发派单按钮  修改状态
	 * @param ids
	 * @param state
	 */
	public void updateByIdsTaskState(String ids, String state) {
		mapper.updateByIdsTaskState(ids,state);
	}

	/**
	 * 根据id查询一条派单任务记录
	 * @param ids
	 * @return
	 */
	public ErpWorkTask selectByIds(String ids) {
		return mapper.selectByIds(ids);
	}

	/**
	 * 点击确认派单  更新状态
	 * @param state
	 */
	public void updateByTaskState(String state,String workTaskId) {
		mapper.updateByTaskState(state,workTaskId);
	}

	/**
	 * 查询业务人员---外勤
	 * @return
	 */
	public List<SysUser> selectByYeWu() {
		return mapper.selectByYeWu();
	}

	public void updateByWorkTaskTime(ErpWorkTask erpWorkTask) {
		//4.进行判断
		ErpWorkTask erpWorkTaskOld = erpWorkTaskMapper.selectByOrderId(erpWorkTask.getId());
		//5.任务表中的完成限时  大于限时超时完成  小于等于完成
		if(erpWorkTask.getFinishedTime().getTime()>erpWorkTaskOld.getEndTime().getTime()){
			//超时完成
			erpWorkTask.setTaskState("5");
		}else {
			erpWorkTask.setTaskState("4");//完成
		}
		//更新工单表
		erpWorkTaskMapper.updateByFinishState(erpWorkTask);//更新任务表
	}
}

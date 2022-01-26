package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpWorkTask;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.queryvo.ErpWorkTaskQuery;
import com.edgedo.sys.queryvo.ErpWorkTaskView;
import com.edgedo.sys.queryvo.SysUserView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpWorkTaskMapper  extends BaseMapper<ErpWorkTask>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpWorkTaskView> listPage(ErpWorkTaskQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpWorkTaskView> listByObj(ErpWorkTaskQuery query);


	/**
	 * 删除单个
	 * @param ids
	 */
    void deleteByIdsOne(String ids);

	/**
	 * 删除多个
	 * @param ids
	 * @return
	 */
	int deleteByIdAll(List<String> ids);

	/**
	 * 触发派单按钮  修改状态
	 * @param ids
	 * @param state
	 */
    void updateByIdsTaskState(String ids, String state);

	/**
	 * 根据id查询一条派单任务记录
	 * @param ids
	 * @return
	 */
	ErpWorkTask selectByIds(String ids);

	/**
	 * 点击确认派单更新状态
	 * @param state
	 */
    void updateByTaskState(String state,String workTaskId);

	/**
	 * 根据工单表中的任务id查询
	 * @param ownerWorkTaskId
	 * @return
	 */
	ErpWorkTask selectByOrderId(String ownerWorkTaskId);

	/**
	 * 更新任务表
	 * @param erpWorkTask
	 */
	void updateByFinishState(ErpWorkTask erpWorkTask);

    List<SysUser> selectByYeWu();
}
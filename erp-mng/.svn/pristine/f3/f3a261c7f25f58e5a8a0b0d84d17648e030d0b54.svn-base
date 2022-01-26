package com.edgedo.sys.mapper;

import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpUserWorkLog;
import com.edgedo.sys.queryvo.ErpUserWorkLogQuery;
import com.edgedo.sys.queryvo.ErpUserWorkLogView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpUserWorkLogMapper  extends BaseMapper<ErpUserWorkLog>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpUserWorkLogView> listPage(ErpUserWorkLogQuery query);
	public List<ErpUserWorkLogView> selectByList(ErpUserWorkLogQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpUserWorkLogView> listByObj(ErpUserWorkLogQuery query);

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
	 * 根据员工id查询
	 * @param id
	 * @return
	 */
	ErpUserWorkLog selectByWorkUserId(String id,String today);

	/**
	 * 同一天工作几个任务  更新  服务内内容  加班时长  加班描述  是否加班
	 * @param erpUserWorkLog
	 */
	void updateByCurrState(ErpUserWorkLog erpUserWorkLog);

	/**
	 * 同一天工作几个任务 但是没有加班
	 * @param erpUserWorkLog
	 */
	void updateByCurrOneState(ErpUserWorkLog erpUserWorkLog);


    void updateByShengHe(String id, String userId, String userName, Date date);

	void updateByShengHeId(String id,Date date);

    int selectUserOne(String currDate,String userId);

	ErpUserWorkLog selectUserOneInfo(String currDate,String userId);

	void updateByInfo(ErpUserWorkLog erpUserWorkLog);
}
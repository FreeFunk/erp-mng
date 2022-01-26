package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpWorkTaskOrder;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.queryvo.ErpWorkTaskOrderQuery;
import com.edgedo.sys.queryvo.ErpWorkTaskOrderView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpWorkTaskOrderMapper  extends BaseMapper<ErpWorkTaskOrder>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpWorkTaskOrderView> listPage(ErpWorkTaskOrderQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpWorkTaskOrderView> listByObj(ErpWorkTaskOrderQuery query);


	/**
	 * 是否存在工作人员
	 * @param ids
	 * @return
	 */
    ErpWorkTaskOrder selectByIdsOnWorkUser(String ids);

	/**
	 * 修改工单状态
	 */
	void updateByTaskState(ErpWorkTaskOrder erpWorkTaskOrder);

	//更新工单表
    void updateByOutUserName(ErpWorkTaskOrder voObj);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	ErpWorkTaskOrder selectyIds(String id);

	/**
	 * 更新工单表
	 * @param erpWorkTaskOrder
	 */
	void updateByOrderState(ErpWorkTaskOrder erpWorkTaskOrder);

	/**
	 * 加班更新
	 * @param erpWorkTaskOrder
	 */
    void updateByOverWork(ErpWorkTaskOrder erpWorkTaskOrder);

	/**
	 * 删除一个
	 * @param ids
	 */
	void deleteByIdsOne(String ids);

	/**
	 * 删除多个
	 * @param ids
	 * @return
	 */
	int deleteByIdAll(List<String> ids);

	ErpWorkTaskOrder selectByIdsOnWorkUserLog(String ids);

	List<ErpWorkTaskOrderQuery> selectByWorkState();

	/**
	 * 更新工单表
	 * @param waiQinId
	 * @param waiQin
	 * @param id
	 */
    void updateByOutPutUser(String waiQinId, String waiQin,String id);

	/**
	 * 根据任务id查询
	 * @param ownerWorkTaskId
	 * @return
	 */
	ErpWorkTaskOrderView selectByOutPutUserId(String ownerWorkTaskId);

	ErpWorkTaskOrderView selectByOrderId(String ids);

	/**
	 * 查询派单表对应的工单id
	 * @param id
	 * @return
	 */
    String selectTaskId(String id);

    String selectByIdWork(String ids);

	ErpWorkTaskOrder selectByWaiQinOne(String id);

	List<SysUser> selectByInput();

    void updateByIntput(String id, String inputId, String inputName);

	List<ErpWorkTaskOrderView> selectByWorkId(String id);
}
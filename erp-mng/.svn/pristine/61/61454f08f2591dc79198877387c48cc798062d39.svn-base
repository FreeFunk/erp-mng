package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpOutputuserTaskWork;
import com.edgedo.sys.entity.ErpWorkTaskOrderUser;
import com.edgedo.sys.queryvo.ErpWorkTaskOrderUserQuery;
import com.edgedo.sys.queryvo.ErpWorkTaskOrderUserView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpWorkTaskOrderUserMapper  extends BaseMapper<ErpWorkTaskOrderUser>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpWorkTaskOrderUserView> listPage(ErpWorkTaskOrderUserQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpWorkTaskOrderUserView> listByObj(ErpWorkTaskOrderUserQuery query);


	/**
	 * 批量插入
	 * @param list
	 */
    void insertList(List<ErpWorkTaskOrderUser> list);
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
	 * 查询所有员工表
	 * @return
	 */
	List<ErpWorkTaskOrderUser> select();

	/**
	 * 根据工单id删除
	 * @param id
	 */
    void updateByUserState(String id);

    List<ErpWorkTaskOrderUser> selectCreateTime(String currDate);

    List<ErpWorkTaskOrderUser> selectByUserId(String userId);
}
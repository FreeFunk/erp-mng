package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpChannelAgent;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.entity.SysXianqu;
import com.edgedo.sys.queryvo.ErpChannelAgentQuery;
import com.edgedo.sys.queryvo.ErpChannelAgentView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpChannelAgentMapper  extends BaseMapper<ErpChannelAgent> {

	/**
	 * 分页条件查询
	 *
	 * @param query
	 * @return
	 */
	public List<ErpChannelAgentView> listPage(ErpChannelAgentQuery query);

	/**
	 * 不分页条件查询
	 *
	 * @param query
	 * @return
	 */
	public List<ErpChannelAgentView> listByObj(ErpChannelAgentQuery query);


	/**
	 * 根据所属用户id查询一个用户信息
	 *
	 * @param sysUserId
	 * @return
	 */
	SysUser selectBySysUserId(String sysUserId);

	/**
	 * 根据县区id查询地区信息
	 *
	 * @param xianQuId
	 * @return
	 */
	SysXianqu selectByXianQuId(String xianQuId);

	/**
	 * 根据渠道代理id查询渠道代理名
	 *
	 * @param channelAgentId
	 * @return
	 */
	String selectByChannelAgentId(String channelAgentId);

	/**
	 * 删除单个
	 *
	 * @param ids
	 */
	void deleteByIdsOne(String ids);

	/**
	 * 删除多个
	 *
	 * @param ids
	 * @return
	 */
	int deleteByIdsAll(List<String> ids);

}
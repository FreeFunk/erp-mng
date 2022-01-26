package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpCarTeam;
import com.edgedo.sys.entity.SysXianqu;
import com.edgedo.sys.queryvo.ErpCarTeamQuery;
import com.edgedo.sys.queryvo.ErpCarTeamView;
import com.edgedo.sys.queryvo.SysXianquQuery;
import com.edgedo.sys.queryvo.SysXianquView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpCarTeamMapper  extends BaseMapper<ErpCarTeam>{

	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarTeamView> listPage(ErpCarTeamQuery query);

	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarTeamView> listByObj(ErpCarTeamQuery query);


	/**
	 * 查询县区关联的所有信息
	 * @return
	 */
    List<SysXianquView> selectByXianQuList();

	/**
	 * 根据县区id查询一条记录
	 * @param xianQuId
	 * @return
	 */
	SysXianqu selectByXianQuId(String xianQuId);

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
	int deleteByIdsAll(List<String> ids);

	/**
	 * 根据车队名查询id
	 * @param ownerTeamName
	 * @return
	 */
	ErpCarTeam selectByCarTeamId(String ownerTeamName);

	/**
	 * 根据车队名，查询是否存在同名
	 * @param carTeamName
	 * @return
	 */
	int selecByCarTeamName(String carTeamName);
}
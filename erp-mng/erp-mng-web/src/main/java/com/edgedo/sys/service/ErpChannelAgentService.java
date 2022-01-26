package com.edgedo.sys.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.common.util.MD5;
import com.edgedo.sys.entity.ErpChannelAgent;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.entity.SysXianqu;
import com.edgedo.sys.mapper.ErpChannelAgentMapper;
import com.edgedo.sys.mapper.SysUserMapper;
import com.edgedo.sys.queryvo.ErpChannelAgentQuery;
import com.edgedo.sys.queryvo.ErpChannelAgentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpChannelAgentService {
	
	
	@Autowired
	private ErpChannelAgentMapper mapper;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpChannelAgentView> listPage(ErpChannelAgentQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpChannelAgentView voObj) {
		voObj.setId(Guid.guid());
		//1.将新加入的渠道代理加入sysuser
//		SysUser sysUser1 = new SysUser();
//		sysUser1.setId(Guid.guid());//ID
//		sysUser1.setUserCode(voObj.getUserName());//用户名
//		String pass = MD5.encode(MD5.encode(voObj.getPassword())+sysUser1.getId());
//		sysUser1.setPassword(pass);//密码
//		sysUser1.setUserName(voObj.getChannelAgentName());//代理人的姓名
//		sysUser1.setPhone(voObj.getContactPhoneNum());//联系电话
		//查询地区
		String xianQuId = voObj.getXianquId();//县区id
		SysXianqu sysXianqu = mapper.selectByXianQuId(xianQuId);
//		sysUser1.setProvinceId(sysXianqu.getProvinceId());//省id
//		sysUser1.setProvinceName(sysXianqu.getProvinceName());//省名
//		sysUser1.setCityId(sysXianqu.getCityId());//城市id
//		sysUser1.setCityName(sysXianqu.getCityName());//城市名字
//		sysUser1.setXianquId(voObj.getXianquId());//县区id
//		sysUser1.setXianquName(sysXianqu.getName());//县区名
//		sysUser1.setDataState("1");
//		sysUser1.setUserState("ACTIVE");
//		sysUser1.setCreateTime(new Date());
//		sysUser1.setDefaultRoleId("AGENT");//默认角色
//		sysUser1.setDefaultRoleName("代理商");
//		sysUserMapper.insert(sysUser1);//插入sysuser表

		//更新渠道代理表
		voObj.setProvinceId(sysXianqu.getProvinceId());//省id
		voObj.setProvinceName(sysXianqu.getProvinceName());//省名
		voObj.setCityId(sysXianqu.getCityId());//城市id
		voObj.setCityName(sysXianqu.getCityName());//城市名字
		voObj.setXianquId(voObj.getXianquId());//县区id
		voObj.setXianquName(sysXianqu.getName());//县区名
//		voObj.setSysUserId(sysUser1.getId());
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
	public String update(ErpChannelAgent voObj) {
		String xianQuId = voObj.getXianquId();//县区id
		SysXianqu sysXianqu = mapper.selectByXianQuId(xianQuId);
		voObj.setXianquId(voObj.getXianquId());//县区id
		voObj.setXianquName(sysXianqu.getName());//县区名
		sysUserMapper.updateByXianQu(voObj.getSysUserId(),sysXianqu.getId(),sysXianqu.getName());
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpChannelAgent voObj) {
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
	public ErpChannelAgent loadById(String id) {
		return mapper.selectById(id);
	}


	/**
	 * 删除单个
	 * @param ids
	 */
	public void deleteByIdsOne(String ids) {
		mapper.deleteByIdsOne(ids);
	}
}

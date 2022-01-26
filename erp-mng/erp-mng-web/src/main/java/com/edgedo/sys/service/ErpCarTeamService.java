package com.edgedo.sys.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpCarTeam;
import com.edgedo.sys.entity.SysXianqu;
import com.edgedo.sys.mapper.ErpCarTeamMapper;
import com.edgedo.sys.queryvo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpCarTeamService {


	@Autowired
	private ErpCarTeamMapper mapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpCarTeamView> listPage(ErpCarTeamQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}

	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpCarTeam voObj) {
		int num = mapper.selecByCarTeamName(voObj.getCarTeamName());
		if(num==0){//判断是否存在一个车队名
			voObj.setId(Guid.guid());
			String xianQuId = voObj.getXianquId();//县区id
			SysXianqu sysXianqu = mapper.selectByXianQuId(xianQuId);
			voObj.setXianquName(sysXianqu.getName());//县区名
			voObj.setCityId(sysXianqu.getCityId());//城市id
			voObj.setCityName(sysXianqu.getCityName());//城市名字
			voObj.setProvinceId(sysXianqu.getProvinceId());//省id
			voObj.setProvinceName(sysXianqu.getProvinceName());//省名
			voObj.setCreateTime(new Date());
			voObj.setDataState("1");
			mapper.insert(voObj);
			return "";
		}else{
			return "车队名已存在,请重新输入!";
		}
	}

	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpCarTeam voObj) {
		mapper.updateById(voObj);
		return "";
	}

	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpCarTeam voObj) {
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
	public ErpCarTeam loadById(String id) {
		return mapper.selectById(id);
	}

	/**
	 * 查询县区关联所有信息
	 * @return
	 */
    public List<SysXianquView> selectByXianQuId() {
		return mapper.selectByXianQuList();
    }

	/**
	 * 删除单个
	 * @param ids
	 */
	public void deleteByIdsOne(String ids) {
		mapper.deleteByIdsOne(ids);
	}
}

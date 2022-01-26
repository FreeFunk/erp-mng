package com.edgedo.sys.service;
		
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpCarInfo;
import com.edgedo.sys.entity.ErpGpsTerminal;
import com.edgedo.sys.entity.ErpSupplier;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.mapper.ErpCarInfoMapper;
import com.edgedo.sys.mapper.ErpGpsTerminalMapper;
import com.edgedo.sys.mapper.ErpSupplierMapper;
import com.edgedo.sys.mapper.SysUserMapper;
import com.edgedo.sys.queryvo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpGpsTerminalService {
	
	
	@Autowired
	private ErpGpsTerminalMapper mapper;

	@Autowired
	private ErpSupplierMapper erpSupplierMapper;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private ErpCarInfoMapper erpCarInfoMapper;


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpGpsTerminalView> listPage(ErpGpsTerminalQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpGpsTerminal voObj) {
		voObj.setId(Guid.guid());
		voObj.setCreateTime(new Date());
		//添加供应商ID
		List<ErpSupplierView> erpSupplier = erpSupplierMapper.selectBySupplierName(voObj.getSupplierName());
		String erpSuppLierId="";
		for(ErpSupplierView erpSuppliers:erpSupplier){
			erpSuppLierId = erpSuppLierId+erpSuppliers.getId();
		}
		voObj.setSupplierId(erpSuppLierId);
		//添加占用人ID
		SysUserView sysUser= sysUserMapper.selectBySysUserName(voObj.getTerminalUserName());
		voObj.setTerminalUserId(sysUser.getId());
		//添加占用人TYPE
		voObj.setUserType(sysUser.getDefaultRoleName());
		//判断车牌号是否存在
		if(voObj.getCarPlateNum() != null && !voObj.getCarPlateNum().equals("")){
			ErpCarInfo carPlateNum = erpCarInfoMapper.selectByCarPlateNum(voObj.getCarPlateNum());
			if(carPlateNum != null){
				mapper.insert(voObj);
				return "";
			}else{
				return "车牌号不存在";
			}
		}else{
			mapper.insert(voObj);
			return "";
		}
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpGpsTerminal voObj) {
		//添加供应商ID
		List<ErpSupplierView> erpSupplier = erpSupplierMapper.selectBySupplierName(voObj.getSupplierName());
		String erpSupplierId="";
		for(ErpSupplierView erpSuppliers:erpSupplier){
			erpSupplierId = erpSuppliers.getId();
		}
		voObj.setSupplierId(erpSupplierId);
		//添加占用人ID
		SysUserView sysUser= sysUserMapper.selectBySysUserName(voObj.getTerminalUserName());
		voObj.setTerminalUserId(sysUser.getId());
		//添加占用人TYPE
		voObj.setUserType(sysUser.getDefaultRoleName());
		//判断车牌号是否存在
		if(voObj.getCarPlateNum() != null && !voObj.getCarPlateNum().equals("")){
			ErpCarInfo carPlateNum = erpCarInfoMapper.selectByCarPlateNum(voObj.getCarPlateNum());
			if(carPlateNum != null){
				mapper.updateById(voObj);
				return "";
			}else{
				return "车牌号不存在";
			}
		}else{
			mapper.updateById(voObj);
			return "";
		}
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpGpsTerminal voObj) {
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
	public ErpGpsTerminal loadById(String id) {
		return mapper.selectById(id);
	}

	/**
	 * 查询供应商表供应商名称对应ID
	 */
	public String selectSupplierId(String supplierName) {

		List<ErpGpsTerminalView> list = mapper.selectSupplierId(supplierName);;
		String sumSupplierId = "";
		for(int i=0;i<list.size();i++){
			sumSupplierId=sumSupplierId+list.get(i).getId() + ",";
		}
		return sumSupplierId;
	}

	/**
	 * 查询供应商表姓名
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpSupplierView> selectSupplierName() {
		return mapper.selectSupplierName();
	}

	/**
	 * 逻辑删除
	 * @param list
	 */
	public void deleteByDataState(List<String> list) {
		mapper.deleteByDataState(list);
	}

}

package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpGpsTerminal;
import com.edgedo.sys.entity.ErpSupplier;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.queryvo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ErpGpsTerminalMapper  extends BaseMapper<ErpGpsTerminal>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpGpsTerminalView> listPage(ErpGpsTerminalQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpGpsTerminalView> listByObj(ErpGpsTerminalQuery query);

	/**
	 * 根据名称查找对应供应商名称ID
	 * @param supplierName
	 * @return
	 */
	public List<ErpGpsTerminalView> selectSupplierId(@Param("supplierName")String supplierName);

	/**
	 * 查询供应商名称
	 * @return
	 */
	public List<ErpSupplierView> selectSupplierName();

	/**
	 *逻辑删除
	 * @param list
	 */
	public void deleteByDataState( List<String> list);

	/**
	 * 根据供应商名 id  终端编号 查询一个终端id
	 * @param terminalCode
	 * @param supplierId
	 * @param supplierName
	 * @return
	 */
	ErpGpsTerminal selectByTerminalCodeByBySupplierIdBySupplierName(String terminalCode, String supplierId, String supplierName);


	/*List<SysUserView> listBySysUser(SysUserQuery query);*/


	/*List<SysUserView> listBySysUser(SysUserQuery query);*/
}

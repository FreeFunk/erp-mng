package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpGpsTerminal;
import com.edgedo.sys.entity.ErpSupplier;
import com.edgedo.sys.queryvo.ErpSupplierQuery;
import com.edgedo.sys.queryvo.ErpSupplierView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ErpSupplierMapper  extends BaseMapper<ErpSupplier>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpSupplierView> listPage(ErpSupplierQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpSupplierView> listByObj(ErpSupplierQuery query);


	/**
	 * 逻辑删除
	 */
	public void deleteByDataState (List<String> ids);


	/**
	 * 根据供应商id 查询供应商名
	 * @param supplierId
	 * @return
	 */
    String selectBySupplierId(String supplierId);

	/**
	 * 根据供应商名查id
	 * @param supplierName
	 * @return
	 */
	public List<ErpSupplierView> selectBySupplierName(String supplierName);

    String selectSupplierName(String supplierId);

	List<ErpSupplier> selectSupplierNames();

    List<ErpSupplier> selectNewSimOperator();
}
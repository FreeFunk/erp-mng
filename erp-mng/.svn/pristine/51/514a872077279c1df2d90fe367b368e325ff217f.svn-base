package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpSim;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.queryvo.*;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpSimMapper  extends BaseMapper<ErpSim>{

	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpSimView> listPage(ErpSimQuery query);

	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpSimView> listByObj(ErpSimQuery query);


	/**
	 * 更新sim卡状态
	 * @param erpSimNoChange
	 */
	void updateBySimState(ErpSim erpSimNoChange);

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
	 * 查询sim占用人
	 * @param query
	 * @return
	 */
	List<SysUserView> listBySysUser(SysUserQuery query);


	/**
	 * 根据id查询一个sys对象
	 * @param userId
	 * @return
	 */
	SysUser selectByIdSysUser(String userId);

	/**
	 * 换卡后注销原卡号
	 * @param info
	 * @param orgSimNum
	 */
	void updateBySimStateChange(String info, String orgSimNum);

	/**
	 * 根据sim卡号查询
	 * @param simCode
	 * @return
	 */
	ErpSim selectBySimCode(String simCode);

	/**
	 * 查询一条sim卡记录
	 * @param newSimNum
	 * @return
	 */
    ErpSim selectByIds(String newSimNum);

	/**
	 * sim卡查询
	 * @return
	 */
	List selectSim();

	/**
	 * 查询所有SIM类型的供应商
	 * @param
	 * @return
	 */
	List<ErpSim> selectNewSimOperator();

    void updateZhuXiao(ErpSim erpSim);

    ErpSim selectByErpNum(String newSimNum);

	List<ErpSim> selectAllSim();

	List<ErpSim> selectSelectAllSim(List<String> idList);

    void updateBySimCode(ErpSim erpSim);

    void updateExcel(ErpSim erpSim);

    int countByCarPlateNum(String carPlateNum);

	void updateByCarPlateNum(String carPlateNum);
}
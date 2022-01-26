package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpCarTechnicalDossier;
import com.edgedo.sys.queryvo.ErpCarTechnicalDossierQuery;
import com.edgedo.sys.queryvo.ErpCarTechnicalDossierView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ErpCarTechnicalDossierMapper  extends BaseMapper<ErpCarTechnicalDossier>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarTechnicalDossierView> listPage(ErpCarTechnicalDossierQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCarTechnicalDossierView> listByObj(ErpCarTechnicalDossierQuery query);


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

    ErpCarTechnicalDossier selectBycarFrameNum(@Param("carFrameNum")String carFrameNum);

    void insertListErpCarTechnicalDossier(List<ErpCarTechnicalDossier> listErpCarTechnicalDossier);
}
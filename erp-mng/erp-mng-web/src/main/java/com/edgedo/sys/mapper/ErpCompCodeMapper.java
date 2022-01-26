package com.edgedo.sys.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpCompCode;
import com.edgedo.sys.queryvo.ErpCompCodeQuery;
import com.edgedo.sys.queryvo.ErpCompCodeView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpCompCodeMapper  extends BaseMapper<ErpCompCode>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCompCodeView> listPage(ErpCompCodeQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpCompCodeView> listByObj(ErpCompCodeQuery query);


    List<ErpCompCode> selectAllCarInfo(Map<String, Object> mapQuery);

    int updateByIdDelete(List<String> ids);

    ErpCompCode selectByCodeAndCompName(String compName ,String code);

	void updateVoId(ErpCompCode erpCompCode);

    int selectVoCompName(String compName);

	int selectVoCode(String compCode);

	int selectVoCompNameUpdate(String compName, String id);

	int selectVoCodeUpdate(String compCode, String id);
}
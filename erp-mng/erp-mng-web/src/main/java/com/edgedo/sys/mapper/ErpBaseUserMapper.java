package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpBaseUser;
import com.edgedo.sys.queryvo.ErpBaseUserQuery;
import com.edgedo.sys.queryvo.ErpBaseUserView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpBaseUserMapper  extends BaseMapper<ErpBaseUser>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpBaseUserView> listPage(ErpBaseUserQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpBaseUserView> listByObj(ErpBaseUserQuery query);
	
	

}
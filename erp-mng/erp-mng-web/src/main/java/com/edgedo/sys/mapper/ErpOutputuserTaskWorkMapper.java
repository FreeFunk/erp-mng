package com.edgedo.sys.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.sys.entity.ErpOutputuserTaskWork;
import com.edgedo.sys.queryvo.ErpOutputuserTaskWorkQuery;
import com.edgedo.sys.queryvo.ErpOutputuserTaskWorkView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ErpOutputuserTaskWorkMapper  extends BaseMapper<ErpOutputuserTaskWork>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpOutputuserTaskWorkView> listPage(ErpOutputuserTaskWorkQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<ErpOutputuserTaskWorkView> listByObj(ErpOutputuserTaskWorkQuery query);


    List<ErpOutputuserTaskWork> selectByUserId(String userId);

    void updateByWorkId(String id);
}
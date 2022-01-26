package com.edgedo.sys.service;
		
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpOutputuserTaskWork;
import com.edgedo.sys.entity.ErpWorkTask;
import com.edgedo.sys.entity.ErpWorkTaskOrder;
import com.edgedo.sys.mapper.ErpOutputuserTaskWorkMapper;
import com.edgedo.sys.mapper.ErpWorkTaskOrderMapper;
import com.edgedo.sys.queryvo.ErpOutputuserTaskWorkQuery;
import com.edgedo.sys.queryvo.ErpOutputuserTaskWorkView;
import com.edgedo.sys.queryvo.ErpWorkTaskOrderQuery;
import com.edgedo.sys.queryvo.ErpWorkTaskOrderView;
import com.sun.java.accessibility.util.GUIInitializedListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpOutputuserTaskWorkService {
	
	
	@Autowired
	private ErpOutputuserTaskWorkMapper mapper;

	@Autowired
	private ErpWorkTaskOrderMapper erpWorkTaskOrderMapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpOutputuserTaskWorkView> listPage(ErpOutputuserTaskWorkQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpOutputuserTaskWork voObj) {
		voObj.setId(Guid.guid());
		mapper.insert(voObj);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpOutputuserTaskWork voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpOutputuserTaskWork voObj) {
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
	public ErpOutputuserTaskWork loadById(String id) {
		return mapper.selectById(id);
	}


    public void insertByNewOne(ErpWorkTask erpWorkTask) {
		//查询派单表对应工单id
		String orderId = erpWorkTaskOrderMapper.selectTaskId(erpWorkTask.getId());
		String waiQin = erpWorkTask.getFinishedUserName();
		//多个外勤人员
		String[] userArr = waiQin.split(",");
		if(userArr.equals("")){
			//一个外勤人员
			ErpOutputuserTaskWork erpOutputuserTaskWork = new ErpOutputuserTaskWork();
			String[] oneUserArr = waiQin.split("@");
			String waiQinId =oneUserArr[0];
			//主键
			erpOutputuserTaskWork.setId(Guid.guid());
			//创建时间
			erpOutputuserTaskWork.setCreateTime(new Date());
			//外勤id
			erpOutputuserTaskWork.setOutputUserId(waiQinId);
			//任务id
			erpOutputuserTaskWork.setOwnerWorkTaskId(orderId);
			//数据状态
			erpOutputuserTaskWork.setDataState("1");
			mapper.insert(erpOutputuserTaskWork);
		}else {
			for(int i = 0;i<userArr.length;i++){
				//一个外勤人员
				ErpOutputuserTaskWork erpOutputuserTaskWork = new ErpOutputuserTaskWork();
				String[] oneUserArr = userArr[i].split("@");
				String waiQinId = oneUserArr[0];
				//主键
				erpOutputuserTaskWork.setId(Guid.guid());
				//创建时间
				erpOutputuserTaskWork.setCreateTime(new Date());
				//外勤id
				erpOutputuserTaskWork.setOutputUserId(waiQinId);
				//任务id
				erpOutputuserTaskWork.setOwnerWorkTaskId(orderId);
				//数据状态
				erpOutputuserTaskWork.setDataState("1");
				mapper.insert(erpOutputuserTaskWork);
			}
		}
    }

	/**
	 * 点击转出新建关联表
	 * @param waiQin
	 */
	public void insertByNewOutPutUser(String waiQin,String taskId) {
		//多个外勤人员
		String[] userArr = waiQin.split(",");
		if(userArr.equals("")){
			//一个外勤人员
			ErpOutputuserTaskWork erpOutputuserTaskWork = new ErpOutputuserTaskWork();
			String[] oneUserArr = waiQin.split("@");
			String waiQinId =oneUserArr[0];
			//主键
			erpOutputuserTaskWork.setId(Guid.guid());
			//创建时间
			erpOutputuserTaskWork.setCreateTime(new Date());
			//外勤id
			erpOutputuserTaskWork.setOutputUserId(waiQinId);
			//任务id
			erpOutputuserTaskWork.setOwnerWorkTaskId(taskId);
			//数据状态
			erpOutputuserTaskWork.setDataState("1");
			mapper.insert(erpOutputuserTaskWork);
		}else {
			for(int i = 0;i<userArr.length;i++){
				//一个外勤人员
				ErpOutputuserTaskWork erpOutputuserTaskWork = new ErpOutputuserTaskWork();
				String[] oneUserArr = userArr[i].split("@");
				String waiQinId = oneUserArr[0];
				//主键
				erpOutputuserTaskWork.setId(Guid.guid());
				//创建时间
				erpOutputuserTaskWork.setCreateTime(new Date());
				//外勤id
				erpOutputuserTaskWork.setOutputUserId(waiQinId);
				//任务id
				erpOutputuserTaskWork.setOwnerWorkTaskId(taskId);
				//数据状态
				erpOutputuserTaskWork.setDataState("1");
				mapper.insert(erpOutputuserTaskWork);
			}
		}
	}



	/**
	 * 根据外勤人员id查询关联订单
	 * @param userId
	 * @return
	 */
//	public List<ErpWorkTaskOrderView> selectByUserId(String userId) {
//		//查询所有相关的工单
//		List<ErpWorkTaskOrderView> listErpWorkTask = new ArrayList<ErpWorkTaskOrderView>();
//		List<ErpOutputuserTaskWork> list = mapper.selectByUserId(userId);
//		for(ErpOutputuserTaskWork erpOutputuserTaskWork : list){
//			 List<ErpWorkTaskOrderView> list1 = erpWorkTaskOrderMapper.selectByOutPutUserId(erpOutputuserTaskWork.getOwnerWorkTaskId());
//			for(ErpWorkTaskOrderView erpWorkTaskOrderView : list1){
//				String[] arr = erpWorkTaskOrderView.getOutputUserId().split(",");
//				for(String string : arr){
//					if(!(string.equals(userId))){
//						continue;
//					}else {
//						listErpWorkTask.add(erpWorkTaskOrderView);
//					}
//				}
//			}
//		}
//		return listErpWorkTask;
//	}
}

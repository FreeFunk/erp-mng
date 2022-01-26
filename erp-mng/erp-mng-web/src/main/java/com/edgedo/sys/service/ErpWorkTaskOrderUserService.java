package com.edgedo.sys.service;
		
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpOutputuserTaskWork;
import com.edgedo.sys.entity.ErpWorkTask;
import com.edgedo.sys.entity.ErpWorkTaskOrder;
import com.edgedo.sys.entity.ErpWorkTaskOrderUser;
import com.edgedo.sys.mapper.ErpWorkTaskOrderMapper;
import com.edgedo.sys.mapper.ErpWorkTaskOrderUserMapper;
import com.edgedo.sys.mapper.SysUserMapper;
import com.edgedo.sys.queryvo.ErpWorkTaskOrderUserQuery;
import com.edgedo.sys.queryvo.ErpWorkTaskOrderUserView;
import com.edgedo.sys.queryvo.ErpWorkTaskOrderView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpWorkTaskOrderUserService {
	
	
	@Autowired
	private ErpWorkTaskOrderUserMapper mapper;

	@Autowired
	private ErpWorkTaskOrderMapper erpWorkTaskOrderMapper;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpWorkTaskOrderUserView> listPage(ErpWorkTaskOrderUserQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpWorkTaskOrderUser voObj) {
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
	public String update(ErpWorkTaskOrderUser voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpWorkTaskOrderUser voObj) {
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

		return mapper.deleteByIdAll(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public ErpWorkTaskOrderUser loadById(String id) {
		return mapper.selectById(id);
	}

	/**
	 * 确认工单表  同步员工工单表
	 * @param ids
	 */
    public void insertUser(String ids) {
    	//获取工单表
		ErpWorkTaskOrder erpWorkTaskOrder = erpWorkTaskOrderMapper.selectByIdsOnWorkUser(ids);
		List<ErpWorkTaskOrderUser> list = new ArrayList<ErpWorkTaskOrderUser>();
		//外勤人员
		String outUserName = erpWorkTaskOrder.getOutputUserName();
		String outUserId = erpWorkTaskOrder.getOutputUserId();
		String[] outputUserNameArr = outUserName.split(",");//按逗号切割
		String[] outputUserIdArr = outUserId.split(",");//按逗号切割
		for(int i = 0;i<outputUserNameArr.length;i++){
			//主键
			String id = Guid.guid();
			//外勤人员对象
			ErpWorkTaskOrderUser erpWorkTaskOrderUser = new ErpWorkTaskOrderUser();
			//创建时间
			erpWorkTaskOrderUser.setCreateTime(new Date());
			//所属工单id
			erpWorkTaskOrderUser.setOwnerWorkTaskOrderId(erpWorkTaskOrder.getId());
			//数据状态
			erpWorkTaskOrderUser.setDataState("1");
			erpWorkTaskOrderUser.setId(id);
			//员工名
			erpWorkTaskOrderUser.setOwnerUserId(outputUserIdArr[i]);
			erpWorkTaskOrderUser.setOwnerUserName(outputUserNameArr[i]);
			list.add(erpWorkTaskOrderUser);
		}
		mapper.insertList(list);
    }
	/**
	 * 单个删除
	 */
	public void deleteByIdsOne(String ids) {
		mapper.deleteByIdsOne(ids);
	}

	public void insertByNewOne(ErpWorkTask erpWorkTask) {
		//查询派单表对应工单id
		String orderId = erpWorkTaskOrderMapper.selectTaskId(erpWorkTask.getId());
		String waiQin = erpWorkTask.getFinishedUserName();//外勤
		//多个外勤人员
		String[] userArr = waiQin.split(",");
		if(userArr.equals("")){
			//一个外勤人员
			ErpWorkTaskOrderUser erpWorkTaskOrderUser = new ErpWorkTaskOrderUser();
			String[] oneUserArr = waiQin.split("@");
			String waiQinId = oneUserArr[0];
			String waiQinName = oneUserArr[1];
			//主键
			erpWorkTaskOrderUser.setId(Guid.guid());
			//创建时间
			erpWorkTaskOrderUser.setCreateTime(new Date());
			//外勤id
			erpWorkTaskOrderUser.setOwnerUserId(waiQinId);
			erpWorkTaskOrderUser.setOwnerUserName(waiQinName);
			//任务id
			erpWorkTaskOrderUser.setOwnerWorkTaskOrderId(orderId);
			//数据状态
			erpWorkTaskOrderUser.setDataState("1");
			mapper.insert(erpWorkTaskOrderUser);
		}else {
			for(int i = 0;i<userArr.length;i++){
				//一个外勤人员
				ErpWorkTaskOrderUser erpWorkTaskOrderUser = new ErpWorkTaskOrderUser();
				String[] oneUserArr = userArr[i].split("@");
				String waiQinId = oneUserArr[0];
				String waiQinName = oneUserArr[1];
				//主键
				erpWorkTaskOrderUser.setId(Guid.guid());
				//创建时间
				erpWorkTaskOrderUser.setCreateTime(new Date());
				//外勤id
				erpWorkTaskOrderUser.setOwnerUserId(waiQinId);
				erpWorkTaskOrderUser.setOwnerUserName(waiQinName);
				//任务id
				erpWorkTaskOrderUser.setOwnerWorkTaskOrderId(orderId);
				//数据状态
				erpWorkTaskOrderUser.setDataState("1");
				mapper.insert(erpWorkTaskOrderUser);
			}
		}
	}
	/**
	 * 根据外勤人员id查询关联订单
	 * @param userId
	 * @return
	 */
	public List<ErpWorkTaskOrderView> selectByUserId(String userId) {
		//查询所有相关的工单
		List<ErpWorkTaskOrderView> listErpWorkTask = new ArrayList<ErpWorkTaskOrderView>();
		List<ErpWorkTaskOrderUser> list = mapper.selectByUserId(userId);
		for(ErpWorkTaskOrderUser erpWorkTaskOrderUser : list){
			ErpWorkTaskOrderView erpWorkTaskOrderView = erpWorkTaskOrderMapper.selectByOutPutUserId(erpWorkTaskOrderUser.getOwnerWorkTaskOrderId());
			String[] arr = erpWorkTaskOrderView.getOutputUserId().split(",");
			if(arr==null){
				listErpWorkTask.add(erpWorkTaskOrderView);
			}else {
				for(String string : arr){
					if(!(string.equals(userId))){
						continue;
					}else {
						listErpWorkTask.add(erpWorkTaskOrderView);
					}
				}
			}
		}
		return listErpWorkTask;
	}
}

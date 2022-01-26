package com.edgedo.sys.service;
		
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpWorkTask;
import com.edgedo.sys.entity.ErpWorkTaskOrder;
import com.edgedo.sys.entity.ErpWorkTaskOrderUser;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.mapper.*;
import com.edgedo.sys.queryvo.ErpChangeNetSumMonthView;
import com.edgedo.sys.queryvo.ErpWorkTaskOrderQuery;
import com.edgedo.sys.queryvo.ErpWorkTaskOrderView;
import com.edgedo.sys.queryvo.ErpWorkTaskView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpWorkTaskOrderService {
	
	
	@Autowired
	private ErpWorkTaskOrderMapper mapper;

	@Autowired
	private ErpWorkTaskMapper erpWorkTaskMapper;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private ErpWorkTaskOrderUserMapper erpWorkTaskOrderUserMapper;

	@Autowired
	private ErpOutputuserTaskWorkMapper erpOutputuserTaskWorkMapper;

	@Autowired
	private ErpOutputuserTaskWorkService erpOutputuserTaskWorkService;


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpWorkTaskOrderView> listPage(List<ErpWorkTaskOrderView> list,ErpWorkTaskOrderQuery query){
//		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}

	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpWorkTaskOrder voObj) {

		//判断存不存在这个任务
		ErpWorkTask erpWorkTask = erpWorkTaskMapper.selectByOrderId(voObj.getOwnerWorkTaskId());
		if(erpWorkTask==null){
			return "无法对应到任务派单,请查看任务ID是否填写正确";
		}else if (!(erpWorkTask.getTaskState().equals("0") && erpWorkTask.getTaskState().equals("1"))){
			return "该任务正在进行或已经完成,请更换任务";
		}else {
			voObj.setId(Guid.guid());
			String waiQin = voObj.getOutputUserName();
			//多个外勤人员
			String waiQinId = "";
			String waiQinName = "";
			String[] userArr = waiQin.split(",");
			if(userArr.equals("")){
				//一个外勤人员
				String[] oneUserArr = waiQin.split("@");
				waiQinId =oneUserArr[0];
				waiQinName = oneUserArr[1];
			}else {
				for(int i = 0;i<userArr.length;i++){
					//一个外勤人员
					String[] oneUserArr = userArr[i].split("@");
					if(waiQinId.equals("") && waiQinName.equals("")){
						waiQinId =oneUserArr[0];
						waiQinName = oneUserArr[1];
					}else {
						waiQinId = waiQinId+","+oneUserArr[0];
						waiQinName = waiQinName+","+oneUserArr[1];
					}
				}
			}
			voObj.setOutputUserId(waiQinId);
			voObj.setOutputUserName(waiQinName);
			voObj.setCreateTime(new Date());
			voObj.setDataState("1");
			voObj.setTaskOrderState("1");//已确认
			mapper.insert(voObj);
			//更新任务表
			String state = "2";
			erpWorkTaskMapper.updateByTaskState(state,voObj.getOwnerWorkTaskId());
			return "";
		}

	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(ErpWorkTaskOrder voObj) {
		String waiQin = voObj.getOutputUserName();
		//多个外勤人员
		String waiQinId = "";
		String waiQinName = "";
		String[] userArr = waiQin.split(",");
		if(userArr.equals("")){
			//一个外勤人员
			String[] oneUserArr = waiQin.split("@");
			waiQinId =oneUserArr[0];
			waiQinName = oneUserArr[1];
		}else {
			for(int i = 0;i<userArr.length;i++){
				//一个外勤人员
				String[] oneUserArr = userArr[i].split("@");
				if(waiQinId.equals("") && waiQinName.equals("")){
					waiQinId =oneUserArr[0];
					waiQinName = oneUserArr[1];
				}else {
					waiQinId = waiQinId+","+oneUserArr[0];
					waiQinName = waiQinName+","+oneUserArr[1];
				}
			}
		}
		voObj.setOutputUserId(waiQinId);
		voObj.setOutputUserName(waiQinName);
		mapper.updateById(voObj);
		return "";
	}

	public List<String> updateOutUser(String waiQin) {
		List<String> list = new ArrayList<String>();
		//多个外勤人员
		String waiQinId = "";
		String waiQinName = "";
		String[] userArr = waiQin.split(",");
		if(userArr.equals("")){
			//一个外勤人员
			String[] oneUserArr = waiQin.split("@");
			waiQinId =oneUserArr[0];
			waiQinName = oneUserArr[1];
			list.add(0,waiQinId);
			list.add(1,waiQinName);
		}else {
			for(int i = 0;i<userArr.length;i++){
				//一个外勤人员
				String[] oneUserArr = userArr[i].split("@");
				if(i==0){
					waiQinId =oneUserArr[0];
					waiQinName = oneUserArr[1];
				}else {
					waiQinId = waiQinId+","+oneUserArr[0];
					waiQinName = waiQinName+","+oneUserArr[1];
				}
				list.add(0,waiQinId);
				list.add(1,waiQinName);
			}
		}
		return list;
	}



	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpWorkTaskOrder voObj) {
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
	public ErpWorkTaskOrder loadById(String id) {
		return mapper.selectById(id);
	}


	/**
	 * 点击派单  创建任务工单表
	 * @param erpWorkTask
	 */
    public void insertByErpWorkTask(ErpWorkTask erpWorkTask) {
    	ErpWorkTaskOrder erpWorkTaskOrder = new ErpWorkTaskOrder();
    	//主键
		erpWorkTaskOrder.setId(Guid.guid());
		//创建时间
		erpWorkTaskOrder.setCreateTime(new Date());
		//所属任务ID
		erpWorkTaskOrder.setOwnerWorkTaskId(erpWorkTask.getId());
		//任务标题
		erpWorkTaskOrder.setOwnerWorkTaskTitle(erpWorkTask.getTaskTitle());
		//任务描述
		erpWorkTaskOrder.setOwnerWorkTaskDesc(erpWorkTask.getTaskDesc());
		//工单状态
		erpWorkTaskOrder.setTaskOrderState("0");
		//加班
		erpWorkTaskOrder.setIsOverWork("0");
		//数据状态
		erpWorkTaskOrder.setDataState("1");
		mapper.insert(erpWorkTaskOrder);
    }

	/**
	 * 是否存在工作人员
	 * @param ids
	 * @return
	 */
	public ErpWorkTaskOrder selectByIdsOnWorkUser(String ids) {
		return mapper.selectByIdsOnWorkUser(ids);
    }


	/**
	 * 修改工单状态
	 */
	public void updateByTaskState(ErpWorkTaskOrder erpWorkTaskOrder) {
		//确认时间

		mapper.updateByTaskState(erpWorkTaskOrder);
	}

	/**
	 * 点击转出  更新
	 * @param voObj
	 * @return
	 */
	public String updateByOutUserName(ErpWorkTaskOrder voObj) {//有任务id 新的外勤人员put 转出人信息无put
		//更新任务表
		String stateWorkTask = "1";//派单
		erpWorkTaskMapper.updateByTaskState(stateWorkTask,voObj.getOwnerWorkTaskId());
		//将员工工单删除
		erpWorkTaskOrderUserMapper.updateByUserState(voObj.getId());
		//erpOutputuserTaskWorkMapper.updateByWorkId(voObj.getId());
		//将当前的转出工表转成insert
		ErpWorkTaskOrder erpWorkTaskOrder = mapper.selectyIds(voObj.getId());
		ErpWorkTaskOrder erpWorkTaskOrder1 = new ErpWorkTaskOrder();
		//更新id
		erpWorkTaskOrder1.setId(Guid.guid());
		//创建时间
		erpWorkTaskOrder1.setCreateTime(new Date());
		//所属任务id
		erpWorkTaskOrder1.setOwnerWorkTaskId(erpWorkTaskOrder.getOwnerWorkTaskId());
		//新的外勤人员
		List<String> list = updateOutUser(voObj.getOutputUserName());
		erpWorkTaskOrder1.setOutputUserId(list.get(0));
		erpWorkTaskOrder1.setOutputUserName(list.get(1));
		//任务描述
		erpWorkTaskOrder1.setOwnerWorkTaskDesc(erpWorkTaskOrder.getOwnerWorkTaskDesc());
		//任务标题
		erpWorkTaskOrder1.setOwnerWorkTaskTitle(erpWorkTaskOrder.getOwnerWorkTaskTitle());
		//工单状态
		erpWorkTaskOrder1.setTaskOrderState("0");//未确认
		//数据状态
		erpWorkTaskOrder1.setDataState("1");
		mapper.insert(erpWorkTaskOrder1);
		//更新任务工单表
		//转出时间
		voObj.setOutTime(new Date());
		//转出人Id
		//转出id
		voObj.setOutWorkTaskId(erpWorkTaskOrder1.getId());
		//工单状态
		voObj.setTaskOrderState("3");//转出
		voObj.setDataState("0");//删除该工单
		mapper.updateByOutUserName(voObj);
		//新建关联表
		erpOutputuserTaskWorkService.insertByNewOutPutUser(voObj.getOutputUserName(),voObj.getOwnerWorkTaskId());
		return "";
	}

	/**
	 * 进行判断是否和任务表中限时时间 匹配
	 * @param erpWorkTaskOrder
	 */
	public void selectByWorkTaskTime(ErpWorkTaskOrder erpWorkTaskOrder) {
		//更新工单表
		erpWorkTaskOrder.setTaskOrderState("2");//工单表完成
		mapper.updateByOrderState(erpWorkTaskOrder);//更新工单表
	}

	public void updateByOverWork(ErpWorkTaskOrder erpWorkTaskOrder) {
		mapper.updateByOverWork(erpWorkTaskOrder);
	}

	/**
	 * 删除一个
	 * @param ids
	 */
	public void deleteByIdsOne(String ids) {
		mapper.deleteByIdsOne(ids);
	}

	/**
	 * 未确认工单展示
	 * @return
	 */
	public List<ErpWorkTaskOrderQuery> selectByWorkState() {
		return mapper.selectByWorkState();
	}

	/**
	 * 根据任务id  更新工单的外勤人员
	 * @param waiQinName
	 * @param id
	 */
	public void updateByOutPutUserName(String waiQinName,String id) {
		//多个外勤人员
		String waiQinId = "";
		String waiQin = "";
		String[] userArr = waiQinName.split(",");
		if(userArr.equals("")){
			//一个外勤人员
			String[] oneUserArr = waiQinName.split("@");
			waiQinId =oneUserArr[0];
            waiQin = oneUserArr[1];
		}else {
			for(int i = 0;i<userArr.length;i++){
				//一个外勤人员
				String[] oneUserArr = userArr[i].split("@");
				if(i==0){
					waiQinId =oneUserArr[0];
                    waiQin = oneUserArr[1];
				}else {
					waiQinId = waiQinId+","+oneUserArr[0];
                    waiQin = waiQin+","+oneUserArr[1];
				}
			}
		}
		//查出是否存在外勤
		ErpWorkTaskOrder erpWorkTaskOrder = mapper.selectByWaiQinOne(id);
		if(erpWorkTaskOrder!=null){
			waiQin = erpWorkTaskOrder.getOutputUserName()+","+waiQin;
			waiQinId = erpWorkTaskOrder.getOutputUserId()+","+waiQinId;
		}
		mapper.updateByOutPutUser(waiQinId,waiQin,id);

	}

	/**
	 * 根据工单表id查询
	 * @param ids
	 * @return
	 */
    public ErpWorkTaskOrderView selectByOrderId(String ids) {
    	return mapper.selectByOrderId(ids);
    }

	/**
	 * 查询工作任务表是否存在外勤人员
	 * @param ids
	 * @return
	 */
	public String selectByIdWork(String ids) {
		return mapper.selectByIdWork(ids);
    }

	public List<SysUser> selectByInput() {
		return mapper.selectByInput();
	}

	/**
	 * 根据id添加内勤人员
	 * @param id
	 * @param inputId
	 * @param inputName
	 */
	public void updateByIntput(String id, String inputId, String inputName) {
		mapper.updateByIntput(id,inputId,inputName);
	}

	/**
	 * 工单ID查找任务表
	 * @param id
	 * @return
	 */
	public List<ErpWorkTaskOrderView> selectByWorkId(String id) {
		List<ErpWorkTaskOrderView> erpWorkTaskOrderList = mapper.selectByWorkId(id);
		return erpWorkTaskOrderList;
	}
}

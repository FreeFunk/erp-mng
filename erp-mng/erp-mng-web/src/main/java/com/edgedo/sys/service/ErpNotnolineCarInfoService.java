package com.edgedo.sys.service;
		
import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpNotnolineCarInfo;
import com.edgedo.sys.entity.ErpUnlocationRemindMsgRec;
import com.edgedo.sys.mapper.ErpNotnolineCarInfoMapper;
import com.edgedo.sys.mapper.ErpUnlocationRemindMsgRecMapper;
import com.edgedo.sys.queryvo.ErpNotnolineCarInfoQuery;
import com.edgedo.sys.queryvo.ErpNotnolineCarInfoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpNotnolineCarInfoService {
	
	
	@Autowired
	private ErpNotnolineCarInfoMapper mapper;

	@Autowired
	private ErpUnlocationRemindMsgRecMapper erpUnlocationRemindMsgRecMapper;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpNotnolineCarInfoView> listPage(ErpNotnolineCarInfoQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}

	/**
	 * 定时任务查询短信记录中是否存在短信的次数  ，更新未定位车辆的发送短信的次数
	 */
	//@Scheduled(cron = "0 0/3 * * * ? ")//3分钟查一次
	//@Scheduled(cron = "0/2 * * * * ? ")//1分钟查一次
	public void updateByNotnoLineCarNum(){
		List<ErpNotnolineCarInfo> listNew = new ArrayList<ErpNotnolineCarInfo>();
		//1.去到短信记录表查询每一个id
		List<ErpNotnolineCarInfo> list = mapper.selectByAll();
		for(ErpNotnolineCarInfo erpNotnolineCarInfo:list){
			//2.返回出现的次数
			int flag = erpUnlocationRemindMsgRecMapper.selectByCarIdNum(erpNotnolineCarInfo.getId());
			erpNotnolineCarInfo.setSendMsgNum(flag);
			listNew.add(erpNotnolineCarInfo);
		}
		//3.更新未定位车辆短信次数
		mapper.updateByAllSendMsgNum(listNew);
	}



	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpNotnolineCarInfo voObj) {
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
	public String update(ErpNotnolineCarInfo voObj) {
		mapper.updateById(voObj);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpNotnolineCarInfo voObj) {
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
	public ErpNotnolineCarInfo loadById(String id) {
		return mapper.selectById(id);
	}


	/**
	 * 根据唯一id 查询多个未上线车辆
	 * @param listId
	 * @return
	 */
    public List<ErpNotnolineCarInfo> selectByListId(List<String> listId) {
    	return mapper.selectByListId(listId);
    }


	/**
	 * 数据库中查出所有的用户信息
	 * @return
	 */
	public List<ErpNotnolineCarInfo> selectByAllInfo(String unlocatonDay) {
		return mapper.selectByAllInfo(unlocatonDay);
	}

	public ErpUnlocationRemindMsgRec selectBySendMessageId(String ownerUnlocationRemindId) {
		return erpUnlocationRemindMsgRecMapper.selectBySendMessageId(ownerUnlocationRemindId);
	}
}

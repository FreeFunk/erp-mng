package com.edgedo.sys.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.edgedo.common.util.Guid;
import com.edgedo.sys.entity.ErpNotnolineCarInfo;
import com.edgedo.sys.entity.ErpUnlocationRemindMsgRec;
import com.edgedo.sys.entity.SysPhoneMsgRec;
import com.edgedo.sys.mapper.ErpUnlocationRemindMsgRecMapper;
import com.edgedo.sys.mapper.SysPhoneMsgRecMapper;
import com.edgedo.sys.queryvo.ErpNotnolineCarInfoView;
import com.edgedo.sys.queryvo.ErpUnlocationRemindMsgRecQuery;
import com.edgedo.sys.queryvo.ErpUnlocationRemindMsgRecView;
import com.edgedo.timedtask.SendMessages;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class ErpUnlocationRemindMsgRecService {


	@Autowired
	private ErpUnlocationRemindMsgRecMapper mapper;

	@Autowired
	private ErpUnlocationRemindMsgRecMapper erpUnlocationRemindMsgRecMapper;

	@Autowired
	private SysPhoneMsgRecMapper sysPhoneMsgRecMapper;

	@Autowired
	SysConfigService sysConfigService;

	@Autowired
	private SysPhoneMsgRecService sysPhoneMsgRecService;

	SendMessages sendMessages = new SendMessages();

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class,readOnly = true)
	public List<ErpUnlocationRemindMsgRecView> listPage(ErpUnlocationRemindMsgRecQuery query){
		List list = mapper.listPage(query);
		query.setList(list);
		return list;
	}

	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(ErpUnlocationRemindMsgRec voObj) {
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
	public String update(ErpUnlocationRemindMsgRec voObj) {
		mapper.updateById(voObj);
		return "";
	}

	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(ErpUnlocationRemindMsgRec voObj) {
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
	public ErpUnlocationRemindMsgRec loadById(String id) {
		return mapper.selectById(id);
	}


	/**
	 * 将多条短信的记录插入到新表中
	 * @param list
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertNewSendManyMessage(List<ErpNotnolineCarInfo> list) {
		//短信发送的限制周期
		String sendMessageDay = sysConfigService.getValueById("SEND_MESSAGE_DAY");
		for(ErpNotnolineCarInfo erpNotnolineCarInfo:list){
			String carPlateNum = erpNotnolineCarInfo.getCarPlateNum();
			Map<String,Object> map = new HashMap<>();
			map.put("carPlateNum",carPlateNum);
			map.put("sendMessageDay",sendMessageDay);
			//查询该车牌号三个月内是否发送过短信记录
			int countSendNum = erpUnlocationRemindMsgRecMapper.countSendNum(map);
			//判断是否发送过短信
			if(countSendNum==0){
				//创建发送短信对象
				ErpUnlocationRemindMsgRec erpUnlocationRemindMsgRec = new ErpUnlocationRemindMsgRec();
				//如果没有发送过短信直接插入待发信息表
				erpUnlocationRemindMsgRec.setCreateTime(new Date());//创建时间
				erpUnlocationRemindMsgRec.setOwnerUnlocationRemindId(erpNotnolineCarInfo.getId());//未定位车辆ID
				erpUnlocationRemindMsgRec.setCarPlateNum(erpNotnolineCarInfo.getCarPlateNum());//车牌号
				String msg = "【轩易行平台】您的车"+carPlateNum+"目前处于离线状态,出车时请及时检修,以免被查罚款,北斗电话:15133532012。";
				erpUnlocationRemindMsgRec.setMsgText(msg);//发送内容
				erpUnlocationRemindMsgRec.setContactPerson(erpNotnolineCarInfo.getContactPerson());//联系人
				erpUnlocationRemindMsgRec.setContactPhoneNum(erpNotnolineCarInfo.getContactPhoneNum());//联系电话
				erpUnlocationRemindMsgRec.setSendState("0");//发送状态  0待发送
				erpUnlocationRemindMsgRec.setDataState("1");
				erpUnlocationRemindMsgRec.setSendType("OFFLINE_TZ_MSG");
				//插入发送信息表
				insert(erpUnlocationRemindMsgRec);
			}
		}
	}

	/**
	 * 插入信息是拼装车牌号，一个手机号只发送一次
	 * @param list
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertSendManyMessageNew(List<ErpNotnolineCarInfo> list) {
		list.stream().filter(bean -> {
			bean.setTerminalChangshang("【轩易行平台】您的车"+bean.getCarPlateNum()
					+"目前处于离线状态,出车时请及时检修,以免被查罚款,北斗电话:15133532012。退订回TD。");
			return true;
		}).collect(Collectors.toList());
		mapper.insertAll(list);
	}


	/**
	 * 向所有未定位车辆发送消息
	 * @param list
	 */
	public void insertNewSendAllMessage(List<ErpNotnolineCarInfo> list) {
		for(ErpNotnolineCarInfo erpNotnolineCarInfo:list){
			//查询该车牌号三个月内是否发送过短信记录
			int flog = erpUnlocationRemindMsgRecMapper.selectByCarPlateNumDate(erpNotnolineCarInfo.getCarPlateNum());
			int flog2 = erpUnlocationRemindMsgRecMapper.selectByCarPlateNum(erpNotnolineCarInfo.getCarPlateNum());

			//创建发送短信对象
			ErpUnlocationRemindMsgRec erpUnlocationRemindMsgRec = new ErpUnlocationRemindMsgRec();
			//判断是否发送过短信
			if(flog2==0){//没有给该车发过短信
				//如果没有发送过短信直接插入待发信息表
				erpUnlocationRemindMsgRec.setId(Guid.guid());
				erpUnlocationRemindMsgRec.setCreateTime(new Date());//创建时间
				erpUnlocationRemindMsgRec.setOwnerUnlocationRemindId(erpNotnolineCarInfo.getId());//未定位车辆ID
				erpUnlocationRemindMsgRec.setCarPlateNum(erpNotnolineCarInfo.getCarPlateNum());//车牌号
				String msg = "【轩易行平台】"+erpNotnolineCarInfo.getCarPlateNum()+"已离线,请及时检修,以免被罚款,电话:15133532012";
				erpUnlocationRemindMsgRec.setMsgText(msg);//发送内容
				erpUnlocationRemindMsgRec.setContactPerson(erpNotnolineCarInfo.getContactPerson());//联系人
				erpUnlocationRemindMsgRec.setContactPhoneNum(erpNotnolineCarInfo.getContactPhoneNum());//联系电话
				//erpUnlocationRemindMsgRec.setSendTime(new Date());//发送时间
				erpUnlocationRemindMsgRec.setSendState("0");//发送状态  0待发送
				erpUnlocationRemindMsgRec.setDataState("1");
				//插入发送信息表
				mapper.insert(erpUnlocationRemindMsgRec);
			}else if(flog>0){//如果该车存在判断三个月之内没有发送过短信或者发送以时间以超过3个月
				//如果没有发送过短信直接插入待发信息表
				erpUnlocationRemindMsgRec.setId(Guid.guid());
				erpUnlocationRemindMsgRec.setCreateTime(new Date());//创建时间
				erpUnlocationRemindMsgRec.setOwnerUnlocationRemindId(erpNotnolineCarInfo.getId());//未定位车辆ID
				erpUnlocationRemindMsgRec.setCarPlateNum(erpNotnolineCarInfo.getCarPlateNum());//车牌号
				String msg = "【轩易行平台】"+erpNotnolineCarInfo.getCarPlateNum()+"已离线,请及时检修,以免被罚款,电话:15133532012";
				erpUnlocationRemindMsgRec.setMsgText(msg);//发送内容
				erpUnlocationRemindMsgRec.setContactPerson(erpNotnolineCarInfo.getContactPerson());//联系人
				erpUnlocationRemindMsgRec.setContactPhoneNum(erpNotnolineCarInfo.getContactPhoneNum());//联系电话
				//erpUnlocationRemindMsgRec.setSendTime(new Date());//发送时间
				erpUnlocationRemindMsgRec.setSendState("0");//发送状态  0待发送
				erpUnlocationRemindMsgRec.setDataState("1");
				//插入发送信息表
				mapper.insert(erpUnlocationRemindMsgRec);
			}
		}

	}

	public List<ErpUnlocationRemindMsgRec> selectByCarId(String ids) {
		return mapper.selectByCarIdList(ids);
	}

	/**
	 * 单个车辆发送信息
	 * @param
	 */
	/*public void sendOneMessages(ErpUnlocationRemindMsgRec erpUnlocationRemindMsgRec) {
		//String sendMessageDay = sysConfigService.getValueById("SEND_MESSAGE_DAY");
		sendMessages.sendOneMessages(erpUnlocationRemindMsgRec);
		update(erpUnlocationRemindMsgRec);
	}*/


    public List<ErpUnlocationRemindMsgRec> selectByPhoneNum(String moblie) {
		return erpUnlocationRemindMsgRecMapper.selectByPhoneNum(moblie);
	}

	/**
	 * 判断落地短信状态
	 * @param reqBody
	 */
	public void judgeSmsStatus(String reqBody) {
		//解析json数据
		JSONObject jsonObject = new JSONObject(reqBody);
		String code = jsonObject.getString("code");//短信发送状态
		String mobile = jsonObject.getString("mobile");//手机号
		String msg = jsonObject.getString("msg");//状态码
		String smsid = jsonObject.getString("smsid");//平台短信ID
		String reportDate = jsonObject.getString("report_date");//创建时间
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=null;
		try {
			date=sDateFormat.parse(reportDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date sendDate = date;
		String uid= jsonObject.getString("uid");//发送请求的uid

		//创建短信发送状态对象
		SysPhoneMsgRec sysPhoneMsgRec = new SysPhoneMsgRec();
		sysPhoneMsgRec.setPhoneNum(mobile);//手机号
		sysPhoneMsgRec.setSmsId(smsid);//短信落地ID
		sysPhoneMsgRec.setResponseCode(msg);//回执码(短信落地状态)
		if(code.equals("0")){
			sysPhoneMsgRec.setIsSuccess("2");//2 --- 发送成功
		}else{
			sysPhoneMsgRec.setIsSuccess("-1");//-1 -- 发送失败
		}
		sysPhoneMsgRecService.insert(sysPhoneMsgRec);

		List<ErpUnlocationRemindMsgRec> erpUnlocationRemindMsgRecList = mapper.selectByPhoneNum(mobile);
		for(ErpUnlocationRemindMsgRec erpUnlocationRemindMsgRec:erpUnlocationRemindMsgRecList){
			if(erpUnlocationRemindMsgRec.getSmsId()==null){
				erpUnlocationRemindMsgRec.setSmsId(smsid);
				SysPhoneMsgRec phoneMsgRec = sysPhoneMsgRecService.selectBySmsid(smsid);
				phoneMsgRec.setUserId(erpUnlocationRemindMsgRec.getId());
				phoneMsgRec.setContent(erpUnlocationRemindMsgRec.getMsgText());
				erpUnlocationRemindMsgRec.setSendState(phoneMsgRec.getIsSuccess());
				erpUnlocationRemindMsgRec.setSendTime(sendDate);
				sysPhoneMsgRecService.update(phoneMsgRec);
				update(erpUnlocationRemindMsgRec);
				break;
			}
		}
	}

	public void updateBySendState(ErpUnlocationRemindMsgRec erpUnlocationRemindMsgRec) {
		mapper.updateBySendState(erpUnlocationRemindMsgRec);
	}

	public void updateBySendStateNew(ErpUnlocationRemindMsgRec erpUnlocationRemindMsgRec) {
		mapper.updateBySendStateNew(erpUnlocationRemindMsgRec);
	}

	public List<ErpUnlocationRemindMsgRec> selectByAllNewAllInfo() {
		return mapper.selectByAllNewAllInfo();
	}

	public List<ErpUnlocationRemindMsgRec> selectBySendTypeGroupByPhoneNum(String sendType) {
		return mapper.selectBySendTypeGroupByPhoneNum(sendType);
	}

	public List<ErpUnlocationRemindMsgRec> selectBySendType(String sendType) {
		return mapper.selectBySendType(sendType);
	}
}

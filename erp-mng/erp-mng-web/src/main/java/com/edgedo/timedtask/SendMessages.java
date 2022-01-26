package com.edgedo.timedtask;

import com.edgedo.phonemsgclient.ISysPhoneMsgClientService;
import com.edgedo.sys.entity.ErpUnlocationRemindMsgRec;
import com.edgedo.sys.entity.SysPhoneMsgRec;
import com.edgedo.sys.service.ErpUnlocationRemindMsgRecService;
import com.edgedo.sys.service.SysConfigService;
import com.edgedo.sys.service.SysPhoneMsgRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class SendMessages {

    @Autowired
    SysConfigService sysConfigService;
    @Autowired
    ErpUnlocationRemindMsgRecService erpUnlocationRemindMsgRecService;
    @Autowired
    ISysPhoneMsgClientService sysPhoneMsgClientService;
    @Autowired
    SysPhoneMsgRecService sysPhoneMsgRecService;


  /**
   *@Author:ZhaoSiDa
   *@Description: 发送离线通知短信
   *@DateTime: 2020/4/16 10:21
   */
    //@Scheduled(cron = "0 0/1 * * * ?")//1分钟查一次
    public void sendAllsMessage() {
        //1.到新表中查询 发送类型为0 待发送的车辆  一次查100条
        List<ErpUnlocationRemindMsgRec> list = erpUnlocationRemindMsgRecService.selectBySendType("OFFLINE_TZ_MSG");
        for(ErpUnlocationRemindMsgRec erpUnlocationRemindMsgRec : list){
            try {
                String templateId = erpUnlocationRemindMsgRec.getSendType();
                String phoneNum = erpUnlocationRemindMsgRec.getContactPhoneNum();
                String carPlateNum =erpUnlocationRemindMsgRec.getCarPlateNum();
                Map<String,String> modelParam = new HashMap<>();
                modelParam.put("CAR_PLATE_NUM",carPlateNum);
                //String result = null;
                String result = sysPhoneMsgClientService.sendPhoneMsg(templateId,phoneNum,modelParam);
                //获取返回码判断短信是否发送成功
                if(result==null){
                    erpUnlocationRemindMsgRec.setSendState("1");
                    erpUnlocationRemindMsgRec.setSendTime(new Date());
                    erpUnlocationRemindMsgRec.setSmsId("");
                    erpUnlocationRemindMsgRecService.updateBySendState(erpUnlocationRemindMsgRec);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     *@Author:ZhaoSiDa
     *@Description: 发送车辆定位检查通知短信
     *@DateTime: 2020/4/16 10:21
     */
    //@Scheduled(cron = "0 0/1 * * * ?")//1分钟查一次
    public void sendRemindCheckGpsPhoneMsg() {
        //1.到新表中查询 发送类型为0 待发送的车辆  一次查100条
        List<ErpUnlocationRemindMsgRec> list = erpUnlocationRemindMsgRecService.selectBySendType("REMIND_CHECK_GPS_TZ_MSG");
        for(ErpUnlocationRemindMsgRec erpUnlocationRemindMsgRec : list){
            try {
                String templateId = erpUnlocationRemindMsgRec.getSendType();
                String phoneNum = erpUnlocationRemindMsgRec.getContactPhoneNum();
                Map<String,String> modelParam = new HashMap<>();
                //String result = null;
                String result = sysPhoneMsgClientService.sendPhoneMsg(templateId,phoneNum,modelParam);
                //获取返回码判断短信是否发送成功
                if(result==null){
                    erpUnlocationRemindMsgRec.setSendState("1");
                    erpUnlocationRemindMsgRec.setSendTime(new Date());
                    erpUnlocationRemindMsgRec.setSmsId("");
                    erpUnlocationRemindMsgRecService.updateBySendState(erpUnlocationRemindMsgRec);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}


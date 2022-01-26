package com.edgedo.timedtask;

import com.edgedo.common.util.Guid;
import com.edgedo.common.util.HttpRequestUtil;
import com.edgedo.common.util.JsonUtils;
import com.edgedo.sys.entity.ErpCarMsg1;
import com.edgedo.sys.entity.ErpCarSimpleInfo;
import com.edgedo.sys.entity.ErpChangeNetRec;

import com.edgedo.sys.entity.ErpCarInfo;
import com.edgedo.sys.queryvo.ErpCarInfoView;
import com.edgedo.sys.queryvo.ErpCarSimpleInfoView;
import com.edgedo.sys.service.ErpCarSimpleInfoService;
import com.edgedo.sys.service.ErpCarInfoService;
import com.edgedo.sys.service.ErpChangeNetRecService;
import com.edgedo.sys.service.SysConfigService;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * @ClassName UpdateChangeNetRec
 * @Description 同步转网记录
 * @Author 床前明月光
 * @Date 2019/7/20 9:04
 **/
@Component
public class UpdateChangeNetRecOut {

    @Autowired
    SysConfigService sysConfigService;
    @Autowired
    ErpCarInfoService erpCarInfoService;
    @Autowired
    ErpChangeNetRecService erpChangeNetRecService;
    @Autowired
    ErpCarSimpleInfoService erpCarSimpleInfoService;

    //@Scheduled(cron = "0 0/1 * * * ?")
    public String tongBuChangeNetRecin(){
        String changeNetUrl = sysConfigService.getValueById("CHANGE_NET_URL_2");
        String cookieUseridCar = sysConfigService.getValueById("COOKIE_USERID_CAR");
        String toTal = toTalNum();
        //String toTal = toTalNum();
        Map<String,String> params = new HashMap<>();
        params.put("requestParam.equal.platformId","70859928-7aae-4842-b67a-60f2b7ff8f77");
        params.put("requestParam.equal.createTimeBegin","1564588800000");
        params.put("requestParam.equal.createTimeEnd","1567267199000");
        params.put("requestParam.page","1");
        params.put("requestParam.rows",toTal);
        params.put("requestParam.like.vehicleNo","冀C");
        Map<String,String> header = new HashMap<>();
        header.put("Cookie",cookieUseridCar);
        String s = "";
        try {
            s = HttpRequestUtil.sendPostRequest(changeNetUrl,params,header);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public String toTalNum(){
        String changeNetUrl = sysConfigService.getValueById("CHANGE_NET_URL_2");
        String cookieUseridCar = sysConfigService.getValueById("COOKIE_USERID_CAR");
        Map<String,String> params = new HashMap<>();
        params.put("requestParam.equal.platformId","70859928-7aae-4842-b67a-60f2b7ff8f77");
        params.put("requestParam.equal.createTimeBegin","1564588800000");
        params.put("requestParam.equal.createTimeEnd","1567267199000");
        params.put("requestParam.page","1");
        params.put("requestParam.rows","20");
        params.put("requestParam.like.vehicleNo","冀C");
        Map<String,String> header = new HashMap<>();
        header.put("Cookie",cookieUseridCar);
        String s = "";
        String toTal = "";
        try {
            s = HttpRequestUtil.sendPostRequest(changeNetUrl,params,header);
            //总数
            JSONObject jsonObject = new JSONObject(s);
            toTal = jsonObject.optString("Total");
        } catch (IOException e) {
            e.printStackTrace();
            return "请重新登录";
        }
        return toTal;
    }

    //解析时间戳，接收当前时间戳。
    //解析json数据转为list集合
    public List<ErpCarMsg1> analysisJSON(){
        String s = tongBuChangeNetRecin();
        //拿到取到json集合对象 listJson
        //解析json
        List<ErpCarMsg1> listJson = null;
        String s1 = StringUtils.substringAfter(s, ":");
        String s2 = StringUtils.substringBeforeLast(s1, ",");
        System.out.println(s2);
        listJson = JsonUtils.parseList(s2,ErpCarMsg1.class);
        return listJson;
    }

    //将json集合放到数据库中
    public String DepositJsonInDataBase(){
        sysConfigService.updateByValueIdStart("UPDATE_CAR_INFO_DATA_FLAG");
        List<ErpCarMsg1> listJson = analysisJSON();
        //建立存放转网记录对象集合 erpChangeNetRecList
        List<ErpChangeNetRec> erpChangeNetRecList = new ArrayList<>();
        //存放车辆信息对象集合
        //List<ErpCarInfo> erpCarInfoList = erpCarInfoService.selectAll();

        //遍历json集合对象
        for (ErpCarMsg1 erpCarMsgs:listJson) {
            //实例化对象
            ErpChangeNetRec erpChangeNetRec = new ErpChangeNetRec();
            //把json集合对象信息放入转网记录对象集合中，存入json对象ID
            //查找数据库以存放的jsonID和现有存入的转网记录集合做对比，如果ID重复不插入，ID不重复插入。
            //添加信息
            //车牌号
            erpChangeNetRec.setCarPlateNum(erpCarMsgs.getVehicleNo());
            //提交人
            erpChangeNetRec.setSubmitPerson(erpCarMsgs.getApplyUser());
            //转出
            erpChangeNetRec.setChangeNetType("转出");
            //现平台
            erpChangeNetRec.setNowPt(erpCarMsgs.getApplyUnitName());
            //平台数据ID
            erpChangeNetRec.setPlatformID(erpCarMsgs.getId());//存放ID
            //拒绝原因
            erpChangeNetRec.setRefuseReason(erpCarMsgs.getRefuseReason());
            //System.out.println("拒绝原因："+erpCarMsgs.getRefuseReason());
            //车牌颜色
            String color = erpCarMsgs.getPlateColor();
            if(color.equals("2")){
                color = "黄色";
            }else{
                color = "蓝色";
            }
            erpChangeNetRec.setCarPlateColor(color);
            //原平台
            erpChangeNetRec.setOrgPt("中电科卫星导航运营服务有限公司河北办事处");

            String createTime = erpCarMsgs.getCreateTime();
            long it = new Long(createTime);
            Date date = new Date(it);
            //提交时间
            erpChangeNetRec.setSubmitTime(date);
            //信息存放到转网记录对象集合中
            erpChangeNetRecList.add(erpChangeNetRec);
        }

        //以有车辆信息存入对应的转网记录集合中，
        if(erpChangeNetRecList!=null){
            for(ErpChangeNetRec erpChangeNetRecs :erpChangeNetRecList){
                ErpCarSimpleInfoView erpCarSimpleInfo = erpCarSimpleInfoService.selectByCarPlateNum(erpChangeNetRecs.getCarPlateNum());
                //System.out.println("车辆："+erpCarSimpleInfo+erpChangeNetRecs.getCarPlateNum());
                if(erpCarSimpleInfo!=null){
                    //所属车辆ID
                    erpChangeNetRecs.setOwnerCarInfoId(erpCarSimpleInfo.getId());
                    //车架号
                    erpChangeNetRecs.setCarFrameNum(erpCarSimpleInfo.getCarFrameNum());
                    //客户名称
                    erpChangeNetRecs.setCustomerName(erpCarSimpleInfo.getYehuName());
                    //终端厂商
                    erpChangeNetRecs.setTerminalChangshang(erpCarSimpleInfo.getTerminalName());
                    //籍贯
                    String placeOfOrigin = erpCarSimpleInfo.getPlaceOfOrigin();
                    if(placeOfOrigin != null){
                        String[] arr = placeOfOrigin.split("-");
                        //省
                        erpChangeNetRecs.setProvinceName(arr[0]);
                        //市
                        erpChangeNetRecs.setCityName(arr[1]);
                        //县区
                        erpChangeNetRecs.setXianquName(arr[2]);
                        System.out.println("已插入：");
                        System.out.println(erpChangeNetRecs);
                        erpChangeNetRecService.insert(erpChangeNetRecs);
                    }
                }

            }
        }
        //删除重复数据
        erpChangeNetRecService.deleteRepeat();
        return "更新完毕";
    }

   ///封装一个方法 动态遍历当前的JSON字符串
    public String dynamicLoopin(String toTal){
        //List<String> list = new ArrayList<String>();//用于接收返回回来的JSON字符串  一条20
        //判断是否正在更新
        //如果ID=1说明再更新
        //如果ID=0说明更新完毕
        //String toTal = ;
        if(toTal.equals("开始更新")){
            String messg = DepositJsonInDataBase();//进行更新
            if(messg.equals("更新完毕")){
                sysConfigService.updateByValueIdEnd("UPDATE_CAR_INFO_DATA_FLAG");
            }
            return "";
        }else {
            //erpCarSimpleInfoMapper.deleteByTime(betweenDateStr,currDateStr);
            //更新syconfig更新信息标识  0 为更新 1 更新
            sysConfigService.updateByValueIdStart("UPDATE_CAR_INFO_DATA_FLAG");
            return "请重新登陆";
        }
    }
}

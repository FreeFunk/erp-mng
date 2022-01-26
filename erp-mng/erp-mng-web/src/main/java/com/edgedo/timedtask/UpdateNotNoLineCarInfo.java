package com.edgedo.timedtask;

import com.edgedo.common.util.Guid;
import com.edgedo.common.util.HttpRequestUtil;
import com.edgedo.sys.entity.CarSimpleInfoVo;
import com.edgedo.sys.entity.ErpCarSimpleInfo;
import com.edgedo.sys.entity.ErpNotnolineCarInfo;
import com.edgedo.sys.entity.ErpNotnolineCarInfoVo;
import com.edgedo.sys.mapper.ErpNotnolineCarInfoMapper;
import com.edgedo.sys.service.SysConfigService;
import io.swagger.models.auth.In;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UpdateNotNoLineCarInfo {

    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    ErpNotnolineCarInfoMapper erpNotnolineCarInfoMapper;

    //1--7天  2--30天  3--90天
    Integer todayNum=1;

    //页数
    Integer flagNum = 1;//设置第一页

    //UNLOCATION_CAR_INFO
    public String tongBuChangeNetRecSimpleInfo(Integer loopNum,Integer todayNum){
        String changeNetUrl = sysConfigService.getValueById("NOTONLINE_CAR_INFO");
        String cookieUseridCar = sysConfigService.getValueById("COOKIE_USERID_CAR");
        String params = "requestParam.equal.type="+todayNum+"&requestParam.page=1"
                +"&requestParam.rows=100"+"&sortname=sysutc"+"&sortorder=asc";
        Map<String,String> header = new HashMap<>();
        header.put("Cookie",cookieUseridCar);
        String s = "";
        try {
            s = HttpRequestUtil.sendGetRequestHeader(changeNetUrl,params,header);
            //System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    //封装一个方法 动态遍历当前的JSON字符串
//    @Scheduled(cron = "0/5 * * * * ?")
    public String dynamicLoop(){
        List<String> list = new ArrayList<String>();//用于接收返回回来的JSON字符串  一条20
        String toTal = toTalNum(todayNum);
        if(toTal.equals("请重新登录")){
            return toTal;
        }else {
            Integer toTalNum = new Integer(toTal);
            //更新syconfig更新信息标识  0 为更新 1 更新
            sysConfigService.updateByValueIdStart("UPDATE_NOTONLINE_CAR_INFO_FLAG");
            Integer yeShu = (toTalNum/100)+1;
            //1.设置一次要插入的数据  遍历边界 100 6236  8691
            for(int i = 0;i<yeShu;i++){//遍历完后200条数据1 2 2 3 3 49 10 11
                String rows = tongBuChangeNetRecSimpleInfo(flagNum,todayNum); //JSON数据结构 {"Rows":集合,"Total":"车辆总数"}
                list.add(rows);
                flagNum += 1;
            }
            analySis(list);//进行解析
            //进行7天的统计，加一
            todayNum+=1;
            //重新从1开始
            flagNum = 1;
            if(todayNum<=3){
                dynamicLoop();
            }
            //并且删除前一天创建的时间
            String currDateStr = getDateStr(new Date());//当前时间
            String betweenDateStr = getDateBetweenStr(new Date());//前一个小时的时间
            //erpNotnolineCarInfoMapper.deleteByTime(betweenDateStr,currDateStr);
            //更新syconfig更新信息标识  0 为更新 1 更新
            sysConfigService.updateByValueIdEnd("UPDATE_NOTONLINE_CAR_INFO_FLAG");
            todayNum = 1;
            return "";
        }
    }

    /**
     * 两个小时之间
     * @return
     */
    public String getDateBetweenStr(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, -1);// 24小时制
        date = cal.getTime();
        String betweenDateStr = simpleDateFormat.format(date);
        return betweenDateStr;
    }


    /**
     * 获取当前字符串时间
     * @return
     */
    public String getDateStr(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currDateStr = simpleDateFormat.format(date);
        return currDateStr;
    }

    //解析JSON 插入数据库
    public void analySis(List<String> list){//list size:0-9  erpNotnolineCarInfoMapper
        List<ErpNotnolineCarInfo> listErpNotnolineCarInfoInsert = new ArrayList<ErpNotnolineCarInfo>();//插入
        List<ErpNotnolineCarInfo> listErpNotnolineCarInfoUpdate = new ArrayList<ErpNotnolineCarInfo>();//更新
        for(int j = 0;j<list.size();j++){
            //获取JSONObject对象  一层解析
            JSONObject jsonObject = new JSONObject(list.get(j));
            //拿到Rows的数组一共20个  二层解析  ->集合 20条
            JSONArray jsonArray = jsonObject.optJSONArray("Rows");
            for(int i = 0;i<jsonArray.length();i++) {
                //三层解析  --->一个JSONObject对象  简单的key:value
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                //封装对象
                ErpNotnolineCarInfo erpNotnolineCarInfo = new ErpNotnolineCarInfo();
                //车牌号   vehicleNo
                String vehicleNo =jsonObject1.optString("vehicleNo");
                if (!vehicleNo.matches(".*冀C.*")){
                    continue;
                }
                erpNotnolineCarInfo.setCarPlateNum(vehicleNo);
                //车主/业户   companyname
                String companyname = jsonObject1.optString("companyname");
                erpNotnolineCarInfo.setYehuName(companyname);
                //联系人   vehicleOwnerName
                String vehicleOwnerName =jsonObject1.optString("vehicleOwnerName");
                erpNotnolineCarInfo.setContactPerson(vehicleOwnerName);
                //联系电话   vehicleOwnerPhone
                String vehicleOwnerPhone =jsonObject1.optString("vehicleOwnerPhone");
                erpNotnolineCarInfo.setContactPhoneNum(vehicleOwnerPhone);
                //终端厂商   oemName
                String oemName =jsonObject1.optString("oemName");
                erpNotnolineCarInfo.setTerminalChangshang(oemName);
                //终端型号   tmodelName
                String tmodelName =jsonObject1.optString("tmodelName");
                erpNotnolineCarInfo.setTerminalModel(tmodelName);
                //最后定位时间  sysutc
                String sysutc =jsonObject1.optString("sysutc");
                Long sysutcL = Long.valueOf(sysutc);
                Date sysutcTime = new Date(sysutcL);
                erpNotnolineCarInfo.setLastLocaltionTime(sysutcTime);
                //主键
                erpNotnolineCarInfo.setId(Guid.guid());
                //创建时间
                erpNotnolineCarInfo.setCreateTime(new Date());
                //设置未定位天数
                if(todayNum==1){
                    erpNotnolineCarInfo.setUnlocatonDay("0");//7天
                }else if(todayNum==2){
                    erpNotnolineCarInfo.setUnlocatonDay("1");//30天
                }else if(todayNum==3){
                    erpNotnolineCarInfo.setUnlocatonDay("2");//90天
                }
                //数据状态
                erpNotnolineCarInfo.setDataState("1");

//                //判断是否有重复的
                int flag = erpNotnolineCarInfoMapper.selectByCarNumAndLastTime(vehicleNo,erpNotnolineCarInfo.getUnlocatonDay());
//
//               // 经度  lat
//                String lat =jsonObject1.optString("lat");
//                //纬度    lon
//                String lon =jsonObject1.optString("lon");

                if(flag==0){
                    listErpNotnolineCarInfoInsert.add(erpNotnolineCarInfo);
                }else{
                    listErpNotnolineCarInfoUpdate.add(erpNotnolineCarInfo);
                }
            }
        }
        if (listErpNotnolineCarInfoInsert.size()!=0){
            insert(listErpNotnolineCarInfoInsert);
        }
        if(listErpNotnolineCarInfoUpdate.size()!=0){
//            String str = String.valueOf(todayNum-1);
//            erpNotnolineCarInfoMapper.deteleByAll(listErpNotnolineCarInfoUpdate,str);//将要更新的数据先删除再将最新的记录插入
            update(listErpNotnolineCarInfoUpdate);
        }

    }

    //进行判断是否存在数据  ，不再则插入，在更新
    public void insert(List<ErpNotnolineCarInfo> list){
        erpNotnolineCarInfoMapper.insertByList(list);
    }
    public void update(List<ErpNotnolineCarInfo> list){
        erpNotnolineCarInfoMapper.updateByList(list);
    }



    //计算页数
    public String toTalNum(Integer todayNum){
        String changeNetUrl = sysConfigService.getValueById("NOTONLINE_CAR_INFO");
        String cookieUseridCar = sysConfigService.getValueById("COOKIE_USERID_CAR");
        String params = "requestParam.equal.type="+todayNum+"&requestParam.page=1"
                +"&requestParam.rows=100"+"&sortname=sysutc"+"&sortorder=asc";
        Map<String,String> header = new HashMap<>();
        header.put("Cookie",cookieUseridCar);
        String s = "";
        String toTal = "";
        try {
            https://truck.gghypt.net/carApp/vehicleStatisticsAction!findNotOnlineVehicleList.action
            s = HttpRequestUtil.sendGetRequestHeader(changeNetUrl,params,header);
            //总数
            JSONObject jsonObject = new JSONObject(s);
            toTal = jsonObject.optString("Total");
        } catch (Exception e) {
            e.printStackTrace();
            return "请重新登录";
        }
        return toTal;
    }

    public String insertNotLineCar() {
        String changeNetUrl = sysConfigService.getValueById("NOTONLINE_CAR_INFO");
        String cookieUseridCar = sysConfigService.getValueById("COOKIE_USERID_CAR");
        startTb("1",changeNetUrl,cookieUseridCar);
        startTb("2",changeNetUrl,cookieUseridCar);
        startTb("3",changeNetUrl,cookieUseridCar);
        return "";
    }

    public void startTb(String type,String changeNetUrl,String cookieUseridCar){

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean flag = true;
                    int toTal = 0;
                    while (flag){
                        int t = 1;
                        for(int j =0;j<t;j++){
                            String params = "";
                            params += "requestParam.equal.type="+type;
                            params += "&requestParam.page="+(j+1);
                            params += "&requestParam.rows=1000";
                            params += "&sortname=sysutc";
                            params += "&sortorder=asc";
                            Map<String,String> header = new HashMap<>();
                            header.put("Cookie",cookieUseridCar);
                            String s = "";
                            try {
                                s = HttpRequestUtil.sendGetRequestHeader(changeNetUrl,params,header);
                                JSONObject jsonObject = new JSONObject(s);
                                toTal = Integer.valueOf(jsonObject.optString("Total"));
                                t = toTal/1000 +1;
                                String rows = jsonObject.optString("Rows");
                                List<ErpNotnolineCarInfoVo> list = com.alibaba.fastjson.JSON.parseArray(rows,ErpNotnolineCarInfoVo.class);
                              /*  //筛选“冀C的车牌号出来
                                List<ErpNotnolineCarInfoVo> notOnlineCarList = null;
                                notOnlineCarList = list.stream()
                                        .filter(ErpNotnolineCarInfoVo->(ErpNotnolineCarInfoVo.getVehicleNo().contains("冀C")))
                                        .collect(Collectors.toList());*/

                                if(list!=null && list.size()>0){
                                    if(type.equals("1")){
                                        erpNotnolineCarInfoMapper.insertType1(list);
                                    }
                                    if(type.equals("2")){
                                        erpNotnolineCarInfoMapper.insertType2(list);
                                    }
                                    if(type.equals("3")){
                                        erpNotnolineCarInfoMapper.insertType3(list);
                                    }
                                }
                                System.out.println(toTal);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        //统计数量是否全
                        int countByTypeId = erpNotnolineCarInfoMapper.countByTypeId(type);
                        if(countByTypeId == toTal){
                            flag = false;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }
}

package com.edgedo.timedtask;

import com.aliyun.oss.internal.ResponseParsers;
import com.edgedo.common.util.Guid;
import com.edgedo.common.util.HttpRequestUtil;
import com.edgedo.common.util.JsonUtils;
import com.edgedo.sys.entity.*;
import com.edgedo.sys.mapper.ErpCarDetailedInfoMapper;
import com.edgedo.sys.mapper.ErpCarInfoMapper;
import com.edgedo.sys.mapper.ErpCarSimpleInfoMapper;
import com.edgedo.sys.mapper.ErpChangeNetRecMapper;
import com.edgedo.sys.queryvo.ErpCarInfoView;
import com.edgedo.sys.queryvo.SysXianquView;
import com.edgedo.sys.service.SysConfigService;
import com.edgedo.sys.service.SysXianquService;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName UpdateChangeNetRec
 * @Description 同步转网记录
 * @Author 床前明月光
 * @Date 2019/7/20 9:04
 **/
//@Component
public class UpdateQghyptCarInfo {

    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private SysXianquService sysXianquService;

    Integer flagNum = 1;//设置第一页
    private Integer rowCount = 0;
    private Integer threadEndCount = 0;

    //主键
    //int id = 1;

    @Autowired
    ErpCarSimpleInfoMapper erpCarSimpleInfoMapper;

    @Autowired
    ErpCarDetailedInfoMapper erpCarDetailedInfoMapper;

    //车辆简单信息
    List<ErpCarSimpleInfo> erpCarSimpleInfoSelect = new ArrayList<ErpCarSimpleInfo>();
    //车辆详细信息
    List<ErpCarDetailedInfo> listErpCarDetailedInfoSelect = new ArrayList<ErpCarDetailedInfo>();

//    @Scheduled(cron = "*/3  *  *  *  *  ?")//每3秒钟执行
    public String tongBuChangeNetRecSimpleInfo(Integer loopNum,Integer rows){
        String changeNetUrl = sysConfigService.getValueById("SELECT_QGHYPT_CAR_INFO");
        String cookieUseridCar = sysConfigService.getValueById("COOKIE_USERID_HD");
        Map<String,String> params = new HashMap<>();
        //拿到20条数据
        //requestParam.page的value值可变  要想一次插入100条数据 要让这个动态加上1
        params.put("requestParam.equal.areaCode","130000");
        params.put("requestParam.equal.cityId","130300");
        params.put("requestParam.equal.orgCode","130300");
        params.put("requestParam.equal.orgLevel","2");
        params.put("requestParam.page",String.valueOf(loopNum));
        params.put("requestParam.rows",String.valueOf(rows));
        params.put("sortname","vinCode");
        params.put("sortorder","asc");
        Map<String,String> header = new HashMap<>();
        header.put("Cookie",cookieUseridCar);
        String s = "";
        try {
            s = HttpRequestUtil.sendPostRequest(changeNetUrl,params,header);
            //System.out.println("=======" + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public String toTalNum(){
        String changeNetUrl = sysConfigService.getValueById("SELECT_QGHYPT_CAR_INFO");
        String cookieUseridCar = sysConfigService.getValueById("COOKIE_USERID_HD");
        Map<String,String> params = new HashMap<>();
        //拿到20条数据
        //requestParam.page的value值可变  要想一次插入100条数据 要让这个动态加上1
        params.put("requestParam.equal.areaCode","130000");
        params.put("requestParam.equal.cityId","130300");
        params.put("requestParam.equal.orgCode","130300");
        params.put("requestParam.equal.orgLevel","2");
        params.put("requestParam.page","1");
        params.put("requestParam.rows","20");
        params.put("sortname","vehicleNo");
        params.put("sortorder","asc");
        Map<String,String> header = new HashMap<>();
        header.put("Cookie",cookieUseridCar);
        String s = "";
        String toTal = "";
        try {
            s = HttpRequestUtil.sendPostRequest(changeNetUrl,params,header);
            //总数
            JSONObject jsonObject = new JSONObject(s);
            toTal = jsonObject.optString("Total");
        } catch (Exception e) {
            e.printStackTrace();
            return "请重新登录";
        }
        return toTal;
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

    /**
     * 两个小时之间
     * @return
     */
    public String getDateBetweenStr(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, -2);// 24小时制
        date = cal.getTime();
        String betweenDateStr = simpleDateFormat.format(date);
        return betweenDateStr;
    }

    public String dynamicLoop(){
        List<String> list = new ArrayList<String>();//用于接收返回回来的JSON字符串  一条20
        String toTal = toTalNum();
        if(toTal.equals("请重新登录")){
            return toTal;
        }else {
            Integer toTalNum = new Integer(toTal);
            Integer yeShu = (toTalNum/500)+1;
            //更新syconfig更新信息标识  0 为更新 1 更新
            sysConfigService.updateByValueIdStart("UPDATE_CAR_INFO_DATA_FLAG");
            //1.设置一次要插入的数据  遍历边界 100
            for(int i = 0;i<yeShu;i++) {//遍历完后200条数据1 2 2 3 3 49 10 11
                String rows = tongBuChangeNetRecSimpleInfo(flagNum,1); //JSON数据结构 {"Rows":集合,"Total":"车辆总数"}
                list.add(rows);
                flagNum++;
            }
           //进行解析
            if (flagNum >= yeShu) {
                flagNum = 1;
            }
            analySis(list);
            //如果相等就进行删除
            erpCarSimpleInfoMapper.deleteByAllCarFrame();
            //根据时间判断
            String currDateStr = getDateStr(new Date());//当前时间
            String betweenDateStr = getDateBetweenStr(new Date());//前两个小时的时间
            //并且删除时间不等的记录
            erpCarSimpleInfoMapper.deleteByTime(betweenDateStr,currDateStr);
            int allNum = erpCarSimpleInfoMapper.selectAll(betweenDateStr, currDateStr);//查询的总数如果不等在执行插入更新
            if (allNum < toTalNum) {
                dynamicLoop();
            }
            //更新syconfig更新信息标识  0 为更新 1 更新
            sysConfigService.updateByValueIdEnd("UPDATE_CAR_INFO_DATA_FLAG");
        }
        return "";
    }

    //解析JSON 插入数据库
    public void analySis(List<String> list){
        //存储新的数据
        List<ErpCarSimpleInfo> listErpCarSimpleInfoInsert = new ArrayList<ErpCarSimpleInfo>();//插入
        //存储修改的数据
        List<ErpCarSimpleInfo> listErpCarSimpleInfoUpdate = new ArrayList<ErpCarSimpleInfo>();//更新
        for(int j = 0;j<list.size();j++){
            //获取JSONObject对象  一层解析
            JSONObject jsonObject = new JSONObject(list.get(j));
            //拿到Rows的数组一共20个  二层解析  ->集合 20条
            JSONArray jsonArray = jsonObject.optJSONArray("Rows");
            for(int i = 0;i<jsonArray.length();i++) {
                //三层解析  --->一个JSONObject对象  简单的key:value
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                //封装对象
                ErpCarSimpleInfo erpCarSimpleInfo = new ErpCarSimpleInfo();
                //车牌号
                String vehicleNo = jsonObject1.optString("vehicleNo");
                erpCarSimpleInfo.setCarPlateNum(vehicleNo);
                //车架号
                String vinCode = jsonObject1.optString("vinCode");
                erpCarSimpleInfo.setCarFrameNum(vinCode);
                //车主/业户
                String orgShortname = jsonObject1.optString("orgShortname");
                erpCarSimpleInfo.setYehuName(orgShortname);
                //所属服务商/运输企业
                String platformName = jsonObject1.optString("platformName");
                erpCarSimpleInfo.setSupplierName(platformName);
                //籍贯地
                String areaName = jsonObject1.optString("areaName");//省名
                String cityName = jsonObject1.optString("cityName");//城市名
                String countyName = jsonObject1.optString("countyName");//省名
                erpCarSimpleInfo.setPlaceOfOrigin(areaName + "-" + cityName + "-" + countyName);
                //车辆类型
                String vehicleType = jsonObject1.optString("vehicleType");
                erpCarSimpleInfo.setCarType(vehicleType);
                //终端厂商
                String fullName = jsonObject1.optString("fullName");
                erpCarSimpleInfo.setTerminalName(fullName);

                //最后定位   毫秒值
                String sysutc = jsonObject1.optString("sysutc");
                Long sysutcL = Long.valueOf(sysutc);
                Date sysutcTime = new Date(sysutcL);
                erpCarSimpleInfo.setEndLocationTime(sysutcTime);
                //入网时间  毫秒值
                String firstSysutc = jsonObject1.optString("firstSysutc");
                Long firstSysutcL = Long.valueOf(firstSysutc);
                Date firstSysutcTime = new Date(firstSysutcL);
                erpCarSimpleInfo.setInsertNetwork(firstSysutcTime);
                //入网天数
                String daysCount = jsonObject1.optString("daysCount");
                erpCarSimpleInfo.setDaysCount(daysCount);
                //主键
                String id = Guid.guid();
                erpCarSimpleInfo.setId(id);
                //创建时间
                erpCarSimpleInfo.setCreateTime(new Date());
                //终端型号
                erpCarSimpleInfo.setTerminalType("北斗");
                //数据状态
                erpCarSimpleInfo.setDataState("1");

                //上升数据库
                int falg = erpCarSimpleInfoMapper.selectByCarNum(vinCode);
                if(falg!=0){
                    listErpCarSimpleInfoUpdate.add(erpCarSimpleInfo);
                }else {
                    listErpCarSimpleInfoInsert.add(erpCarSimpleInfo);
                }
                //erpCarSimpleInfoSelect.add(erpCarSimpleInfo);
                // 用户详细信息
                //用户详细信息索引
                //String userVid = jsonObject1.optString("vid");
                //拿到JSON字符串
                //String userCarDetail = tongBuChangeNetRecDetailedInfo(userVid);
                //进行解析JSON
                //String newErpCarDetail = userCarDetail+","+id;
                //listErpCarDetail.add(userVid);
            }
        }

        //userCarDetailInfo(listErpCarDetail);
        //insertErpCarDetailedInfo(listErpCarDetailedInfoSelect);
        if (listErpCarSimpleInfoInsert.size()!=0){
            insert(listErpCarSimpleInfoInsert);
        }
        if(listErpCarSimpleInfoUpdate.size()!=0){
            erpCarSimpleInfoMapper.deteleByAll(listErpCarSimpleInfoUpdate);//将要更新的数据先删除再将最新的记录插入
            update(listErpCarSimpleInfoUpdate);
        }
//        select(erpCarSimpleInfoSelect);
        //selectCarFrame(listErpCarDetailedInfoSelect);
    }



    public String dynamicLoopNew(){
        if(rowCount == 0){
            rowCount = 500;
        }else{
            rowCount = rowCount + 100;
        }
        String toTal = toTalNum();
        if(toTal.equals("请重新登录")){
            return toTal;
        }else {
            //存储新的数据
            List<ErpCarSimpleInfo> listErpCarSimpleInfoInsert = new ArrayList<ErpCarSimpleInfo>();//插入
            //存储修改的数据
            List<ErpCarSimpleInfo> listErpCarSimpleInfoUpdate = new ArrayList<ErpCarSimpleInfo>();//更新
            Integer toTalNum = new Integer(toTal);
            Integer yeShu = (toTalNum/rowCount)+1;
            //更新syconfig更新信息标识  0 为更新 1 更新
            sysConfigService.updateByValueIdStart("UPDATE_CAR_INFO_DATA_FLAG");
            //1.设置一次要插入的数据  遍历边界 100
            for(int i = 0;i<yeShu;i++){//遍历完后200条数据1 2 2 3 3 49 10 11
                int index = i+1;
                //开启多个线程
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<String> list = new ArrayList<String>();
                        String rows = tongBuChangeNetRecSimpleInfo(index,rowCount); //JSON数据结构 {"Rows":集合,"Total":"车辆总数"}
                        list.add(rows);
                        analySisNew(list,listErpCarSimpleInfoInsert,listErpCarSimpleInfoUpdate);//进行解析
                        //设置结束线程的个数
                        setEndThreadCount(yeShu);
                        while (index>=yeShu){
                            //System.out.println("已经结束的线程数："+threadEndCount);
                            if(getEndThreadCount() >= yeShu){
                                if (listErpCarSimpleInfoInsert.size()!=0){
                                    insert(listErpCarSimpleInfoInsert);
                                }
                                if(listErpCarSimpleInfoUpdate.size()!=0){
                                    erpCarSimpleInfoMapper.deteleByAll(listErpCarSimpleInfoUpdate);//将要更新的数据先删除再将最新的记录插入
                                    insert(listErpCarSimpleInfoUpdate);
                                }
                                //如果相等就进行删除
                                erpCarSimpleInfoMapper.deleteByAllCarFrame();
                                //根据时间判断
                                String currDateStr = getDateStr(new Date());//当前时间
                                String betweenDateStr = getDateBetweenStr(new Date());//前两个小时的时间
                                //并且删除时间不等的记录
                                erpCarSimpleInfoMapper.deleteByTime(betweenDateStr,currDateStr);
                                int allNum = erpCarSimpleInfoMapper.selectAll(betweenDateStr,currDateStr);//查询的总数如果不等在执行插入更新
                                if(allNum<toTalNum){
                                    dynamicLoopNew();
                                }else{
                                    rowCount = 500;
                                    //更新syconfig更新信息标识  0 未更新 1 更新
                                    sysConfigService.updateByValueIdEnd("UPDATE_CAR_INFO_DATA_FLAG");
                                }
                                break;
                            }else{
                                try{
                                    Thread.sleep(3000);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
                thread.start();
            }
            return "";
        }
    }

    //解析JSON 插入数据库
    public void analySisNew(List<String> list,List<ErpCarSimpleInfo> listErpCarSimpleInfoInsert,List<ErpCarSimpleInfo> listErpCarSimpleInfoUpdate){//list size:0-9
        for(int j = 0;j<list.size();j++){
            //获取JSONObject对象  一层解析
            JSONObject jsonObject = new JSONObject(list.get(j));
            //拿到Rows的数组一共20个  二层解析  ->集合 20条
            JSONArray jsonArray = jsonObject.optJSONArray("Rows");
            if(jsonArray != null){
                for(int i = 0;i<jsonArray.length();i++) {
                    //三层解析  --->一个JSONObject对象  简单的key:value
                    JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                    //封装对象
                    ErpCarSimpleInfo erpCarSimpleInfo = new ErpCarSimpleInfo();
                    //车牌号
                    String vehicleNo = jsonObject1.optString("vehicleNo");
                    erpCarSimpleInfo.setCarPlateNum(vehicleNo);
                    //车架号
                    String vinCode = jsonObject1.optString("vinCode");
                    erpCarSimpleInfo.setCarFrameNum(vinCode);
                    //车主/业户
                    String orgShortname = jsonObject1.optString("orgShortname");
                    erpCarSimpleInfo.setYehuName(orgShortname);
                    //所属服务商/运输企业
                    String platformName = jsonObject1.optString("platformName");
                    erpCarSimpleInfo.setSupplierName(platformName);
                    //籍贯地
                    String areaName = jsonObject1.optString("areaName");//省名
                    String cityName = jsonObject1.optString("cityName");//城市名
                    String countyName = jsonObject1.optString("countyName");//省名
                    erpCarSimpleInfo.setPlaceOfOrigin(areaName + "-" + cityName + "-" + countyName);
                    //车辆类型
                    String vehicleType = jsonObject1.optString("vehicleType");
                    erpCarSimpleInfo.setCarType(vehicleType);
                    //终端厂商
                    String fullName = jsonObject1.optString("fullName");
                    erpCarSimpleInfo.setTerminalName(fullName);

                    //最后定位   毫秒值
                    String sysutc = jsonObject1.optString("sysutc");
                    Long sysutcL = Long.valueOf(sysutc);
                    Date sysutcTime = new Date(sysutcL);
                    erpCarSimpleInfo.setEndLocationTime(sysutcTime);
                    //入网时间  毫秒值
                    String firstSysutc = jsonObject1.optString("firstSysutc");
                    Long firstSysutcL = Long.valueOf(firstSysutc);
                    Date firstSysutcTime = new Date(firstSysutcL);
                    erpCarSimpleInfo.setInsertNetwork(firstSysutcTime);
                    //入网天数
                    String daysCount = jsonObject1.optString("daysCount");
                    erpCarSimpleInfo.setDaysCount(daysCount);
                    //主键
                    String id = Guid.guid();
                    erpCarSimpleInfo.setId(id);
                    //创建时间
                    erpCarSimpleInfo.setCreateTime(new Date());
                    //终端型号
                    erpCarSimpleInfo.setTerminalType("北斗");
                    //数据状态
                    erpCarSimpleInfo.setDataState("1");

                    //上升数据库
                    int falg = erpCarSimpleInfoMapper.selectByCarNum(vinCode);
                    if(falg>0){
                        listErpCarSimpleInfoUpdate.add(erpCarSimpleInfo);
                    }else {
                        listErpCarSimpleInfoInsert.add(erpCarSimpleInfo);
                    }
                }
            }
        }
    }

    public void select(List<ErpCarSimpleInfo> list){
        List<ErpCarSimpleInfo> listErpCarSimpleInfoInsert = new ArrayList<ErpCarSimpleInfo>();//插入
        List<ErpCarSimpleInfo> listErpCarSimpleInfoUpdate = new ArrayList<ErpCarSimpleInfo>();//更新
        List<ErpCarSimpleInfo> list1 = erpCarSimpleInfoMapper.selectByFrameNum(list);
        for(int i =0;i<list1.size();i++){
            if(list1.get(i)==null){
                listErpCarSimpleInfoInsert.add(list1.get(i));
            }else {
                listErpCarSimpleInfoUpdate.add(list1.get(i));
            }
        }
        if(!(listErpCarSimpleInfoInsert.size()==0)){
            insert(listErpCarSimpleInfoInsert);
        }else if(!(listErpCarSimpleInfoUpdate.size()==0)){
            update(listErpCarSimpleInfoUpdate);
        }
    }

    //进行判断是否存在数据  ，不再则插入，在更新
    public void insert(List<ErpCarSimpleInfo> list){
        erpCarSimpleInfoMapper.insertByList(list);
    }
    public void update(List<ErpCarSimpleInfo> list){
        erpCarSimpleInfoMapper.insertByList(list);
    }

    //进行判断是否存在数据  ，不再则插入，在更新
    public void insertObj(List<ErpCarSimpleInfo> list){
        for(int i=0;i<list.size();i++){
            erpCarSimpleInfoMapper.insert(list.get(i));
        }
    }
    public void updateObj(List<ErpCarSimpleInfo> list){
        for(int i=0;i<list.size();i++){
            erpCarSimpleInfoMapper.insert(list.get(i));
        }
    }

    //车辆详细信息
    public String tongBuChangeNetRecDetailedInfo(String vid){
        String changeNetUrl = sysConfigService.getValueById("SELECT_QGHYPT_CAR_TECHNOLOGY_INFO");
        String cookieUseridCar = sysConfigService.getValueById("COOKIE_USERID_HD");
        Map<String,String> params = new HashMap<>();
        //点击的用户
        params.put("requestParam.equal.vid",vid);
        Map<String,String> header = new HashMap<>();
        header.put("Cookie",cookieUseridCar);
        String s = "";
        try {
            s = HttpRequestUtil.sendPostRequest(changeNetUrl,params,header);
            //System.out.println(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    //解析用户的详细信息
    public void userCarDetailInfo(List<String> listErpCarDetail){
        for(String strErpCar : listErpCarDetail){
            String userCarDetail = tongBuChangeNetRecDetailedInfo(strErpCar);
            JSONObject jsonObject = new JSONObject(userCarDetail);//转化JSON字符串
            ErpCarDetailedInfo erpCarDetailedInfo = new ErpCarDetailedInfo();
            //主键
            erpCarDetailedInfo.setId(Guid.guid());

            //创建时间
            erpCarDetailedInfo.setCreateTime(new Date());
            //是否定位
            String firstMap = jsonObject.optString("firstMap");
            erpCarDetailedInfo.setIsLocation(firstMap);
            //所属地区
            String provName = jsonObject.optString("provName");
            String cityName = jsonObject.optString("cityName");
            String countryName = jsonObject.optString("countryName");
            String address = provName+""+cityName+""+countryName;
            erpCarDetailedInfo.setPlaceOfOrigin(address);
            //入网时间
            String firstSysutc = jsonObject.optString("firstSysutc");
            Long firstSysutcL = Long.valueOf(firstSysutc);
            Date firstSysutcTime = new Date(firstSysutcL);
            erpCarDetailedInfo.setInsertNetwork(firstSysutcTime);
            //业户名称
            String companyname = jsonObject.optString("companyname");
            erpCarDetailedInfo.setYehuName(companyname);
            //联系人
            String ownerName = jsonObject.optString("ownerName");
            erpCarDetailedInfo.setContactPerson(ownerName);
            //联系电话
            String ownerPhone = jsonObject.optString("ownerPhone");
            erpCarDetailedInfo.setContactPhoneNum(ownerPhone);
            //车牌号
            String vehicleNo = jsonObject.optString("vehicleNo");
            erpCarDetailedInfo.setCarPlateNum(vehicleNo);
            //车牌颜色
            String plateColorName = jsonObject.optString("plateColorName");
            erpCarDetailedInfo.setCarPlateColor(plateColorName);
            //车架号
            String vinCode = jsonObject.optString("vinCode");
            erpCarDetailedInfo.setCarFrameNum(vinCode);
            //查询是否存在车
            //int flagNum = erpCarDetailedInfoMapper.selectByCarNum(vinCode);
            //车辆类型
            String typeName = jsonObject.optString("typeName");
            erpCarDetailedInfo.setCarType(typeName);
            //车辆品牌
            String brandName = jsonObject.optString("brandName");
            erpCarDetailedInfo.setCarBrand(brandName);
            //车辆型号
            String prodName = jsonObject.optString("prodName");
            erpCarDetailedInfo.setCarModel(prodName);
            //燃料类型
            String fuelType = jsonObject.optString("fuelType");
            if(fuelType.equals("1")){
                fuelType = "柴油";
            } else if(fuelType.equals("2")){
                fuelType = "汽油";
            } else if(fuelType.equals("3")){
                fuelType = "电";
            } else if(fuelType.equals("4")){
                fuelType = "乙醇";
            }else if(fuelType.equals("5")){
                fuelType = "液化天然气(LNG)";
            }else if(fuelType.equals("6")){
                fuelType = "压缩天然气(CNG)";
            }else {
                fuelType = "";
            }
            erpCarDetailedInfo.setFuelType(fuelType);
            //总质量
            String vehicleTon = jsonObject.optString("vehicleTon");
            erpCarDetailedInfo.setTotalMass(vehicleTon);
            //核定载质量
            String loadTon = jsonObject.optString("loadTon");
            erpCarDetailedInfo.setHedingzaiMass(loadTon);
            //准牵引总质量
            String vehicleDrawTon = jsonObject.optString("vehicleDrawTon");
            erpCarDetailedInfo.setTractionMass(loadTon);
            //外廓尺寸长
            String vehicleLength = jsonObject.optString("vehicleLength");
            erpCarDetailedInfo.setTechOuterLength(vehicleLength);
            //外廓尺寸宽
            String vehicleWidth = jsonObject.optString("vehicleWidth");
            erpCarDetailedInfo.setTechOuterWidth(vehicleWidth);
            //外廓尺寸高
            String vehicleHeight = jsonObject.optString("vehicleHeight");
            erpCarDetailedInfo.setTechOuterHeight(vehicleHeight);
            //轴数
            String vehicleAxis = jsonObject.optString("vehicleAxis");
            erpCarDetailedInfo.setAxleNum(new Integer(vehicleAxis));
            //轮胎数
            int vehicleTyreNumber = jsonObject.optInt("vehicleTyreNumber");
            erpCarDetailedInfo.setTyreNum(vehicleTyreNumber);
            //轮胎规格
            String vehicleTyreSize = jsonObject.optString("vehicleTyreSize");
            erpCarDetailedInfo.setTyreSpecifications(vehicleTyreSize);
            //车辆出厂日期
            String verify = jsonObject.optString("verifyTime");
            if(verify.equals("")){

            }else{
                Long verifyTimeL = Long.valueOf(verify);
                Date verifyTime = new Date(verifyTimeL);
                erpCarDetailedInfo.setCarProduceDate(verifyTime);
            }
            //经营范围
            String vehicleBusinessScope = jsonObject.optString("vehicleBusinessScope");
            erpCarDetailedInfo.setBusinessScope(vehicleBusinessScope);
            //车身颜色
            String vehicleColorName = jsonObject.optString("vehicleColorName");
            erpCarDetailedInfo.setCarBodyColor(vehicleColorName);
            //车辆购置方式
            String purchaseType = jsonObject.optString("purchaseType");
            erpCarDetailedInfo.setCarPurchaseType(purchaseType);
            //车辆保险
            String insuranceType = jsonObject.optString("insuranceType");
            String str = "";
            if(insuranceType!=null && !insuranceType.equals("")){
                String[] insuranceTypeArr = insuranceType.split(",");
                for(int i=0;i<insuranceTypeArr.length;i++){
                    String arr = insuranceTypeArr[i];
                    if(arr=="1"){
                        str+="交强险 ";
                    }else if(arr=="2"){
                        str+="盗抢险 ";
                    }else if(arr=="3"){
                        str+="三者 ";
                    }else if(arr=="4"){
                        str+="车损险 ";
                    }else if(arr=="5"){
                        str+="车上人员险 ";
                    }else if(arr=="6"){
                        str+="货物运输险 ";
                    }
                }
            }
            insuranceType = str;
            erpCarDetailedInfo.setCarInsuring(insuranceType);
            //车辆保险到期时间
            String insuranceExpirate = jsonObject.optString("insuranceExpirateTime");
            if(insuranceExpirate.equals("")){

            }else{
                Long insuranceExpirateTimeL = Long.valueOf(insuranceExpirate);
                Date insuranceExpirateTime = new Date(insuranceExpirateTimeL);
                erpCarDetailedInfo.setCarInsuringEndTime(insuranceExpirateTime);
            }

            //道路运输证号
            String roadTransport = jsonObject.optString("roadTransport");
            erpCarDetailedInfo.setRoadTransportNum(roadTransport);
            //经营许可证
            String simBusinessName = jsonObject.optString("simBusinessName");
            erpCarDetailedInfo.setBusinessLicenseNumber(simBusinessName);
            //终端id
            String tmac = jsonObject.optString("tmac");
            erpCarDetailedInfo.setTerminalId(tmac);
            //终端编号
            String tmacName = jsonObject.optString("tmacName");
            erpCarDetailedInfo.setTerminalCode(tmacName);
            //SIM卡号
            String commaddr = jsonObject.optString("commaddr");
            erpCarDetailedInfo.setSimCode(commaddr);
            //终端厂商
            String fullName = jsonObject.optString("fullName");
            erpCarDetailedInfo.setTerminalName(fullName);
            //数据状态
            erpCarDetailedInfo.setDataState("1");
            listErpCarDetailedInfoSelect.add(erpCarDetailedInfo);
        }
    }

    public void selectCarFrame(List<ErpCarDetailedInfo> list){
        List<ErpCarDetailedInfo> listErpCarDetailedInfoInsert = new ArrayList<ErpCarDetailedInfo>();//插入
        List<ErpCarDetailedInfo> listErpCarDetailedInfoUpdate = new ArrayList<ErpCarDetailedInfo>();//更新
        List<ErpCarDetailedInfo> list1 = erpCarDetailedInfoMapper.selectByFrameNum(list);
        for(int i =0;i<list1.size();i++){
            if(list1.get(i)==null){
                listErpCarDetailedInfoInsert.add(list1.get(i));
            }else {
                listErpCarDetailedInfoUpdate.add(list1.get(i));
            }
        }
        if(!(listErpCarDetailedInfoInsert.size()==0)){
            insertErpCarDetailedInfo(listErpCarDetailedInfoInsert);
        }else if(!(listErpCarDetailedInfoUpdate.size()==0)){
            updateErpCarDetailedInfo(listErpCarDetailedInfoUpdate);
        }
    }


    //进行判断是否存在数据  ，不再则插入，在更新
    public void insertErpCarDetailedInfo(List<ErpCarDetailedInfo> list){
        erpCarDetailedInfoMapper.insertByList(list);
    }
    public void updateErpCarDetailedInfo(List<ErpCarDetailedInfo> list){
        erpCarDetailedInfoMapper.updateByList(list);
    }

    public synchronized void setEndThreadCount(Integer yeshu){
        threadEndCount++;
        if(threadEndCount > yeshu){
            threadEndCount = 1;
        }
        System.out.println("已经结束的线程个数："+threadEndCount);
    }

    public Integer getEndThreadCount(){
        return threadEndCount;
    }

}

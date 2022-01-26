package com.edgedo.sys.entity;

import java.util.Date;

/**
 * @ClassName CarSimpleInfoVo
 * @Description TODO
 * @Author 床前明月光
 * @Date 2019/9/15 15:36
 **/
public class CarSimpleInfoVo {

    private String attachCt;

    private String daysCount;

    private String vid;

    private String county;
    //车架号
    private String vinCode;
    //车辆类型
    private String vehicleType;
    //车牌号
    private String vehicleNo;
    //
    private String areaCode;
    //入网时间  毫秒值
    private Date firstSysutc;

    private String areaName;

    private String positionModel;

    private String cityId;

    private String cityName;
    ////车主/业户
    private String orgShortname;

    private String countyName;
    //所属服务商/运输企业
    private String platformName;

    private String fwsorcorp;
    //最后定位   毫秒值
    private Date sysutc;
    //终端厂商
    private String fullName;

    public Date getFirstSysutc() {
        return firstSysutc;
    }

    public void setFirstSysutc(String firstSysutc) {
        Date date = new Date();
        date.setTime(Long.valueOf(firstSysutc));
        this.firstSysutc = date;
    }

    public Date getSysutc() {
        return sysutc;
    }

    public void setSysutc(String sysutc) {
        Date date = new Date();
        date.setTime(Long.valueOf(sysutc));
        this.sysutc = date;
    }

    public String getAttachCt() {
        return attachCt;
    }

    public void setAttachCt(String attachCt) {
        this.attachCt = attachCt;
    }

    public String getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(String daysCount) {
        this.daysCount = daysCount;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getVinCode() {
        return vinCode;
    }

    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }



    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getPositionModel() {
        return positionModel;
    }

    public void setPositionModel(String positionModel) {
        this.positionModel = positionModel;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getOrgShortname() {
        return orgShortname;
    }

    public void setOrgShortname(String orgShortname) {
        this.orgShortname = orgShortname;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getFwsorcorp() {
        return fwsorcorp;
    }

    public void setFwsorcorp(String fwsorcorp) {
        this.fwsorcorp = fwsorcorp;
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

package com.edgedo.sys.entity;

import java.util.List;

public class ErpCarMsg1 {
    private String applyFrom;
    private String applyPhone;
    private String applyUnitFromName;//办事处
    private String applyUnitName;//处理人姓名
    private String applyUnitToName;
    private String applyUser;
    private String assistBy;
    private String assistOrgId;
    private String assistTime;
    private List<String> attachList;
    private String createBy;
    private String createTime;
    private String id;
    private String plateColor;
    private String platformIdFrom;
    private String platformIdTo;
    private String pointIdTo;
    private String refuseReason;//拒绝原因原因
    private String transferStatus;
    private String transferType;
    private String updateBy;
    private String updateByName;//转出人
    private String updateTime;
    private boolean uploadOtherType;//车牌号
    private String vehicleNo;
    private String vid;
    public void setApplyFrom(String applyFrom) {
        this.applyFrom = applyFrom;
    }
    public String getApplyFrom() {
        return applyFrom;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone;
    }
    public String getApplyPhone() {
        return applyPhone;
    }

    public void setApplyUnitFromName(String applyUnitFromName) {
        this.applyUnitFromName = applyUnitFromName;
    }
    public String getApplyUnitFromName() {
        return applyUnitFromName;
    }

    public void setApplyUnitName(String applyUnitName) {
        this.applyUnitName = applyUnitName;
    }
    public String getApplyUnitName() {
        return applyUnitName;
    }

    public void setApplyUnitToName(String applyUnitToName) {
        this.applyUnitToName = applyUnitToName;
    }
    public String getApplyUnitToName() {
        return applyUnitToName;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }
    public String getApplyUser() {
        return applyUser;
    }

    public void setAssistBy(String assistBy) {
        this.assistBy = assistBy;
    }
    public String getAssistBy() {
        return assistBy;
    }

    public void setAssistOrgId(String assistOrgId) {
        this.assistOrgId = assistOrgId;
    }
    public String getAssistOrgId() {
        return assistOrgId;
    }

    public void setAssistTime(String assistTime) {
        this.assistTime = assistTime;
    }
    public String getAssistTime() {
        return assistTime;
    }

    public void setAttachList(List<String> attachList) {
        this.attachList = attachList;
    }
    public List<String> getAttachList() {
        return attachList;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }
    public String getPlateColor() {
        return plateColor;
    }

    public void setPlatformIdFrom(String platformIdFrom) {
        this.platformIdFrom = platformIdFrom;
    }
    public String getPlatformIdFrom() {
        return platformIdFrom;
    }

    public void setPlatformIdTo(String platformIdTo) {
        this.platformIdTo = platformIdTo;
    }
    public String getPlatformIdTo() {
        return platformIdTo;
    }

    public void setPointIdTo(String pointIdTo) {
        this.pointIdTo = pointIdTo;
    }
    public String getPointIdTo() {
        return pointIdTo;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }
    public String getRefuseReason() {
        return refuseReason;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }
    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }
    public String getTransferType() {
        return transferType;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }
    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUploadOtherType(boolean uploadOtherType) {
        this.uploadOtherType = uploadOtherType;
    }
    public boolean getUploadOtherType() {
        return uploadOtherType;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }
    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }
    public String getVid() {
        return vid;
    }
    @Override
    public String toString() {
        return "ErpCarMsg1{" +
                "applyFrom='" + applyFrom + '\'' +
                ", applyPhone='" + applyPhone + '\'' +
                ", applyUnitFromName='" + applyUnitFromName + '\'' +
                ", applyUnitName='" + applyUnitName + '\'' +
                ", applyUnitToName='" + applyUnitToName + '\'' +
                ", applyUser='" + applyUser + '\'' +
                ", assistBy='" + assistBy + '\'' +
                ", assistOrgId='" + assistOrgId + '\'' +
                ", assistTime='" + assistTime + '\'' +
                ", attachList=" + attachList +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", id='" + id + '\'' +
                ", plateColor='" + plateColor + '\'' +
                ", platformIdFrom='" + platformIdFrom + '\'' +
                ", platformIdTo='" + platformIdTo + '\'' +
                ", pointIdTo='" + pointIdTo + '\'' +
                ", refuseReason='" + refuseReason + '\'' +
                ", transferStatus='" + transferStatus + '\'' +
                ", transferType='" + transferType + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateByName='" + updateByName + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", uploadOtherType=" + uploadOtherType +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", vid='" + vid + '\'' +
                '}';
    }

}

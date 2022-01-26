package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_change_net_rec")
public class ErpChangeNetRec implements Serializable{
	
		
	/**
	 * 属性描述:主键
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:创建时间
	 */
	@TableField(value="CREATE_TIME",exist=true)
	java.util.Date createTime;
	
	/**
	 * 属性描述:创建人ID
	 */
	@TableField(value="CREATE_USER_ID",exist=true)
	java.lang.String createUserId;
	
	/**
	 * 属性描述:创建人名
	 */
	@TableField(value="CREATE_USER_NAME",exist=true)
	java.lang.String createUserName;
	
	/**
	 * 属性描述:所属车辆ID
	 */
	@TableField(value="OWNER_CAR_INFO_ID",exist=true)
	java.lang.String ownerCarInfoId;
	
	/**
	 * 属性描述:车牌号
	 */
	@TableField(value="CAR_PLATE_NUM",exist=true)
	java.lang.String carPlateNum;
	
	/**
	 * 属性描述:车架号
	 */
	@TableField(value="CAR_FRAME_NUM",exist=true)
	java.lang.String carFrameNum;
	
	/**
	 * 属性描述:车牌颜色
	 */
	@TableField(value="CAR_PLATE_COLOR",exist=true)
	java.lang.String carPlateColor;
	
	/**
	 * 属性描述:转网类型
	 */
	@TableField(value="CHANGE_NET_TYPE",exist=true)
	java.lang.String changeNetType;
	
	/**
	 * 属性描述:客户名称
	 */
	@TableField(value="CUSTOMER_NAME",exist=true)
	java.lang.String customerName;
	
	/**
	 * 属性描述:联系电话
	 */
	@TableField(value="CONTACT_PHONE",exist=true)
	java.lang.String contactPhone;
	
	/**
	 * 属性描述:所属终端ID
	 */
	@TableField(value="TERMINAL_ID",exist=true)
	java.lang.String terminalId;
	
	/**
	 * 属性描述:终端厂商
	 */
	@TableField(value="TERMINAL_CHANGSHANG",exist=true)
	java.lang.String terminalChangshang;
	
	/**
	 * 属性描述:终端编号
	 */
	@TableField(value="TERMINAL_CODE",exist=true)
	java.lang.String terminalCode;
	
	/**
	 * 属性描述:SIM卡号
	 */
	@TableField(value="SIM_NUM",exist=true)
	java.lang.String simNum;
	
	/**
	 * 属性描述:原平台
	 */
	@TableField(value="ORG_PT",exist=true)
	java.lang.String orgPt;
	
	/**
	 * 属性描述:提交人
	 */
	@TableField(value="SUBMIT_PERSON",exist=true)
	java.lang.String submitPerson;
	
	/**
	 * 属性描述:提交时间
	 */
	@TableField(value="SUBMIT_TIME",exist=true)
	java.util.Date submitTime;
	
	/**
	 * 属性描述:省ID
	 */
	@TableField(value="PROVINCE_ID",exist=true)
	java.lang.String provinceId;
	
	/**
	 * 属性描述:省名
	 */
	@TableField(value="PROVINCE_NAME",exist=true)
	java.lang.String provinceName;
	
	/**
	 * 属性描述:城市id
	 */
	@TableField(value="CITY_ID",exist=true)
	java.lang.String cityId;
	
	/**
	 * 属性描述:城市名
	 */
	@TableField(value="CITY_NAME",exist=true)
	java.lang.String cityName;
	
	/**
	 * 属性描述:县区ID
	 */
	@TableField(value="XIANQU_ID",exist=true)
	java.lang.String xianquId;
	
	/**
	 * 属性描述:县区名
	 */
	@TableField(value="XIANQU_NAME",exist=true)
	java.lang.String xianquName;

	/**
	 * 属性描述：平台数据ID
	 */
	@TableField(value="PLATFORM_ID",exist=true)
	java.lang.String platformID;

	/**
	 * 属性描述：拒绝原因
	 */
	@TableField(value="REFUSE_REASON",exist=true)
	java.lang.String refuseReason;
	
	/**
	 * 属性描述:提供资料复选(车身照,行驶证,登记证，执照身/份证,合同)
	 */
	@TableField(value="GIVE_INFORMATION",exist=true)
	java.lang.String giveInformation;
	
	/**
	 * 属性描述:现平台
	 */
	@TableField(value="NOW_PT",exist=true)
	java.lang.String nowPt;
	
	/**
	 * 属性描述:定位情况
	 */
	@TableField(value="LOCATION",exist=true)
	java.lang.String location;
	
	/**
	 * 属性描述:备注
	 */
	@TableField(value="REMARK",exist=true)
	java.lang.String remark;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;
	
	
	
	
	
	
	public java.lang.String getId(){
		return this.id;
	}
	
	public void setId(java.lang.String id){
		this.id=id;
	}
	
	
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date createTime){
		this.createTime=createTime;
	}
	
	
	public java.lang.String getCreateUserId(){
		return this.createUserId;
	}
	
	public void setCreateUserId(java.lang.String createUserId){
		this.createUserId=createUserId;
	}
	
	
	public java.lang.String getCreateUserName(){
		return this.createUserName;
	}
	
	public void setCreateUserName(java.lang.String createUserName){
		this.createUserName=createUserName;
	}
	
	
	public java.lang.String getOwnerCarInfoId(){
		return this.ownerCarInfoId;
	}
	
	public void setOwnerCarInfoId(java.lang.String ownerCarInfoId){
		this.ownerCarInfoId=ownerCarInfoId;
	}
	
	
	public java.lang.String getCarPlateNum(){
		return this.carPlateNum;
	}
	
	public void setCarPlateNum(java.lang.String carPlateNum){
		this.carPlateNum=carPlateNum;
	}
	
	
	public java.lang.String getCarFrameNum(){
		return this.carFrameNum;
	}
	
	public void setCarFrameNum(java.lang.String carFrameNum){
		this.carFrameNum=carFrameNum;
	}
	
	
	public java.lang.String getCarPlateColor(){
		return this.carPlateColor;
	}
	
	public void setCarPlateColor(java.lang.String carPlateColor){
		this.carPlateColor=carPlateColor;
	}
	
	
	public java.lang.String getChangeNetType(){
		return this.changeNetType;
	}
	
	public void setChangeNetType(java.lang.String changeNetType){
		this.changeNetType=changeNetType;
	}
	
	
	public java.lang.String getCustomerName(){
		return this.customerName;
	}
	
	public void setCustomerName(java.lang.String customerName){
		this.customerName=customerName;
	}
	
	
	public java.lang.String getContactPhone(){
		return this.contactPhone;
	}
	
	public void setContactPhone(java.lang.String contactPhone){
		this.contactPhone=contactPhone;
	}
	
	
	public java.lang.String getTerminalId(){
		return this.terminalId;
	}
	
	public void setTerminalId(java.lang.String terminalId){
		this.terminalId=terminalId;
	}
	
	
	public java.lang.String getTerminalChangshang(){
		return this.terminalChangshang;
	}
	
	public void setTerminalChangshang(java.lang.String terminalChangshang){
		this.terminalChangshang=terminalChangshang;
	}
	
	
	public java.lang.String getTerminalCode(){
		return this.terminalCode;
	}
	
	public void setTerminalCode(java.lang.String terminalCode){
		this.terminalCode=terminalCode;
	}
	
	
	public java.lang.String getSimNum(){
		return this.simNum;
	}
	
	public void setSimNum(java.lang.String simNum){
		this.simNum=simNum;
	}
	
	
	public java.lang.String getOrgPt(){
		return this.orgPt;
	}
	
	public void setOrgPt(java.lang.String orgPt){
		this.orgPt=orgPt;
	}
	
	
	public java.lang.String getSubmitPerson(){
		return this.submitPerson;
	}
	
	public void setSubmitPerson(java.lang.String submitPerson){
		this.submitPerson=submitPerson;
	}
	
	
	public java.util.Date getSubmitTime(){
		return this.submitTime;
	}
	
	public void setSubmitTime(java.util.Date submitTime){
		this.submitTime=submitTime;
	}
	
	
	public java.lang.String getProvinceId(){
		return this.provinceId;
	}
	
	public void setProvinceId(java.lang.String provinceId){
		this.provinceId=provinceId;
	}
	
	
	public java.lang.String getProvinceName(){
		return this.provinceName;
	}
	
	public void setProvinceName(java.lang.String provinceName){
		this.provinceName=provinceName;
	}
	
	
	public java.lang.String getCityId(){
		return this.cityId;
	}
	
	public void setCityId(java.lang.String cityId){
		this.cityId=cityId;
	}
	
	
	public java.lang.String getCityName(){
		return this.cityName;
	}
	
	public void setCityName(java.lang.String cityName){
		this.cityName=cityName;
	}
	
	
	public java.lang.String getXianquId(){
		return this.xianquId;
	}
	
	public void setXianquId(java.lang.String xianquId){
		this.xianquId=xianquId;
	}
	
	
	public java.lang.String getXianquName(){
		return this.xianquName;
	}
	
	public void setXianquName(java.lang.String xianquName){
		this.xianquName=xianquName;
	}
	
	
	public java.lang.String getGiveInformation(){
		return this.giveInformation;
	}
	
	public void setGiveInformation(java.lang.String giveInformation){
		this.giveInformation=giveInformation;
	}
	
	
	public java.lang.String getNowPt(){
		return this.nowPt;
	}
	
	public void setNowPt(java.lang.String nowPt){
		this.nowPt=nowPt;
	}
	
	
	public java.lang.String getLocation(){
		return this.location;
	}
	
	public void setLocation(java.lang.String location){
		this.location=location;
	}
	
	
	public java.lang.String getRemark(){
		return this.remark;
	}
	
	public void setRemark(java.lang.String remark){
		this.remark=remark;
	}
	
	
	public java.lang.String getDataState(){
		return this.dataState;
	}
	
	public void setDataState(java.lang.String dataState){
		this.dataState=dataState;
	}

	public String getPlatformID() {
		return platformID;
	}

	public void setPlatformID(String platformID) {
		this.platformID = platformID;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	@Override
	public String toString() {
		return "ErpChangeNetRec{" +
				"id='" + id + '\'' +
				", createTime=" + createTime +
				", createUserId='" + createUserId + '\'' +
				", createUserName='" + createUserName + '\'' +
				", ownerCarInfoId='" + ownerCarInfoId + '\'' +
				", carPlateNum='" + carPlateNum + '\'' +
				", carFrameNum='" + carFrameNum + '\'' +
				", carPlateColor='" + carPlateColor + '\'' +
				", changeNetType='" + changeNetType + '\'' +
				", customerName='" + customerName + '\'' +
				", contactPhone='" + contactPhone + '\'' +
				", terminalId='" + terminalId + '\'' +
				", terminalChangshang='" + terminalChangshang + '\'' +
				", terminalCode='" + terminalCode + '\'' +
				", simNum='" + simNum + '\'' +
				", orgPt='" + orgPt + '\'' +
				", submitPerson='" + submitPerson + '\'' +
				", submitTime=" + submitTime +
				", provinceId='" + provinceId + '\'' +
				", provinceName='" + provinceName + '\'' +
				", cityId='" + cityId + '\'' +
				", cityName='" + cityName + '\'' +
				", xianquId='" + xianquId + '\'' +
				", xianquName='" + xianquName + '\'' +
				", platformID='" + platformID + '\'' +
				", refuseReason='" + refuseReason + '\'' +
				", giveInformation='" + giveInformation + '\'' +
				", nowPt='" + nowPt + '\'' +
				", location='" + location + '\'' +
				", remark='" + remark + '\'' +
				", dataState='" + dataState + '\'' +
				'}';
	}


	@Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ErpChangeNetRec other = (ErpChangeNetRec) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))
				        		&&(this.getOwnerCarInfoId() == null ? other.getId() == null : this.getOwnerCarInfoId().equals(other.getOwnerCarInfoId()))
				        		&&(this.getCarPlateNum() == null ? other.getId() == null : this.getCarPlateNum().equals(other.getCarPlateNum()))
				        		&&(this.getCarFrameNum() == null ? other.getId() == null : this.getCarFrameNum().equals(other.getCarFrameNum()))
				        		&&(this.getCarPlateColor() == null ? other.getId() == null : this.getCarPlateColor().equals(other.getCarPlateColor()))
				        		&&(this.getChangeNetType() == null ? other.getId() == null : this.getChangeNetType().equals(other.getChangeNetType()))
				        		&&(this.getCustomerName() == null ? other.getId() == null : this.getCustomerName().equals(other.getCustomerName()))
				        		&&(this.getContactPhone() == null ? other.getId() == null : this.getContactPhone().equals(other.getContactPhone()))
				        		&&(this.getTerminalId() == null ? other.getId() == null : this.getTerminalId().equals(other.getTerminalId()))
				        		&&(this.getTerminalChangshang() == null ? other.getId() == null : this.getTerminalChangshang().equals(other.getTerminalChangshang()))
				        		&&(this.getTerminalCode() == null ? other.getId() == null : this.getTerminalCode().equals(other.getTerminalCode()))
				        		&&(this.getSimNum() == null ? other.getId() == null : this.getSimNum().equals(other.getSimNum()))
				        		&&(this.getOrgPt() == null ? other.getId() == null : this.getOrgPt().equals(other.getOrgPt()))
				        		&&(this.getSubmitPerson() == null ? other.getId() == null : this.getSubmitPerson().equals(other.getSubmitPerson()))
				        		&&(this.getSubmitTime() == null ? other.getId() == null : this.getSubmitTime().equals(other.getSubmitTime()))
				        		&&(this.getProvinceId() == null ? other.getId() == null : this.getProvinceId().equals(other.getProvinceId()))
				        		&&(this.getProvinceName() == null ? other.getId() == null : this.getProvinceName().equals(other.getProvinceName()))
				        		&&(this.getCityId() == null ? other.getId() == null : this.getCityId().equals(other.getCityId()))
				        		&&(this.getCityName() == null ? other.getId() == null : this.getCityName().equals(other.getCityName()))
				        		&&(this.getXianquId() == null ? other.getId() == null : this.getXianquId().equals(other.getXianquId()))
				        		&&(this.getXianquName() == null ? other.getId() == null : this.getXianquName().equals(other.getXianquName()))
				        		&&(this.getGiveInformation() == null ? other.getId() == null : this.getGiveInformation().equals(other.getGiveInformation()))
				        		&&(this.getNowPt() == null ? other.getId() == null : this.getNowPt().equals(other.getNowPt()))
				        		&&(this.getLocation() == null ? other.getId() == null : this.getLocation().equals(other.getLocation()))
				        		&&(this.getRemark() == null ? other.getId() == null : this.getRemark().equals(other.getRemark()))
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))
				;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
		        result = prime * result + ((getCreateUserName() == null) ? 0 : getCreateUserName().hashCode());
		        result = prime * result + ((getOwnerCarInfoId() == null) ? 0 : getOwnerCarInfoId().hashCode());
		        result = prime * result + ((getCarPlateNum() == null) ? 0 : getCarPlateNum().hashCode());
		        result = prime * result + ((getCarFrameNum() == null) ? 0 : getCarFrameNum().hashCode());
		        result = prime * result + ((getCarPlateColor() == null) ? 0 : getCarPlateColor().hashCode());
		        result = prime * result + ((getChangeNetType() == null) ? 0 : getChangeNetType().hashCode());
		        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
		        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
		        result = prime * result + ((getTerminalId() == null) ? 0 : getTerminalId().hashCode());
		        result = prime * result + ((getTerminalChangshang() == null) ? 0 : getTerminalChangshang().hashCode());
		        result = prime * result + ((getTerminalCode() == null) ? 0 : getTerminalCode().hashCode());
		        result = prime * result + ((getSimNum() == null) ? 0 : getSimNum().hashCode());
		        result = prime * result + ((getOrgPt() == null) ? 0 : getOrgPt().hashCode());
		        result = prime * result + ((getSubmitPerson() == null) ? 0 : getSubmitPerson().hashCode());
		        result = prime * result + ((getSubmitTime() == null) ? 0 : getSubmitTime().hashCode());
		        result = prime * result + ((getProvinceId() == null) ? 0 : getProvinceId().hashCode());
		        result = prime * result + ((getProvinceName() == null) ? 0 : getProvinceName().hashCode());
		        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());
		        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());
		        result = prime * result + ((getXianquId() == null) ? 0 : getXianquId().hashCode());
		        result = prime * result + ((getXianquName() == null) ? 0 : getXianquName().hashCode());
		        result = prime * result + ((getGiveInformation() == null) ? 0 : getGiveInformation().hashCode());
		        result = prime * result + ((getNowPt() == null) ? 0 : getNowPt().hashCode());
		        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
		        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());
		;
        return result;
    }

}

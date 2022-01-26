package com.edgedo.sys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_car_info")
public class ErpCarInfo implements Serializable{
	
		
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
	 * 属性描述:车牌号
	 */
	@TableField(value="CAR_PLATE_NUM",exist=true)
	java.lang.String carPlateNum;
	
	/**
	 * 属性描述:车牌颜色
	 */
	@TableField(value="CAR_PLATE_COLOR",exist=true)
	java.lang.String carPlateColor;
	
	/**
	 * 属性描述:车架号
	 */
	@TableField(value="CAR_FRAME_NUM",exist=true)
	java.lang.String carFrameNum;
	
	/**
	 * 属性描述:道路运输证号
	 */
	@TableField(value="ROAD_TRANSPORT_NUM",exist=true)
	java.lang.String roadTransportNum;
	
	/**
	 * 属性描述:经营许可证号
	 */
	@TableField(value="BUSINESS_LICENSE_NUMBER",exist=true)
	java.lang.String businessLicenseNumber;
	
	/**
	 * 属性描述:业户名称
	 */
	@TableField(value="YEHU_NAME",exist=true)
	java.lang.String yehuName;
	
	/**
	 * 属性描述:所属车队ID
	 */
	@TableField(value="OWNER_TEAM_ID",exist=true)
	java.lang.String ownerTeamId;
	
	/**
	 * 属性描述:车队名
	 */
	@TableField(value="OWNER_TEAM_NAME",exist=true)
	java.lang.String ownerTeamName;
	
	/**
	 * 属性描述:联系人
	 */
	@TableField(value="CONTACT_PERSON",exist=true)
	java.lang.String contactPerson;
	
	/**
	 * 属性描述:联系电话
	 */
	@TableField(value="CONTACT_PHONE_NUM",exist=true)
	java.lang.String contactPhoneNum;
	
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
	 * 属性描述:城市ID
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
	 * 供应商ID
	 */
	@TableField(value="SUPPLIER_ID",exist=true)
	java.lang.String supplierId;

	/**
	 * 供应商名
	 */
	@TableField(value="SUPPLIER_NAME",exist=true)
	java.lang.String supplierName;

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
	 * 属性描述:终端安装类型
	 */
	@TableField(value="TERMINAL_INSTALL_TYPE",exist=true)
	java.lang.String terminalInstallType;


	/**
	 * 属性描述:SIM主键
	 */
	@TableField(value="SIM_ID",exist=true)
	java.lang.String simId;
	/**
	 * 属性描述:SIM卡号
	 */
	@TableField(value="SIM_CODE",exist=true)
	java.lang.String simCode;
	
	/**
	 * 属性描述:渠道代理ID
	 */
	@TableField(value="CHANNEL_AGENT_ID",exist=true)
	java.lang.String channelAgentId;
	
	/**
	 * 属性描述:渠道代理
	 */
	@TableField(value="CHANNEL_AGENT_NAME",exist=true)
	java.lang.String channelAgentName;
	
	/**
	 * 属性描述:业务人员ID
	 */
	@TableField(value="YEWU_USER_ID",exist=true)
	java.lang.String yewuUserId;
	
	/**
	 * 属性描述:业务人员名
	 */
	@TableField(value="YEWU_USER_NAME",exist=true)
	java.lang.String yewuUserName;
	
	/**
	 * 属性描述:安装人员ID
	 */
	@TableField(value="INSTALL_USER_ID",exist=true)
	java.lang.String installUserId;
	
	/**
	 * 属性描述:安装人员名
	 */
	@TableField(value="INSTALL_USER_NAME",exist=true)
	java.lang.String installUserName;
	
	/**
	 * 属性描述:录入人员ID
	 */
	@TableField(value="INPUT_USER_ID",exist=true)
	java.lang.String inputUserId;
	
	/**
	 * 属性描述:录入人员名
	 */
	@TableField(value="INPUT_USER_NAME",exist=true)
	java.lang.String inputUserName;
	
	/**
	 * 属性描述:北斗平台
	 */
	@TableField(value="BEIDOU_OPERATOR",exist=true)
	java.lang.String beidouOperator;
	
	/**
	 * 属性描述:安装时间
	 */
	@TableField(value="INSTALLATION_TIME",exist=true)
	java.util.Date installationTime;

	/**
	 * 属性描述:是否收款
	 */
	@TableField(value="PAY_STATE",exist=true)
	java.lang.String payState;
	/**
	 * 属性描述:收款金额
	 */
	@TableField(value="GET_MONEY_NUM",exist=true)
	java.math.BigDecimal getMoneyNum;
	/**
	 * 属性描述:收款人
	 */
	@TableField(value="GET_MONEY_USER",exist=true)
	java.lang.String getMoneyUser;
	/**
	 * 属性描述:收款方式
	 */
	@TableField(value="GET_MONEY_TYPE",exist=true)
	java.lang.String getMoneyType;
	/**
	 * 属性描述:服务费抵扣
	 */
	@TableField(value="SERVICE_MONEY_DEDUCTION",exist=true)
	java.math.BigDecimal serviceMoneyDeduction;

	/**
	 * 属性描述:业户负责人
	 */
	@TableField(value="YEHU_PERSON",exist=true)
	java.lang.String yehuPerson;
	
	/**
	 * 属性描述:到期时间
	 */
	@TableField(value="END_TIME",exist=true)
	java.util.Date endTime;
	
	/**
	 * 属性描述:上次续费时间
	 */
	@TableField(value="LAST_XUFEI_TIME",exist=true)
	java.util.Date lastXufeiTime;

	/**
	 * 属性描述:手机验证状态
	 */
	@TableField(value="PHONE_VERIFICATION_STATE",exist=true)
	java.lang.String phoneVerificationState;

	/**
	 * 属性描述:首次上线时间
	 */
	@TableField(value="FIRST_ONLINE_TIME",exist=true)
	java.util.Date firstOnlineTime;
	
	/**
	 * 属性描述:备注
	 */
	@TableField(value="REMARK",exist=true)
	java.lang.String remark;

	/**
	 * 属性描述：服务费到期时间
	 */
	@TableField(value = "SERVICE_MONEY_END_TIME",exist = true)
	java.util.Date serviceMoneyEndTime;

	@TableField(value = "DELETE_TIME",exist = true)
	java.util.Date deleteTime;
	/**
	 * 属性描述：入网时间
	 */
	@TableField(value = "ADMISSION_TIME",exist = true)
	java.util.Date admissionTime;
	/**
	 * 属性描述:删除原因
	 */
	@TableField(value="DELETE_REMARK",exist=true)
	java.lang.String deleteRemark;

	/**
	 * 属性描述:前装渠道
	 */
	@TableField(value="INSTALL_CHANNEL",exist=true)
	java.lang.String installChannel;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;

	public String getInstallChannel() {
		return installChannel;
	}

	public void setInstallChannel(String installChannel) {
		this.installChannel = installChannel;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getDeleteRemark() {
		return deleteRemark;
	}

	public void setDeleteRemark(String deleteRemark) {
		this.deleteRemark = deleteRemark;
	}

	public String getSimId() {
		return simId;
	}

	public void setSimId(String simId) {
		this.simId = simId;
	}

	public String getPhoneVerificationState() {
		return phoneVerificationState;
	}

	public void setPhoneVerificationState(String phoneVerificationState) {
		this.phoneVerificationState = phoneVerificationState;
	}

	public Date getFirstOnlineTime() {
		return firstOnlineTime;
	}

	public void setFirstOnlineTime(Date firstOnlineTime) {
		this.firstOnlineTime = firstOnlineTime;
	}

	public Date getInstallationTime() {
		return installationTime;
	}

	public void setInstallationTime(Date installationTime) {
		this.installationTime = installationTime;
	}

	public BigDecimal getGetMoneyNum() {
		return getMoneyNum;
	}

	public void setGetMoneyNum(BigDecimal getMoneyNum) {
		this.getMoneyNum = getMoneyNum;
	}

	public String getGetMoneyUser() {
		return getMoneyUser;
	}

	public void setGetMoneyUser(String getMoneyUser) {
		this.getMoneyUser = getMoneyUser;
	}

	public String getGetMoneyType() {
		return getMoneyType;
	}

	public void setGetMoneyType(String getMoneyType) {
		this.getMoneyType = getMoneyType;
	}

	public BigDecimal getServiceMoneyDeduction() {
		return serviceMoneyDeduction;
	}

	public void setServiceMoneyDeduction(BigDecimal serviceMoneyDeduction) {
		this.serviceMoneyDeduction = serviceMoneyDeduction;
	}

	public String getYehuPerson() {
		return yehuPerson;
	}

	public void setYehuPerson(String yehuPerson) {
		this.yehuPerson = yehuPerson;
	}

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
	
	
	public java.lang.String getCarPlateNum(){
		return this.carPlateNum;
	}
	
	public void setCarPlateNum(java.lang.String carPlateNum){
		this.carPlateNum=carPlateNum;
	}
	
	
	public java.lang.String getCarPlateColor(){
		return this.carPlateColor;
	}
	
	public void setCarPlateColor(java.lang.String carPlateColor){
		this.carPlateColor=carPlateColor;
	}
	
	
	public java.lang.String getCarFrameNum(){
		return this.carFrameNum;
	}
	
	public void setCarFrameNum(java.lang.String carFrameNum){
		this.carFrameNum=carFrameNum;
	}
	
	
	public java.lang.String getRoadTransportNum(){
		return this.roadTransportNum;
	}
	
	public void setRoadTransportNum(java.lang.String roadTransportNum){
		this.roadTransportNum=roadTransportNum;
	}
	
	
	public java.lang.String getBusinessLicenseNumber(){
		return this.businessLicenseNumber;
	}
	
	public void setBusinessLicenseNumber(java.lang.String businessLicenseNumber){
		this.businessLicenseNumber=businessLicenseNumber;
	}
	
	
	public java.lang.String getYehuName(){
		return this.yehuName;
	}
	
	public void setYehuName(java.lang.String yehuName){
		this.yehuName=yehuName;
	}
	
	
	public java.lang.String getOwnerTeamId(){
		return this.ownerTeamId;
	}
	
	public void setOwnerTeamId(java.lang.String ownerTeamId){
		this.ownerTeamId=ownerTeamId;
	}
	
	
	public java.lang.String getOwnerTeamName(){
		return this.ownerTeamName;
	}
	
	public void setOwnerTeamName(java.lang.String ownerTeamName){
		this.ownerTeamName=ownerTeamName;
	}
	
	
	public java.lang.String getContactPerson(){
		return this.contactPerson;
	}
	
	public void setContactPerson(java.lang.String contactPerson){
		this.contactPerson=contactPerson;
	}
	
	
	public java.lang.String getContactPhoneNum(){
		return this.contactPhoneNum;
	}
	
	public void setContactPhoneNum(java.lang.String contactPhoneNum){
		this.contactPhoneNum=contactPhoneNum;
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

	public java.lang.String getSupplierId(){
		return this.supplierId;
	}

	public void setSupplierId(java.lang.String supplierId){
		this.supplierId=supplierId;
	}

	public java.lang.String getSupplierName(){
		return this.supplierName;
	}

	public void setSupplierName(java.lang.String supplierName){
		this.supplierName=supplierName;
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
	
	
	public java.lang.String getTerminalInstallType(){
		return this.terminalInstallType;
	}
	
	public void setTerminalInstallType(java.lang.String terminalInstallType){
		this.terminalInstallType=terminalInstallType;
	}
	
	
	public java.lang.String getSimCode(){
		return this.simCode;
	}
	
	public void setSimCode(java.lang.String simCode){
		this.simCode=simCode;
	}
	
	
	public java.lang.String getChannelAgentId(){
		return this.channelAgentId;
	}
	
	public void setChannelAgentId(java.lang.String channelAgentId){
		this.channelAgentId=channelAgentId;
	}
	
	
	public java.lang.String getChannelAgentName(){
		return this.channelAgentName;
	}
	
	public void setChannelAgentName(java.lang.String channelAgentName){
		this.channelAgentName=channelAgentName;
	}
	
	
	public java.lang.String getYewuUserId(){
		return this.yewuUserId;
	}
	
	public void setYewuUserId(java.lang.String yewuUserId){
		this.yewuUserId=yewuUserId;
	}
	
	
	public java.lang.String getYewuUserName(){
		return this.yewuUserName;
	}
	
	public void setYewuUserName(java.lang.String yewuUserName){
		this.yewuUserName=yewuUserName;
	}
	
	
	public java.lang.String getInstallUserId(){
		return this.installUserId;
	}
	
	public void setInstallUserId(java.lang.String installUserId){
		this.installUserId=installUserId;
	}
	
	
	public java.lang.String getInstallUserName(){
		return this.installUserName;
	}
	
	public void setInstallUserName(java.lang.String installUserName){
		this.installUserName=installUserName;
	}
	
	
	public java.lang.String getInputUserId(){
		return this.inputUserId;
	}
	
	public void setInputUserId(java.lang.String inputUserId){
		this.inputUserId=inputUserId;
	}
	
	
	public java.lang.String getInputUserName(){
		return this.inputUserName;
	}
	
	public void setInputUserName(java.lang.String inputUserName){
		this.inputUserName=inputUserName;
	}
	
	
	public java.lang.String getBeidouOperator(){
		return this.beidouOperator;
	}
	
	public void setBeidouOperator(java.lang.String beidouOperator){
		this.beidouOperator=beidouOperator;
	}
	
	
	public java.lang.String getPayState(){
		return this.payState;
	}
	
	public void setPayState(java.lang.String payState){
		this.payState=payState;
	}
	
	
	public java.util.Date getEndTime(){
		return this.endTime;
	}
	
	public void setEndTime(java.util.Date endTime){
		this.endTime=endTime;
	}
	
	
	public java.util.Date getLastXufeiTime(){
		return this.lastXufeiTime;
	}
	
	public void setLastXufeiTime(java.util.Date lastXufeiTime){
		this.lastXufeiTime=lastXufeiTime;
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

	public Date getServiceMoneyEndTime() {
		return serviceMoneyEndTime;
	}

	public void setServiceMoneyEndTime(Date serviceMoneyEndTime) {
		this.serviceMoneyEndTime = serviceMoneyEndTime;
	}

	public Date getAdmissionTime() {
		return admissionTime;
	}

	public void setAdmissionTime(Date admissionTime) {
		this.admissionTime = admissionTime;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", createTime=").append(createTime);			
			sb.append(", createUserId=").append(createUserId);			
			sb.append(", createUserName=").append(createUserName);			
			sb.append(", carPlateNum=").append(carPlateNum);			
			sb.append(", carPlateColor=").append(carPlateColor);			
			sb.append(", carFrameNum=").append(carFrameNum);			
			sb.append(", roadTransportNum=").append(roadTransportNum);			
			sb.append(", businessLicenseNumber=").append(businessLicenseNumber);			
			sb.append(", yehuName=").append(yehuName);			
			sb.append(", ownerTeamId=").append(ownerTeamId);			
			sb.append(", ownerTeamName=").append(ownerTeamName);			
			sb.append(", contactPerson=").append(contactPerson);			
			sb.append(", contactPhoneNum=").append(contactPhoneNum);			
			sb.append(", provinceId=").append(provinceId);			
			sb.append(", provinceName=").append(provinceName);			
			sb.append(", cityId=").append(cityId);			
			sb.append(", cityName=").append(cityName);
			sb.append(", xianquId=").append(xianquId);
			sb.append(", supplierId=").append(supplierId);
			sb.append(", supplierName=").append(supplierName);
			sb.append(", xianquName=").append(xianquName);
			sb.append(", terminalId=").append(terminalId);			
			sb.append(", terminalChangshang=").append(terminalChangshang);			
			sb.append(", terminalCode=").append(terminalCode);			
			sb.append(", terminalInstallType=").append(terminalInstallType);			
			sb.append(", simCode=").append(simCode);			
			sb.append(", channelAgentId=").append(channelAgentId);			
			sb.append(", channelAgentName=").append(channelAgentName);			
			sb.append(", yewuUserId=").append(yewuUserId);			
			sb.append(", yewuUserName=").append(yewuUserName);			
			sb.append(", installUserId=").append(installUserId);			
			sb.append(", installUserName=").append(installUserName);			
			sb.append(", inputUserId=").append(inputUserId);			
			sb.append(", inputUserName=").append(inputUserName);			
			sb.append(", beidouOperator=").append(beidouOperator);			
			sb.append(", payState=").append(payState);			
			sb.append(", endTime=").append(endTime);			
			sb.append(", lastXufeiTime=").append(lastXufeiTime);			
			sb.append(", remark=").append(remark);
		sb.append(", admissionTime=").append(admissionTime);
		sb.append(", serviceMoneyEndTime=").append(serviceMoneyEndTime);
		sb.append(", dataState=").append(dataState);
        sb.append("]");
        return sb.toString();
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
        ErpCarInfo other = (ErpCarInfo) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getCarPlateNum() == null ? other.getId() == null : this.getCarPlateNum().equals(other.getCarPlateNum()))		
				        		&&(this.getCarPlateColor() == null ? other.getId() == null : this.getCarPlateColor().equals(other.getCarPlateColor()))		
				        		&&(this.getCarFrameNum() == null ? other.getId() == null : this.getCarFrameNum().equals(other.getCarFrameNum()))		
				        		&&(this.getRoadTransportNum() == null ? other.getId() == null : this.getRoadTransportNum().equals(other.getRoadTransportNum()))		
				        		&&(this.getBusinessLicenseNumber() == null ? other.getId() == null : this.getBusinessLicenseNumber().equals(other.getBusinessLicenseNumber()))		
				        		&&(this.getYehuName() == null ? other.getId() == null : this.getYehuName().equals(other.getYehuName()))		
				        		&&(this.getOwnerTeamId() == null ? other.getId() == null : this.getOwnerTeamId().equals(other.getOwnerTeamId()))		
				        		&&(this.getOwnerTeamName() == null ? other.getId() == null : this.getOwnerTeamName().equals(other.getOwnerTeamName()))		
				        		&&(this.getContactPerson() == null ? other.getId() == null : this.getContactPerson().equals(other.getContactPerson()))		
				        		&&(this.getContactPhoneNum() == null ? other.getId() == null : this.getContactPhoneNum().equals(other.getContactPhoneNum()))		
				        		&&(this.getProvinceId() == null ? other.getId() == null : this.getProvinceId().equals(other.getProvinceId()))		
				        		&&(this.getProvinceName() == null ? other.getId() == null : this.getProvinceName().equals(other.getProvinceName()))		
				        		&&(this.getCityId() == null ? other.getId() == null : this.getCityId().equals(other.getCityId()))		
				        		&&(this.getCityName() == null ? other.getId() == null : this.getCityName().equals(other.getCityName()))		
				        		&&(this.getXianquId() == null ? other.getId() == null : this.getXianquId().equals(other.getXianquId()))
				        		&&(this.getXianquName() == null ? other.getId() == null : this.getXianquName().equals(other.getXianquName()))
								&&(this.getSupplierId() == null ? other.getId() == null : this.getSupplierId().equals(other.getSupplierId()))
								&&(this.getSupplierName() == null ? other.getId() == null : this.getSupplierName().equals(other.getSupplierName()))
								&&(this.getTerminalId() == null ? other.getId() == null : this.getTerminalId().equals(other.getTerminalId()))
				        		&&(this.getTerminalChangshang() == null ? other.getId() == null : this.getTerminalChangshang().equals(other.getTerminalChangshang()))		
				        		&&(this.getTerminalCode() == null ? other.getId() == null : this.getTerminalCode().equals(other.getTerminalCode()))		
				        		&&(this.getTerminalInstallType() == null ? other.getId() == null : this.getTerminalInstallType().equals(other.getTerminalInstallType()))		
				        		&&(this.getSimCode() == null ? other.getId() == null : this.getSimCode().equals(other.getSimCode()))		
				        		&&(this.getChannelAgentId() == null ? other.getId() == null : this.getChannelAgentId().equals(other.getChannelAgentId()))		
				        		&&(this.getChannelAgentName() == null ? other.getId() == null : this.getChannelAgentName().equals(other.getChannelAgentName()))		
				        		&&(this.getYewuUserId() == null ? other.getId() == null : this.getYewuUserId().equals(other.getYewuUserId()))		
				        		&&(this.getYewuUserName() == null ? other.getId() == null : this.getYewuUserName().equals(other.getYewuUserName()))		
				        		&&(this.getInstallUserId() == null ? other.getId() == null : this.getInstallUserId().equals(other.getInstallUserId()))		
				        		&&(this.getInstallUserName() == null ? other.getId() == null : this.getInstallUserName().equals(other.getInstallUserName()))		
				        		&&(this.getInputUserId() == null ? other.getId() == null : this.getInputUserId().equals(other.getInputUserId()))		
				        		&&(this.getInputUserName() == null ? other.getId() == null : this.getInputUserName().equals(other.getInputUserName()))		
				        		&&(this.getBeidouOperator() == null ? other.getId() == null : this.getBeidouOperator().equals(other.getBeidouOperator()))		
				        		&&(this.getPayState() == null ? other.getId() == null : this.getPayState().equals(other.getPayState()))		
				        		&&(this.getEndTime() == null ? other.getId() == null : this.getEndTime().equals(other.getEndTime()))		
				        		&&(this.getLastXufeiTime() == null ? other.getId() == null : this.getLastXufeiTime().equals(other.getLastXufeiTime()))		
				        		&&(this.getRemark() == null ? other.getId() == null : this.getRemark().equals(other.getRemark()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))
				&&(this.getServiceMoneyEndTime() == null ? other.getId() == null : this.getServiceMoneyEndTime().equals(other.getServiceMoneyEndTime()))
				&&(this.getAdmissionTime() == null ? other.getId() == null : this.getAdmissionTime().equals(other.getAdmissionTime()))
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
		        result = prime * result + ((getCarPlateNum() == null) ? 0 : getCarPlateNum().hashCode());		
		        result = prime * result + ((getCarPlateColor() == null) ? 0 : getCarPlateColor().hashCode());		
		        result = prime * result + ((getCarFrameNum() == null) ? 0 : getCarFrameNum().hashCode());		
		        result = prime * result + ((getRoadTransportNum() == null) ? 0 : getRoadTransportNum().hashCode());		
		        result = prime * result + ((getBusinessLicenseNumber() == null) ? 0 : getBusinessLicenseNumber().hashCode());		
		        result = prime * result + ((getYehuName() == null) ? 0 : getYehuName().hashCode());		
		        result = prime * result + ((getOwnerTeamId() == null) ? 0 : getOwnerTeamId().hashCode());		
		        result = prime * result + ((getOwnerTeamName() == null) ? 0 : getOwnerTeamName().hashCode());		
		        result = prime * result + ((getContactPerson() == null) ? 0 : getContactPerson().hashCode());		
		        result = prime * result + ((getContactPhoneNum() == null) ? 0 : getContactPhoneNum().hashCode());		
		        result = prime * result + ((getProvinceId() == null) ? 0 : getProvinceId().hashCode());		
		        result = prime * result + ((getProvinceName() == null) ? 0 : getProvinceName().hashCode());		
		        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());		
		        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());		
		        result = prime * result + ((getXianquId() == null) ? 0 : getXianquId().hashCode());		
		        result = prime * result + ((getXianquName() == null) ? 0 : getXianquName().hashCode());
		        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
		        result = prime * result + ((getSupplierName() == null) ? 0 : getSupplierName().hashCode());
		        result = prime * result + ((getTerminalId() == null) ? 0 : getTerminalId().hashCode());		
		        result = prime * result + ((getTerminalChangshang() == null) ? 0 : getTerminalChangshang().hashCode());		
		        result = prime * result + ((getTerminalCode() == null) ? 0 : getTerminalCode().hashCode());		
		        result = prime * result + ((getTerminalInstallType() == null) ? 0 : getTerminalInstallType().hashCode());		
		        result = prime * result + ((getSimCode() == null) ? 0 : getSimCode().hashCode());		
		        result = prime * result + ((getChannelAgentId() == null) ? 0 : getChannelAgentId().hashCode());		
		        result = prime * result + ((getChannelAgentName() == null) ? 0 : getChannelAgentName().hashCode());		
		        result = prime * result + ((getYewuUserId() == null) ? 0 : getYewuUserId().hashCode());		
		        result = prime * result + ((getYewuUserName() == null) ? 0 : getYewuUserName().hashCode());		
		        result = prime * result + ((getInstallUserId() == null) ? 0 : getInstallUserId().hashCode());		
		        result = prime * result + ((getInstallUserName() == null) ? 0 : getInstallUserName().hashCode());		
		        result = prime * result + ((getInputUserId() == null) ? 0 : getInputUserId().hashCode());		
		        result = prime * result + ((getInputUserName() == null) ? 0 : getInputUserName().hashCode());		
		        result = prime * result + ((getBeidouOperator() == null) ? 0 : getBeidouOperator().hashCode());		
		        result = prime * result + ((getPayState() == null) ? 0 : getPayState().hashCode());		
		        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());		
		        result = prime * result + ((getLastXufeiTime() == null) ? 0 : getLastXufeiTime().hashCode());
				result = prime * result + ((getAdmissionTime() == null) ? 0 : getAdmissionTime().hashCode());
				result = prime * result + ((getServiceMoneyEndTime() == null) ? 0 : getServiceMoneyEndTime().hashCode());
		        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

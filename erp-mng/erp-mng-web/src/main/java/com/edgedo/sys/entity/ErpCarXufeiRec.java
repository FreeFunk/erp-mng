package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_car_xufei_rec")
public class ErpCarXufeiRec implements Serializable{
	
		
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
	 * 属性描述:续费时间
	 */
	@TableField(value="XUFEI_TIME",exist=true)
	java.util.Date xufeiTime;
	
	/**
	 * 属性描述:续费金额
	 */
	@TableField(value="XUFEI_MONEY",exist=true)
	java.math.BigDecimal xufeiMoney;
	
	/**
	 * 属性描述:是否前装续费(前装续费，非前装续费)
	 */
	@TableField(value="IS_INSTALL_XUFEI",exist=true)
	java.lang.String isInstallXufei;
	
	/**
	 * 属性描述:前装续费金额
	 */
	@TableField(value="INSTALL_XUFEI_MONEY",exist=true)
	java.math.BigDecimal installXufeiMoney;
	
	/**
	 * 属性描述:供应商ID
	 */
	@TableField(value="SUPPLIER_ID",exist=true)
	java.lang.String supplierId;
	
	/**
	 * 属性描述:供应商名
	 */
	@TableField(value="SUPPLIER_NAME",exist=true)
	java.lang.String supplierName;
	
	/**
	 * 属性描述:付费人
	 */
	@TableField(value="PAY_USER",exist=true)
	java.lang.String payUser;
	
	/**
	 * 属性描述:收款人
	 */
	@TableField(value="GET_MONEY_USER",exist=true)
	java.lang.String getMoneyUser;
	
	/**
	 * 属性描述:服务开始时间
	 */
	@TableField(value="SERVICE_START_TIME",exist=true)
	java.util.Date serviceStartTime;
	
	/**
	 * 属性描述:服务结束时间
	 */
	@TableField(value="SERVICE_END_TIME",exist=true)
	java.util.Date serviceEndTime;
	
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
	
	
	public java.util.Date getXufeiTime(){
		return this.xufeiTime;
	}
	
	public void setXufeiTime(java.util.Date xufeiTime){
		this.xufeiTime=xufeiTime;
	}
	
	
	public java.math.BigDecimal getXufeiMoney(){
		return this.xufeiMoney;
	}
	
	public void setXufeiMoney(java.math.BigDecimal xufeiMoney){
		this.xufeiMoney=xufeiMoney;
	}
	
	
	public java.lang.String getIsInstallXufei(){
		return this.isInstallXufei;
	}
	
	public void setIsInstallXufei(java.lang.String isInstallXufei){
		this.isInstallXufei=isInstallXufei;
	}
	
	
	public java.math.BigDecimal getInstallXufeiMoney(){
		return this.installXufeiMoney;
	}
	
	public void setInstallXufeiMoney(java.math.BigDecimal installXufeiMoney){
		this.installXufeiMoney=installXufeiMoney;
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
	
	
	public java.lang.String getPayUser(){
		return this.payUser;
	}
	
	public void setPayUser(java.lang.String payUser){
		this.payUser=payUser;
	}
	
	
	public java.lang.String getGetMoneyUser(){
		return this.getMoneyUser;
	}
	
	public void setGetMoneyUser(java.lang.String getMoneyUser){
		this.getMoneyUser=getMoneyUser;
	}
	
	
	public java.util.Date getServiceStartTime(){
		return this.serviceStartTime;
	}
	
	public void setServiceStartTime(java.util.Date serviceStartTime){
		this.serviceStartTime=serviceStartTime;
	}
	
	
	public java.util.Date getServiceEndTime(){
		return this.serviceEndTime;
	}
	
	public void setServiceEndTime(java.util.Date serviceEndTime){
		this.serviceEndTime=serviceEndTime;
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
			sb.append(", ownerCarInfoId=").append(ownerCarInfoId);			
			sb.append(", carPlateNum=").append(carPlateNum);			
			sb.append(", carPlateColor=").append(carPlateColor);			
			sb.append(", carFrameNum=").append(carFrameNum);			
			sb.append(", channelAgentId=").append(channelAgentId);			
			sb.append(", channelAgentName=").append(channelAgentName);			
			sb.append(", xufeiTime=").append(xufeiTime);			
			sb.append(", xufeiMoney=").append(xufeiMoney);			
			sb.append(", isInstallXufei=").append(isInstallXufei);			
			sb.append(", installXufeiMoney=").append(installXufeiMoney);			
			sb.append(", supplierId=").append(supplierId);			
			sb.append(", supplierName=").append(supplierName);			
			sb.append(", payUser=").append(payUser);			
			sb.append(", getMoneyUser=").append(getMoneyUser);			
			sb.append(", serviceStartTime=").append(serviceStartTime);			
			sb.append(", serviceEndTime=").append(serviceEndTime);			
			sb.append(", remark=").append(remark);			
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
        ErpCarXufeiRec other = (ErpCarXufeiRec) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getOwnerCarInfoId() == null ? other.getId() == null : this.getOwnerCarInfoId().equals(other.getOwnerCarInfoId()))		
				        		&&(this.getCarPlateNum() == null ? other.getId() == null : this.getCarPlateNum().equals(other.getCarPlateNum()))		
				        		&&(this.getCarPlateColor() == null ? other.getId() == null : this.getCarPlateColor().equals(other.getCarPlateColor()))		
				        		&&(this.getCarFrameNum() == null ? other.getId() == null : this.getCarFrameNum().equals(other.getCarFrameNum()))		
				        		&&(this.getChannelAgentId() == null ? other.getId() == null : this.getChannelAgentId().equals(other.getChannelAgentId()))		
				        		&&(this.getChannelAgentName() == null ? other.getId() == null : this.getChannelAgentName().equals(other.getChannelAgentName()))		
				        		&&(this.getXufeiTime() == null ? other.getId() == null : this.getXufeiTime().equals(other.getXufeiTime()))		
				        		&&(this.getXufeiMoney() == null ? other.getId() == null : this.getXufeiMoney().equals(other.getXufeiMoney()))		
				        		&&(this.getIsInstallXufei() == null ? other.getId() == null : this.getIsInstallXufei().equals(other.getIsInstallXufei()))		
				        		&&(this.getInstallXufeiMoney() == null ? other.getId() == null : this.getInstallXufeiMoney().equals(other.getInstallXufeiMoney()))		
				        		&&(this.getSupplierId() == null ? other.getId() == null : this.getSupplierId().equals(other.getSupplierId()))		
				        		&&(this.getSupplierName() == null ? other.getId() == null : this.getSupplierName().equals(other.getSupplierName()))		
				        		&&(this.getPayUser() == null ? other.getId() == null : this.getPayUser().equals(other.getPayUser()))		
				        		&&(this.getGetMoneyUser() == null ? other.getId() == null : this.getGetMoneyUser().equals(other.getGetMoneyUser()))		
				        		&&(this.getServiceStartTime() == null ? other.getId() == null : this.getServiceStartTime().equals(other.getServiceStartTime()))		
				        		&&(this.getServiceEndTime() == null ? other.getId() == null : this.getServiceEndTime().equals(other.getServiceEndTime()))		
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
		        result = prime * result + ((getCarPlateColor() == null) ? 0 : getCarPlateColor().hashCode());		
		        result = prime * result + ((getCarFrameNum() == null) ? 0 : getCarFrameNum().hashCode());		
		        result = prime * result + ((getChannelAgentId() == null) ? 0 : getChannelAgentId().hashCode());		
		        result = prime * result + ((getChannelAgentName() == null) ? 0 : getChannelAgentName().hashCode());		
		        result = prime * result + ((getXufeiTime() == null) ? 0 : getXufeiTime().hashCode());		
		        result = prime * result + ((getXufeiMoney() == null) ? 0 : getXufeiMoney().hashCode());		
		        result = prime * result + ((getIsInstallXufei() == null) ? 0 : getIsInstallXufei().hashCode());		
		        result = prime * result + ((getInstallXufeiMoney() == null) ? 0 : getInstallXufeiMoney().hashCode());		
		        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());		
		        result = prime * result + ((getSupplierName() == null) ? 0 : getSupplierName().hashCode());		
		        result = prime * result + ((getPayUser() == null) ? 0 : getPayUser().hashCode());		
		        result = prime * result + ((getGetMoneyUser() == null) ? 0 : getGetMoneyUser().hashCode());		
		        result = prime * result + ((getServiceStartTime() == null) ? 0 : getServiceStartTime().hashCode());		
		        result = prime * result + ((getServiceEndTime() == null) ? 0 : getServiceEndTime().hashCode());		
		        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

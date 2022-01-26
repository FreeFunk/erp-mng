package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_sim")
public class ErpSim implements Serializable{
	
		
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
	 * 属性描述:sim卡号
	 */
	@TableField(value="SIM_NUM",exist=true)
	java.lang.String simNum;
	
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
	 * 属性描述:套餐名
	 */
	@TableField(value="SET_MEAL_NAME",exist=true)
	java.lang.String setMealName;
	
	/**
	 * 属性描述:sim卡状态(未激活，自动激活，使用中，注销)
	 */
	@TableField(value="SIM_STATE",exist=true)
	java.lang.String simState;
	
	/**
	 * 属性描述:注销原因
	 */
	@TableField(value="OUT_REASON",exist=true)
	java.lang.String outReason;
	
	/**
	 * 属性描述:开卡时间
	 */
	@TableField(value="SIM_START_TIME",exist=true)
	java.util.Date simStartTime;
	
	/**
	 * 属性描述:沉默期(月)
	 */
	@TableField(value="SIM_RETICENT_NUM",exist=true)
	java.lang.Integer simReticentNum;
	
	/**
	 * 属性描述:卡的套餐描述
	 */
	@TableField(value="SIM_TAOCAN_DESC",exist=true)
	java.lang.String simTaocanDesc;
	
	/**
	 * 属性描述:付费类型(月付，年付)
	 */
	@TableField(value="PAY_TYPE",exist=true)
	java.lang.String payType;
	
	/**
	 * 属性描述:金额
	 */
	@TableField(value="PAY_MONEY",exist=true)
	java.math.BigDecimal payMoney;
	
	/**
	 * 属性描述:自动激活时间
	 */
	@TableField(value="ACTIVE_TIME",exist=true)
	java.util.Date activeTime;
	
	/**
	 * 属性描述:初次使用时间
	 */
	@TableField(value="FIRST_USE_TIME",exist=true)
	java.util.Date firstUseTime;
	
	/**
	 * 属性描述:续费时间
	 */
	@TableField(value="XUFEI_TIME",exist=true)
	java.util.Date xufeiTime;
	
	/**
	 * 属性描述:到期时间
	 */
	@TableField(value="END_TIME",exist=true)
	java.util.Date endTime;
	
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
	 * 属性描述:业户名称
	 */
	@TableField(value="YEHU_NAME",exist=true)
	java.lang.String yehuName;
	
	/**
	 * 属性描述:卡占用人ID
	 */
	@TableField(value="SIM_USER_ID",exist=true)
	java.lang.String simUserId;
	
	/**
	 * 属性描述:占用人TYPE
	 */
	@TableField(value="USER_TYPE",exist=true)
	java.lang.String userType;
	
	/**
	 * 属性描述:卡占占用人
	 */
	@TableField(value="SIM_USER_NAME",exist=true)
	java.lang.String simUserName;
	
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
	
	
	public java.lang.String getSimNum(){
		return this.simNum;
	}
	
	public void setSimNum(java.lang.String simNum){
		this.simNum=simNum;
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
	
	
	public java.lang.String getSetMealName(){
		return this.setMealName;
	}
	
	public void setSetMealName(java.lang.String setMealName){
		this.setMealName=setMealName;
	}
	
	
	public java.lang.String getSimState(){
		return this.simState;
	}
	
	public void setSimState(java.lang.String simState){
		this.simState=simState;
	}
	
	
	public java.lang.String getOutReason(){
		return this.outReason;
	}
	
	public void setOutReason(java.lang.String outReason){
		this.outReason=outReason;
	}
	
	
	public java.util.Date getSimStartTime(){
		return this.simStartTime;
	}
	
	public void setSimStartTime(java.util.Date simStartTime){
		this.simStartTime=simStartTime;
	}
	
	
	public java.lang.Integer getSimReticentNum(){
		return this.simReticentNum;
	}
	
	public void setSimReticentNum(java.lang.Integer simReticentNum){
		this.simReticentNum=simReticentNum;
	}
	
	
	public java.lang.String getSimTaocanDesc(){
		return this.simTaocanDesc;
	}
	
	public void setSimTaocanDesc(java.lang.String simTaocanDesc){
		this.simTaocanDesc=simTaocanDesc;
	}
	
	
	public java.lang.String getPayType(){
		return this.payType;
	}
	
	public void setPayType(java.lang.String payType){
		this.payType=payType;
	}
	
	
	public java.math.BigDecimal getPayMoney(){
		return this.payMoney;
	}
	
	public void setPayMoney(java.math.BigDecimal payMoney){
		this.payMoney=payMoney;
	}
	
	
	public java.util.Date getActiveTime(){
		return this.activeTime;
	}
	
	public void setActiveTime(java.util.Date activeTime){
		this.activeTime=activeTime;
	}
	
	
	public java.util.Date getFirstUseTime(){
		return this.firstUseTime;
	}
	
	public void setFirstUseTime(java.util.Date firstUseTime){
		this.firstUseTime=firstUseTime;
	}
	
	
	public java.util.Date getXufeiTime(){
		return this.xufeiTime;
	}
	
	public void setXufeiTime(java.util.Date xufeiTime){
		this.xufeiTime=xufeiTime;
	}
	
	
	public java.util.Date getEndTime(){
		return this.endTime;
	}
	
	public void setEndTime(java.util.Date endTime){
		this.endTime=endTime;
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
	
	
	public java.lang.String getYehuName(){
		return this.yehuName;
	}
	
	public void setYehuName(java.lang.String yehuName){
		this.yehuName=yehuName;
	}
	
	
	public java.lang.String getSimUserId(){
		return this.simUserId;
	}
	
	public void setSimUserId(java.lang.String simUserId){
		this.simUserId=simUserId;
	}
	
	
	public java.lang.String getUserType(){
		return this.userType;
	}
	
	public void setUserType(java.lang.String userType){
		this.userType=userType;
	}
	
	
	public java.lang.String getSimUserName(){
		return this.simUserName;
	}
	
	public void setSimUserName(java.lang.String simUserName){
		this.simUserName=simUserName;
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
			sb.append(", simNum=").append(simNum);			
			sb.append(", supplierId=").append(supplierId);			
			sb.append(", supplierName=").append(supplierName);			
			sb.append(", setMealName=").append(setMealName);			
			sb.append(", simState=").append(simState);			
			sb.append(", outReason=").append(outReason);			
			sb.append(", simStartTime=").append(simStartTime);			
			sb.append(", simReticentNum=").append(simReticentNum);			
			sb.append(", simTaocanDesc=").append(simTaocanDesc);			
			sb.append(", payType=").append(payType);			
			sb.append(", payMoney=").append(payMoney);			
			sb.append(", activeTime=").append(activeTime);			
			sb.append(", firstUseTime=").append(firstUseTime);			
			sb.append(", xufeiTime=").append(xufeiTime);			
			sb.append(", endTime=").append(endTime);			
			sb.append(", carPlateNum=").append(carPlateNum);			
			sb.append(", carFrameNum=").append(carFrameNum);			
			sb.append(", yehuName=").append(yehuName);			
			sb.append(", simUserId=").append(simUserId);			
			sb.append(", userType=").append(userType);			
			sb.append(", simUserName=").append(simUserName);			
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
        ErpSim other = (ErpSim) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getSimNum() == null ? other.getId() == null : this.getSimNum().equals(other.getSimNum()))		
				        		&&(this.getSupplierId() == null ? other.getId() == null : this.getSupplierId().equals(other.getSupplierId()))		
				        		&&(this.getSupplierName() == null ? other.getId() == null : this.getSupplierName().equals(other.getSupplierName()))		
				        		&&(this.getSetMealName() == null ? other.getId() == null : this.getSetMealName().equals(other.getSetMealName()))		
				        		&&(this.getSimState() == null ? other.getId() == null : this.getSimState().equals(other.getSimState()))		
				        		&&(this.getOutReason() == null ? other.getId() == null : this.getOutReason().equals(other.getOutReason()))		
				        		&&(this.getSimStartTime() == null ? other.getId() == null : this.getSimStartTime().equals(other.getSimStartTime()))		
				        		&&(this.getSimReticentNum() == null ? other.getId() == null : this.getSimReticentNum().equals(other.getSimReticentNum()))		
				        		&&(this.getSimTaocanDesc() == null ? other.getId() == null : this.getSimTaocanDesc().equals(other.getSimTaocanDesc()))		
				        		&&(this.getPayType() == null ? other.getId() == null : this.getPayType().equals(other.getPayType()))		
				        		&&(this.getPayMoney() == null ? other.getId() == null : this.getPayMoney().equals(other.getPayMoney()))		
				        		&&(this.getActiveTime() == null ? other.getId() == null : this.getActiveTime().equals(other.getActiveTime()))		
				        		&&(this.getFirstUseTime() == null ? other.getId() == null : this.getFirstUseTime().equals(other.getFirstUseTime()))		
				        		&&(this.getXufeiTime() == null ? other.getId() == null : this.getXufeiTime().equals(other.getXufeiTime()))		
				        		&&(this.getEndTime() == null ? other.getId() == null : this.getEndTime().equals(other.getEndTime()))		
				        		&&(this.getCarPlateNum() == null ? other.getId() == null : this.getCarPlateNum().equals(other.getCarPlateNum()))		
				        		&&(this.getCarFrameNum() == null ? other.getId() == null : this.getCarFrameNum().equals(other.getCarFrameNum()))		
				        		&&(this.getYehuName() == null ? other.getId() == null : this.getYehuName().equals(other.getYehuName()))		
				        		&&(this.getSimUserId() == null ? other.getId() == null : this.getSimUserId().equals(other.getSimUserId()))		
				        		&&(this.getUserType() == null ? other.getId() == null : this.getUserType().equals(other.getUserType()))		
				        		&&(this.getSimUserName() == null ? other.getId() == null : this.getSimUserName().equals(other.getSimUserName()))		
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
		        result = prime * result + ((getSimNum() == null) ? 0 : getSimNum().hashCode());		
		        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());		
		        result = prime * result + ((getSupplierName() == null) ? 0 : getSupplierName().hashCode());		
		        result = prime * result + ((getSetMealName() == null) ? 0 : getSetMealName().hashCode());		
		        result = prime * result + ((getSimState() == null) ? 0 : getSimState().hashCode());		
		        result = prime * result + ((getOutReason() == null) ? 0 : getOutReason().hashCode());		
		        result = prime * result + ((getSimStartTime() == null) ? 0 : getSimStartTime().hashCode());		
		        result = prime * result + ((getSimReticentNum() == null) ? 0 : getSimReticentNum().hashCode());		
		        result = prime * result + ((getSimTaocanDesc() == null) ? 0 : getSimTaocanDesc().hashCode());		
		        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());		
		        result = prime * result + ((getPayMoney() == null) ? 0 : getPayMoney().hashCode());		
		        result = prime * result + ((getActiveTime() == null) ? 0 : getActiveTime().hashCode());		
		        result = prime * result + ((getFirstUseTime() == null) ? 0 : getFirstUseTime().hashCode());		
		        result = prime * result + ((getXufeiTime() == null) ? 0 : getXufeiTime().hashCode());		
		        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());		
		        result = prime * result + ((getCarPlateNum() == null) ? 0 : getCarPlateNum().hashCode());		
		        result = prime * result + ((getCarFrameNum() == null) ? 0 : getCarFrameNum().hashCode());		
		        result = prime * result + ((getYehuName() == null) ? 0 : getYehuName().hashCode());		
		        result = prime * result + ((getSimUserId() == null) ? 0 : getSimUserId().hashCode());		
		        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());		
		        result = prime * result + ((getSimUserName() == null) ? 0 : getSimUserName().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

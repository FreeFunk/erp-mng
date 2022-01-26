package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_sim_xufei_rec")
public class ErpSimXufeiRec implements Serializable{
	
		
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
	 * 属性描述:所属SIM卡ID
	 */
	@TableField(value="OWNER_SIM_ID",exist=true)
	java.lang.String ownerSimId;
	
	/**
	 * 属性描述:sim卡号
	 */
	@TableField(value="SIM_NUM",exist=true)
	java.lang.String simNum;
	
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
	 * 属性描述:续费前到期时间
	 */
	@TableField(value="BEFORE_XUFEI_END_TIME",exist=true)
	java.util.Date beforeXufeiEndTime;
	
	/**
	 * 属性描述:到期时间
	 */
	@TableField(value="END_TIME",exist=true)
	java.util.Date endTime;
	
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
	
	
	public java.lang.String getOwnerSimId(){
		return this.ownerSimId;
	}
	
	public void setOwnerSimId(java.lang.String ownerSimId){
		this.ownerSimId=ownerSimId;
	}
	
	
	public java.lang.String getSimNum(){
		return this.simNum;
	}
	
	public void setSimNum(java.lang.String simNum){
		this.simNum=simNum;
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
	
	
	public java.util.Date getBeforeXufeiEndTime(){
		return this.beforeXufeiEndTime;
	}
	
	public void setBeforeXufeiEndTime(java.util.Date beforeXufeiEndTime){
		this.beforeXufeiEndTime=beforeXufeiEndTime;
	}
	
	
	public java.util.Date getEndTime(){
		return this.endTime;
	}
	
	public void setEndTime(java.util.Date endTime){
		this.endTime=endTime;
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
			sb.append(", ownerSimId=").append(ownerSimId);			
			sb.append(", simNum=").append(simNum);			
			sb.append(", xufeiTime=").append(xufeiTime);			
			sb.append(", xufeiMoney=").append(xufeiMoney);			
			sb.append(", beforeXufeiEndTime=").append(beforeXufeiEndTime);			
			sb.append(", endTime=").append(endTime);			
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
        ErpSimXufeiRec other = (ErpSimXufeiRec) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getOwnerSimId() == null ? other.getId() == null : this.getOwnerSimId().equals(other.getOwnerSimId()))		
				        		&&(this.getSimNum() == null ? other.getId() == null : this.getSimNum().equals(other.getSimNum()))		
				        		&&(this.getXufeiTime() == null ? other.getId() == null : this.getXufeiTime().equals(other.getXufeiTime()))		
				        		&&(this.getXufeiMoney() == null ? other.getId() == null : this.getXufeiMoney().equals(other.getXufeiMoney()))		
				        		&&(this.getBeforeXufeiEndTime() == null ? other.getId() == null : this.getBeforeXufeiEndTime().equals(other.getBeforeXufeiEndTime()))		
				        		&&(this.getEndTime() == null ? other.getId() == null : this.getEndTime().equals(other.getEndTime()))		
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
		        result = prime * result + ((getOwnerSimId() == null) ? 0 : getOwnerSimId().hashCode());		
		        result = prime * result + ((getSimNum() == null) ? 0 : getSimNum().hashCode());		
		        result = prime * result + ((getXufeiTime() == null) ? 0 : getXufeiTime().hashCode());		
		        result = prime * result + ((getXufeiMoney() == null) ? 0 : getXufeiMoney().hashCode());		
		        result = prime * result + ((getBeforeXufeiEndTime() == null) ? 0 : getBeforeXufeiEndTime().hashCode());		
		        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

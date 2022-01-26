package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_work_task_order_user")
public class ErpWorkTaskOrderUser implements Serializable{
	
		
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
	 * 属性描述:所属工单ID
	 */
	@TableField(value="OWNER_WORK_TASK_ORDER_ID",exist=true)
	java.lang.String ownerWorkTaskOrderId;
	
	/**
	 * 属性描述:所属员工ID
	 */
	@TableField(value="OWNER_USER_ID",exist=true)
	java.lang.String ownerUserId;
	
	/**
	 * 属性描述:所属员工名
	 */
	@TableField(value="OWNER_USER_NAME",exist=true)
	java.lang.String ownerUserName;
	
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
	
	
	public java.lang.String getOwnerWorkTaskOrderId(){
		return this.ownerWorkTaskOrderId;
	}
	
	public void setOwnerWorkTaskOrderId(java.lang.String ownerWorkTaskOrderId){
		this.ownerWorkTaskOrderId=ownerWorkTaskOrderId;
	}
	
	
	public java.lang.String getOwnerUserId(){
		return this.ownerUserId;
	}
	
	public void setOwnerUserId(java.lang.String ownerUserId){
		this.ownerUserId=ownerUserId;
	}
	
	
	public java.lang.String getOwnerUserName(){
		return this.ownerUserName;
	}
	
	public void setOwnerUserName(java.lang.String ownerUserName){
		this.ownerUserName=ownerUserName;
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
			sb.append(", ownerWorkTaskOrderId=").append(ownerWorkTaskOrderId);			
			sb.append(", ownerUserId=").append(ownerUserId);			
			sb.append(", ownerUserName=").append(ownerUserName);			
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
        ErpWorkTaskOrderUser other = (ErpWorkTaskOrderUser) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getOwnerWorkTaskOrderId() == null ? other.getId() == null : this.getOwnerWorkTaskOrderId().equals(other.getOwnerWorkTaskOrderId()))		
				        		&&(this.getOwnerUserId() == null ? other.getId() == null : this.getOwnerUserId().equals(other.getOwnerUserId()))		
				        		&&(this.getOwnerUserName() == null ? other.getId() == null : this.getOwnerUserName().equals(other.getOwnerUserName()))		
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
		        result = prime * result + ((getOwnerWorkTaskOrderId() == null) ? 0 : getOwnerWorkTaskOrderId().hashCode());		
		        result = prime * result + ((getOwnerUserId() == null) ? 0 : getOwnerUserId().hashCode());		
		        result = prime * result + ((getOwnerUserName() == null) ? 0 : getOwnerUserName().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

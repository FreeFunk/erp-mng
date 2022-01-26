package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_unlocation_remind")
public class ErpUnlocationRemind implements Serializable{
	
		
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
	 * 属性描述:提醒标题
	 */
	@TableField(value="REMIND_TITLE",exist=true)
	java.lang.String remindTitle;
	
	/**
	 * 属性描述:开始时间
	 */
	@TableField(value="START_TIME",exist=true)
	java.util.Date startTime;
	
	/**
	 * 属性描述:结束时间
	 */
	@TableField(value="END_TIME",exist=true)
	java.util.Date endTime;
	
	/**
	 * 属性描述:提醒车辆数
	 */
	@TableField(value="REMIND_CAR_NUM",exist=true)
	java.lang.Integer remindCarNum;
	
	/**
	 * 属性描述:发送短信数量
	 */
	@TableField(value="SEND_MSG_NUM",exist=true)
	java.lang.Integer sendMsgNum;
	
	/**
	 * 属性描述:更新时间
	 */
	@TableField(value="UPDATE_TIME",exist=true)
	java.util.Date updateTime;
	
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
	
	
	public java.lang.String getRemindTitle(){
		return this.remindTitle;
	}
	
	public void setRemindTitle(java.lang.String remindTitle){
		this.remindTitle=remindTitle;
	}
	
	
	public java.util.Date getStartTime(){
		return this.startTime;
	}
	
	public void setStartTime(java.util.Date startTime){
		this.startTime=startTime;
	}
	
	
	public java.util.Date getEndTime(){
		return this.endTime;
	}
	
	public void setEndTime(java.util.Date endTime){
		this.endTime=endTime;
	}
	
	
	public java.lang.Integer getRemindCarNum(){
		return this.remindCarNum;
	}
	
	public void setRemindCarNum(java.lang.Integer remindCarNum){
		this.remindCarNum=remindCarNum;
	}
	
	
	public java.lang.Integer getSendMsgNum(){
		return this.sendMsgNum;
	}
	
	public void setSendMsgNum(java.lang.Integer sendMsgNum){
		this.sendMsgNum=sendMsgNum;
	}
	
	
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime=updateTime;
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
			sb.append(", remindTitle=").append(remindTitle);			
			sb.append(", startTime=").append(startTime);			
			sb.append(", endTime=").append(endTime);			
			sb.append(", remindCarNum=").append(remindCarNum);			
			sb.append(", sendMsgNum=").append(sendMsgNum);			
			sb.append(", updateTime=").append(updateTime);			
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
        ErpUnlocationRemind other = (ErpUnlocationRemind) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getRemindTitle() == null ? other.getId() == null : this.getRemindTitle().equals(other.getRemindTitle()))		
				        		&&(this.getStartTime() == null ? other.getId() == null : this.getStartTime().equals(other.getStartTime()))		
				        		&&(this.getEndTime() == null ? other.getId() == null : this.getEndTime().equals(other.getEndTime()))		
				        		&&(this.getRemindCarNum() == null ? other.getId() == null : this.getRemindCarNum().equals(other.getRemindCarNum()))		
				        		&&(this.getSendMsgNum() == null ? other.getId() == null : this.getSendMsgNum().equals(other.getSendMsgNum()))		
				        		&&(this.getUpdateTime() == null ? other.getId() == null : this.getUpdateTime().equals(other.getUpdateTime()))		
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
		        result = prime * result + ((getRemindTitle() == null) ? 0 : getRemindTitle().hashCode());		
		        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());		
		        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());		
		        result = prime * result + ((getRemindCarNum() == null) ? 0 : getRemindCarNum().hashCode());		
		        result = prime * result + ((getSendMsgNum() == null) ? 0 : getSendMsgNum().hashCode());		
		        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

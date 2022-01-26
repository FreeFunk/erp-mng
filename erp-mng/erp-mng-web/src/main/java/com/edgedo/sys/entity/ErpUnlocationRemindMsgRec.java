package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_unlocation_remind_msg_rec")
public class ErpUnlocationRemindMsgRec implements Serializable{
	
		
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
	 * 属性描述:所属未定位提醒ID
	 */
	@TableField(value="OWNER_UNLOCATION_REMIND_ID",exist=true)
	java.lang.String ownerUnlocationRemindId;
	
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
	 * 属性描述:发送类型
	 */
	@TableField(value="SEND_TYPE",exist=true)
	java.lang.String sendType;
	
	/**
	 * 属性描述:短信内容
	 */
	@TableField(value="MSG_TEXT",exist=true)
	java.lang.String msgText;
	
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
	 * 属性描述:发送时间
	 */
	@TableField(value="SEND_TIME",exist=true)
	java.util.Date sendTime;
	
	/**
	 * 属性描述:发送状态
	 */
	@TableField(value="SEND_STATE",exist=true)
	java.lang.String sendState;
	/**
	 * 属性描述:短信落地状态ID
	 */
	@TableField(value="SMS_ID",exist=true)
	java.lang.String smsId;
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
	
	
	public java.lang.String getOwnerUnlocationRemindId(){
		return this.ownerUnlocationRemindId;
	}
	
	public void setOwnerUnlocationRemindId(java.lang.String ownerUnlocationRemindId){
		this.ownerUnlocationRemindId=ownerUnlocationRemindId;
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
	
	
	public java.lang.String getSendType(){
		return this.sendType;
	}
	
	public void setSendType(java.lang.String sendType){
		this.sendType=sendType;
	}
	
	
	public java.lang.String getMsgText(){
		return this.msgText;
	}
	
	public void setMsgText(java.lang.String msgText){
		this.msgText=msgText;
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
	
	
	public java.util.Date getSendTime(){
		return this.sendTime;
	}
	
	public void setSendTime(java.util.Date sendTime){
		this.sendTime=sendTime;
	}
	
	
	public java.lang.String getSendState(){
		return this.sendState;
	}
	
	public void setSendState(java.lang.String sendState){
		this.sendState=sendState;
	}
	
	
	public java.lang.String getDataState(){
		return this.dataState;
	}
	
	public void setDataState(java.lang.String dataState){
		this.dataState=dataState;
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
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
			sb.append(", ownerUnlocationRemindId=").append(ownerUnlocationRemindId);
			sb.append(", carPlateNum=").append(carPlateNum);
			sb.append(", carFrameNum=").append(carFrameNum);
			sb.append(", sendType=").append(sendType);
			sb.append(", msgText=").append(msgText);
			sb.append(", contactPerson=").append(contactPerson);
			sb.append(", contactPhoneNum=").append(contactPhoneNum);
			sb.append(", sendTime=").append(sendTime);
			sb.append(", sendState=").append(sendState);
			sb.append(",smsId=").append(smsId);
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
        ErpUnlocationRemindMsgRec other = (ErpUnlocationRemindMsgRec) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getOwnerUnlocationRemindId() == null ? other.getId() == null : this.getOwnerUnlocationRemindId().equals(other.getOwnerUnlocationRemindId()))		
				        		&&(this.getCarPlateNum() == null ? other.getId() == null : this.getCarPlateNum().equals(other.getCarPlateNum()))		
				        		&&(this.getCarFrameNum() == null ? other.getId() == null : this.getCarFrameNum().equals(other.getCarFrameNum()))		
				        		&&(this.getSendType() == null ? other.getId() == null : this.getSendType().equals(other.getSendType()))		
				        		&&(this.getMsgText() == null ? other.getId() == null : this.getMsgText().equals(other.getMsgText()))		
				        		&&(this.getContactPerson() == null ? other.getId() == null : this.getContactPerson().equals(other.getContactPerson()))		
				        		&&(this.getContactPhoneNum() == null ? other.getId() == null : this.getContactPhoneNum().equals(other.getContactPhoneNum()))		
				        		&&(this.getSendTime() == null ? other.getId() == null : this.getSendTime().equals(other.getSendTime()))		
				        		&&(this.getSendState() == null ? other.getId() == null : this.getSendState().equals(other.getSendState()))
								&&(this.getSmsId() == null ? other.getId() == null : this.getSmsId().equals(other.getSmsId()))
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
		        result = prime * result + ((getOwnerUnlocationRemindId() == null) ? 0 : getOwnerUnlocationRemindId().hashCode());		
		        result = prime * result + ((getCarPlateNum() == null) ? 0 : getCarPlateNum().hashCode());		
		        result = prime * result + ((getCarFrameNum() == null) ? 0 : getCarFrameNum().hashCode());		
		        result = prime * result + ((getSendType() == null) ? 0 : getSendType().hashCode());		
		        result = prime * result + ((getMsgText() == null) ? 0 : getMsgText().hashCode());		
		        result = prime * result + ((getContactPerson() == null) ? 0 : getContactPerson().hashCode());		
		        result = prime * result + ((getContactPhoneNum() == null) ? 0 : getContactPhoneNum().hashCode());		
		        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());		
		        result = prime * result + ((getSendState() == null) ? 0 : getSendState().hashCode());
				result = prime * result + ((getSmsId() == null) ? 0 : getSmsId().hashCode());
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

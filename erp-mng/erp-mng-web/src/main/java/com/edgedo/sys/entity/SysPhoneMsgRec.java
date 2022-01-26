package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("sys_phone_msg_rec")
public class SysPhoneMsgRec implements Serializable{
	
		
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
	 * 属性描述:手机号
	 */
	@TableField(value="PHONE_NUM",exist=true)
	java.lang.String phoneNum;
	
	/**
	 * 属性描述:用户id
	 */
	@TableField(value="USER_ID",exist=true)
	java.lang.String userId;
	
	/**
	 * 属性描述:验证码
	 */
	@TableField(value="CODE",exist=true)
	java.lang.String code;
	
	/**
	 * 属性描述:回执码
	 */
	@TableField(value="RESPONSE_CODE",exist=true)
	java.lang.String responseCode;
	
	/**
	 * 属性描述:短信id
	 */
	@TableField(value="SMS_ID",exist=true)
	java.lang.String smsId;
	
	/**
	 * 属性描述:短信内容
	 */
	@TableField(value="CONTENT",exist=true)
	java.lang.String content;
	
	/**
	 * 属性描述:是否成功
	 */
	@TableField(value="IS_SUCCESS",exist=true)
	java.lang.String isSuccess;
	
	/**
	 * 属性描述:模板id
	 */
	@TableField(value="MODEL_ID",exist=true)
	java.lang.String modelId;
	
	
	
	
	
	
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
	
	
	public java.lang.String getPhoneNum(){
		return this.phoneNum;
	}
	
	public void setPhoneNum(java.lang.String phoneNum){
		this.phoneNum=phoneNum;
	}
	
	
	public java.lang.String getUserId(){
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId){
		this.userId=userId;
	}
	
	
	public java.lang.String getCode(){
		return this.code;
	}
	
	public void setCode(java.lang.String code){
		this.code=code;
	}
	
	
	public java.lang.String getResponseCode(){
		return this.responseCode;
	}
	
	public void setResponseCode(java.lang.String responseCode){
		this.responseCode=responseCode;
	}
	
	
	public java.lang.String getSmsId(){
		return this.smsId;
	}
	
	public void setSmsId(java.lang.String smsId){
		this.smsId=smsId;
	}
	
	
	public java.lang.String getContent(){
		return this.content;
	}
	
	public void setContent(java.lang.String content){
		this.content=content;
	}
	
	
	public java.lang.String getIsSuccess(){
		return this.isSuccess;
	}
	
	public void setIsSuccess(java.lang.String isSuccess){
		this.isSuccess=isSuccess;
	}
	
	
	public java.lang.String getModelId(){
		return this.modelId;
	}
	
	public void setModelId(java.lang.String modelId){
		this.modelId=modelId;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", createTime=").append(createTime);			
			sb.append(", phoneNum=").append(phoneNum);			
			sb.append(", userId=").append(userId);			
			sb.append(", code=").append(code);			
			sb.append(", responseCode=").append(responseCode);			
			sb.append(", smsId=").append(smsId);			
			sb.append(", content=").append(content);			
			sb.append(", isSuccess=").append(isSuccess);			
			sb.append(", modelId=").append(modelId);			
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
        SysPhoneMsgRec other = (SysPhoneMsgRec) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getPhoneNum() == null ? other.getId() == null : this.getPhoneNum().equals(other.getPhoneNum()))		
				        		&&(this.getUserId() == null ? other.getId() == null : this.getUserId().equals(other.getUserId()))		
				        		&&(this.getCode() == null ? other.getId() == null : this.getCode().equals(other.getCode()))		
				        		&&(this.getResponseCode() == null ? other.getId() == null : this.getResponseCode().equals(other.getResponseCode()))		
				        		&&(this.getSmsId() == null ? other.getId() == null : this.getSmsId().equals(other.getSmsId()))		
				        		&&(this.getContent() == null ? other.getId() == null : this.getContent().equals(other.getContent()))		
				        		&&(this.getIsSuccess() == null ? other.getId() == null : this.getIsSuccess().equals(other.getIsSuccess()))		
				        		&&(this.getModelId() == null ? other.getId() == null : this.getModelId().equals(other.getModelId()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getPhoneNum() == null) ? 0 : getPhoneNum().hashCode());		
		        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());		
		        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());		
		        result = prime * result + ((getResponseCode() == null) ? 0 : getResponseCode().hashCode());		
		        result = prime * result + ((getSmsId() == null) ? 0 : getSmsId().hashCode());		
		        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());		
		        result = prime * result + ((getIsSuccess() == null) ? 0 : getIsSuccess().hashCode());		
		        result = prime * result + ((getModelId() == null) ? 0 : getModelId().hashCode());		
		;
        return result;
    }

}

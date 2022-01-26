package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_into_form")
public class ErpIntoForm implements Serializable{
	
		
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
	 * 属性描述:提交时间
	 */
	@TableField(value="SUBMIT_TIME",exist=true)
	java.util.Date submitTime;
	
	/**
	 * 属性描述:原车牌号
	 */
	@TableField(value="ORG_CAR_PLATE_NUM",exist=true)
	java.lang.String orgCarPlateNum;
	
	/**
	 * 属性描述:现车牌号
	 */
	@TableField(value="NEW_CAR_PLATE_NUM",exist=true)
	java.lang.String newCarPlateNum;
	
	/**
	 * 属性描述:客户名称
	 */
	@TableField(value="KEHU_NAME",exist=true)
	java.lang.String kehuName;
	
	/**
	 * 属性描述:原平台
	 */
	@TableField(value="ORG_PLATFORM",exist=true)
	java.lang.String orgPlatform;
	
	/**
	 * 属性描述:现平台
	 */
	@TableField(value="NEW_PLATFORM",exist=true)
	java.lang.String newPlatform;
	
	/**
	 * 属性描述:渠道
	 */
	@TableField(value="CHANNEL",exist=true)
	java.lang.String channel;
	
	/**
	 * 属性描述:区县
	 */
	@TableField(value="XIANQU_NAME",exist=true)
	java.lang.String xianquName;
	
	/**
	 * 属性描述:换卡
	 */
	@TableField(value="CHANGE_CARD",exist=true)
	java.lang.String changeCard;
	
	/**
	 * 属性描述:转成功时间
	 */
	@TableField(value="CHANGE_SUCCESS_TIME",exist=true)
	java.util.Date changeSuccessTime;
	
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
	
	
	public java.util.Date getSubmitTime(){
		return this.submitTime;
	}
	
	public void setSubmitTime(java.util.Date submitTime){
		this.submitTime=submitTime;
	}
	
	
	public java.lang.String getOrgCarPlateNum(){
		return this.orgCarPlateNum;
	}
	
	public void setOrgCarPlateNum(java.lang.String orgCarPlateNum){
		this.orgCarPlateNum=orgCarPlateNum;
	}
	
	
	public java.lang.String getNewCarPlateNum(){
		return this.newCarPlateNum;
	}
	
	public void setNewCarPlateNum(java.lang.String newCarPlateNum){
		this.newCarPlateNum=newCarPlateNum;
	}
	
	
	public java.lang.String getKehuName(){
		return this.kehuName;
	}
	
	public void setKehuName(java.lang.String kehuName){
		this.kehuName=kehuName;
	}
	
	
	public java.lang.String getOrgPlatform(){
		return this.orgPlatform;
	}
	
	public void setOrgPlatform(java.lang.String orgPlatform){
		this.orgPlatform=orgPlatform;
	}
	
	
	public java.lang.String getNewPlatform(){
		return this.newPlatform;
	}
	
	public void setNewPlatform(java.lang.String newPlatform){
		this.newPlatform=newPlatform;
	}
	
	
	public java.lang.String getChannel(){
		return this.channel;
	}
	
	public void setChannel(java.lang.String channel){
		this.channel=channel;
	}
	
	
	public java.lang.String getXianquName(){
		return this.xianquName;
	}
	
	public void setXianquName(java.lang.String xianquName){
		this.xianquName=xianquName;
	}
	
	
	public java.lang.String getChangeCard(){
		return this.changeCard;
	}
	
	public void setChangeCard(java.lang.String changeCard){
		this.changeCard=changeCard;
	}
	
	
	public java.util.Date getChangeSuccessTime(){
		return this.changeSuccessTime;
	}
	
	public void setChangeSuccessTime(java.util.Date changeSuccessTime){
		this.changeSuccessTime=changeSuccessTime;
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
			sb.append(", submitTime=").append(submitTime);			
			sb.append(", orgCarPlateNum=").append(orgCarPlateNum);			
			sb.append(", newCarPlateNum=").append(newCarPlateNum);			
			sb.append(", kehuName=").append(kehuName);			
			sb.append(", orgPlatform=").append(orgPlatform);			
			sb.append(", newPlatform=").append(newPlatform);			
			sb.append(", channel=").append(channel);			
			sb.append(", xianquName=").append(xianquName);			
			sb.append(", changeCard=").append(changeCard);			
			sb.append(", changeSuccessTime=").append(changeSuccessTime);			
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
        ErpIntoForm other = (ErpIntoForm) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getSubmitTime() == null ? other.getId() == null : this.getSubmitTime().equals(other.getSubmitTime()))		
				        		&&(this.getOrgCarPlateNum() == null ? other.getId() == null : this.getOrgCarPlateNum().equals(other.getOrgCarPlateNum()))		
				        		&&(this.getNewCarPlateNum() == null ? other.getId() == null : this.getNewCarPlateNum().equals(other.getNewCarPlateNum()))		
				        		&&(this.getKehuName() == null ? other.getId() == null : this.getKehuName().equals(other.getKehuName()))		
				        		&&(this.getOrgPlatform() == null ? other.getId() == null : this.getOrgPlatform().equals(other.getOrgPlatform()))		
				        		&&(this.getNewPlatform() == null ? other.getId() == null : this.getNewPlatform().equals(other.getNewPlatform()))		
				        		&&(this.getChannel() == null ? other.getId() == null : this.getChannel().equals(other.getChannel()))		
				        		&&(this.getXianquName() == null ? other.getId() == null : this.getXianquName().equals(other.getXianquName()))		
				        		&&(this.getChangeCard() == null ? other.getId() == null : this.getChangeCard().equals(other.getChangeCard()))		
				        		&&(this.getChangeSuccessTime() == null ? other.getId() == null : this.getChangeSuccessTime().equals(other.getChangeSuccessTime()))		
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
		        result = prime * result + ((getSubmitTime() == null) ? 0 : getSubmitTime().hashCode());		
		        result = prime * result + ((getOrgCarPlateNum() == null) ? 0 : getOrgCarPlateNum().hashCode());		
		        result = prime * result + ((getNewCarPlateNum() == null) ? 0 : getNewCarPlateNum().hashCode());		
		        result = prime * result + ((getKehuName() == null) ? 0 : getKehuName().hashCode());		
		        result = prime * result + ((getOrgPlatform() == null) ? 0 : getOrgPlatform().hashCode());		
		        result = prime * result + ((getNewPlatform() == null) ? 0 : getNewPlatform().hashCode());		
		        result = prime * result + ((getChannel() == null) ? 0 : getChannel().hashCode());		
		        result = prime * result + ((getXianquName() == null) ? 0 : getXianquName().hashCode());		
		        result = prime * result + ((getChangeCard() == null) ? 0 : getChangeCard().hashCode());		
		        result = prime * result + ((getChangeSuccessTime() == null) ? 0 : getChangeSuccessTime().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_notnoline_car_info")
public class ErpNotnolineCarInfo implements Serializable{
	
		
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
	 * 属性描述:车牌号
	 */
	@TableField(value="CAR_PLATE_NUM",exist=true)
	java.lang.String carPlateNum;
	
	/**
	 * 属性描述:业户/车主
	 */
	@TableField(value="YEHU_NAME",exist=true)
	java.lang.String yehuName;
	
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
	 * 属性描述:终端厂商
	 */
	@TableField(value="TERMINAL_CHANGSHANG",exist=true)
	java.lang.String terminalChangshang;
	
	/**
	 * 属性描述:终端型号
	 */
	@TableField(value="TERMINAL_MODEL",exist=true)
	java.lang.String terminalModel;

	/**
	 * 未定位天数
	 */
	@TableField(value="UNLOCATION_DAY",exist=true)
	java.lang.String unlocatonDay;

	/**
	 * 属性描述:短信提醒次数
	 */
	@TableField(value="SEND_MSG_NUM",exist=true)
	java.lang.Integer sendMsgNum;

	/**
	 * 属性描述:最后定位时间
	 */
	@TableField(value="LAST_LOCALTION_TIME",exist=true)
	java.util.Date lastLocaltionTime;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;

	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="sysutc",exist=true)
	java.lang.String sysutc;

	public String getSysutc() {
		return sysutc;
	}

	public void setSysutc(String sysutc) {
		this.sysutc = sysutc;
	}

	public java.lang.Integer getSendMsgNum(){
		return this.sendMsgNum;
	}

	public void setSendMsgNum(java.lang.Integer sendMsgNum){
		this.sendMsgNum=sendMsgNum;
	}

	public String getUnlocatonDay() {
		return unlocatonDay;
	}

	public void setUnlocatonDay(String unlocatonDay) {
		this.unlocatonDay = unlocatonDay;
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
	
	
	public java.lang.String getCarPlateNum(){
		return this.carPlateNum;
	}
	
	public void setCarPlateNum(java.lang.String carPlateNum){
		this.carPlateNum=carPlateNum;
	}
	
	
	public java.lang.String getYehuName(){
		return this.yehuName;
	}
	
	public void setYehuName(java.lang.String yehuName){
		this.yehuName=yehuName;
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
	
	
	public java.lang.String getTerminalChangshang(){
		return this.terminalChangshang;
	}
	
	public void setTerminalChangshang(java.lang.String terminalChangshang){
		this.terminalChangshang=terminalChangshang;
	}
	
	
	public java.lang.String getTerminalModel(){
		return this.terminalModel;
	}
	
	public void setTerminalModel(java.lang.String terminalModel){
		this.terminalModel=terminalModel;
	}
	
	
	public java.util.Date getLastLocaltionTime(){
		return this.lastLocaltionTime;
	}
	
	public void setLastLocaltionTime(java.util.Date lastLocaltionTime){
		this.lastLocaltionTime=lastLocaltionTime;
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
			sb.append(", carPlateNum=").append(carPlateNum);			
			sb.append(", yehuName=").append(yehuName);			
			sb.append(", contactPerson=").append(contactPerson);			
			sb.append(", contactPhoneNum=").append(contactPhoneNum);			
			sb.append(", terminalChangshang=").append(terminalChangshang);			
			sb.append(", terminalModel=").append(terminalModel);			
			sb.append(", lastLocaltionTime=").append(lastLocaltionTime);			
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
        ErpNotnolineCarInfo other = (ErpNotnolineCarInfo) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCarPlateNum() == null ? other.getId() == null : this.getCarPlateNum().equals(other.getCarPlateNum()))		
				        		&&(this.getYehuName() == null ? other.getId() == null : this.getYehuName().equals(other.getYehuName()))		
				        		&&(this.getContactPerson() == null ? other.getId() == null : this.getContactPerson().equals(other.getContactPerson()))		
				        		&&(this.getContactPhoneNum() == null ? other.getId() == null : this.getContactPhoneNum().equals(other.getContactPhoneNum()))		
				        		&&(this.getTerminalChangshang() == null ? other.getId() == null : this.getTerminalChangshang().equals(other.getTerminalChangshang()))		
				        		&&(this.getTerminalModel() == null ? other.getId() == null : this.getTerminalModel().equals(other.getTerminalModel()))		
				        		&&(this.getLastLocaltionTime() == null ? other.getId() == null : this.getLastLocaltionTime().equals(other.getLastLocaltionTime()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getCarPlateNum() == null) ? 0 : getCarPlateNum().hashCode());		
		        result = prime * result + ((getYehuName() == null) ? 0 : getYehuName().hashCode());		
		        result = prime * result + ((getContactPerson() == null) ? 0 : getContactPerson().hashCode());		
		        result = prime * result + ((getContactPhoneNum() == null) ? 0 : getContactPhoneNum().hashCode());		
		        result = prime * result + ((getTerminalChangshang() == null) ? 0 : getTerminalChangshang().hashCode());		
		        result = prime * result + ((getTerminalModel() == null) ? 0 : getTerminalModel().hashCode());		
		        result = prime * result + ((getLastLocaltionTime() == null) ? 0 : getLastLocaltionTime().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

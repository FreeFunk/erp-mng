package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_user_work_log")
public class ErpUserWorkLog implements Serializable{
	
		
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
	 * 属性描述:所属员工ID
	 */
	@TableField(value="USER_ID",exist=true)
	java.lang.String userId;
	
	/**
	 * 属性描述:员工姓名
	 */
	@TableField(value="USER_NAME",exist=true)
	java.lang.String userName;
	
	/**
	 * 属性描述:日期
	 */
	@TableField(value="WORK_DATE",exist=true)
	java.util.Date workDate;
	
	/**
	 * 属性描述:星期
	 */
	@TableField(value="WEEK",exist=true)
	java.lang.String week;
	
	/**
	 * 属性描述:服务内容
	 */
	@TableField(value="WORK_TEXT",exist=true)
	java.lang.String workText;
	
	/**
	 * 属性描述:是否加班
	 */
	@TableField(value="IS_OVER_WORK",exist=true)
	java.lang.String isOverWork;
	
	/**
	 * 属性描述:加班时长(分钟)
	 */
	@TableField(value="OVER_WORK_TIME",exist=true)
	java.lang.Integer overWorkTime;
	
	/**
	 * 属性描述:加班描述
	 */
	@TableField(value="OVER_WORK_DESC",exist=true)
	java.lang.String overWorkDesc;
	
	/**
	 * 属性描述:外勤补助
	 */
	@TableField(value="OUT_GET_MONEY",exist=true)
	java.math.BigDecimal outGetMoney;
	
	/**
	 * 属性描述:审核状态
	 */
	@TableField(value="SHENHE_STATE",exist=true)
	java.lang.String shenheState;
	
	/**
	 * 属性描述:审核人ID
	 */
	@TableField(value="SHENHE_USER_ID",exist=true)
	java.lang.String shenheUserId;
	
	/**
	 * 属性描述:审核人名
	 */
	@TableField(value="SHENHE_USER_NAME",exist=true)
	java.lang.String shenheUserName;
	
	/**
	 * 属性描述:审核时间
	 */
	@TableField(value="SHENHE_TIME",exist=true)
	java.util.Date shenheTime;
	
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
	
	
	public java.lang.String getUserId(){
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId){
		this.userId=userId;
	}
	
	
	public java.lang.String getUserName(){
		return this.userName;
	}
	
	public void setUserName(java.lang.String userName){
		this.userName=userName;
	}
	
	
	public java.util.Date getWorkDate(){
		return this.workDate;
	}
	
	public void setWorkDate(java.util.Date workDate){
		this.workDate=workDate;
	}
	
	
	public java.lang.String getWeek(){
		return this.week;
	}
	
	public void setWeek(java.lang.String week){
		this.week=week;
	}
	
	
	public java.lang.String getWorkText(){
		return this.workText;
	}
	
	public void setWorkText(java.lang.String workText){
		this.workText=workText;
	}
	
	
	public java.lang.String getIsOverWork(){
		return this.isOverWork;
	}
	
	public void setIsOverWork(java.lang.String isOverWork){
		this.isOverWork=isOverWork;
	}
	
	
	public java.lang.Integer getOverWorkTime(){
		return this.overWorkTime;
	}
	
	public void setOverWorkTime(java.lang.Integer overWorkTime){
		this.overWorkTime=overWorkTime;
	}
	
	
	public java.lang.String getOverWorkDesc(){
		return this.overWorkDesc;
	}
	
	public void setOverWorkDesc(java.lang.String overWorkDesc){
		this.overWorkDesc=overWorkDesc;
	}
	
	
	public java.math.BigDecimal getOutGetMoney(){
		return this.outGetMoney;
	}
	
	public void setOutGetMoney(java.math.BigDecimal outGetMoney){
		this.outGetMoney=outGetMoney;
	}
	
	
	public java.lang.String getShenheState(){
		return this.shenheState;
	}
	
	public void setShenheState(java.lang.String shenheState){
		this.shenheState=shenheState;
	}
	
	
	public java.lang.String getShenheUserId(){
		return this.shenheUserId;
	}
	
	public void setShenheUserId(java.lang.String shenheUserId){
		this.shenheUserId=shenheUserId;
	}
	
	
	public java.lang.String getShenheUserName(){
		return this.shenheUserName;
	}
	
	public void setShenheUserName(java.lang.String shenheUserName){
		this.shenheUserName=shenheUserName;
	}
	
	
	public java.util.Date getShenheTime(){
		return this.shenheTime;
	}
	
	public void setShenheTime(java.util.Date shenheTime){
		this.shenheTime=shenheTime;
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
			sb.append(", userId=").append(userId);			
			sb.append(", userName=").append(userName);			
			sb.append(", workDate=").append(workDate);			
			sb.append(", week=").append(week);			
			sb.append(", workText=").append(workText);			
			sb.append(", isOverWork=").append(isOverWork);			
			sb.append(", overWorkTime=").append(overWorkTime);			
			sb.append(", overWorkDesc=").append(overWorkDesc);			
			sb.append(", outGetMoney=").append(outGetMoney);			
			sb.append(", shenheState=").append(shenheState);			
			sb.append(", shenheUserId=").append(shenheUserId);			
			sb.append(", shenheUserName=").append(shenheUserName);			
			sb.append(", shenheTime=").append(shenheTime);			
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
        ErpUserWorkLog other = (ErpUserWorkLog) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getUserId() == null ? other.getId() == null : this.getUserId().equals(other.getUserId()))		
				        		&&(this.getUserName() == null ? other.getId() == null : this.getUserName().equals(other.getUserName()))		
				        		&&(this.getWorkDate() == null ? other.getId() == null : this.getWorkDate().equals(other.getWorkDate()))		
				        		&&(this.getWeek() == null ? other.getId() == null : this.getWeek().equals(other.getWeek()))		
				        		&&(this.getWorkText() == null ? other.getId() == null : this.getWorkText().equals(other.getWorkText()))		
				        		&&(this.getIsOverWork() == null ? other.getId() == null : this.getIsOverWork().equals(other.getIsOverWork()))		
				        		&&(this.getOverWorkTime() == null ? other.getId() == null : this.getOverWorkTime().equals(other.getOverWorkTime()))		
				        		&&(this.getOverWorkDesc() == null ? other.getId() == null : this.getOverWorkDesc().equals(other.getOverWorkDesc()))		
				        		&&(this.getOutGetMoney() == null ? other.getId() == null : this.getOutGetMoney().equals(other.getOutGetMoney()))		
				        		&&(this.getShenheState() == null ? other.getId() == null : this.getShenheState().equals(other.getShenheState()))		
				        		&&(this.getShenheUserId() == null ? other.getId() == null : this.getShenheUserId().equals(other.getShenheUserId()))		
				        		&&(this.getShenheUserName() == null ? other.getId() == null : this.getShenheUserName().equals(other.getShenheUserName()))		
				        		&&(this.getShenheTime() == null ? other.getId() == null : this.getShenheTime().equals(other.getShenheTime()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());		
		        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());		
		        result = prime * result + ((getWorkDate() == null) ? 0 : getWorkDate().hashCode());		
		        result = prime * result + ((getWeek() == null) ? 0 : getWeek().hashCode());		
		        result = prime * result + ((getWorkText() == null) ? 0 : getWorkText().hashCode());		
		        result = prime * result + ((getIsOverWork() == null) ? 0 : getIsOverWork().hashCode());		
		        result = prime * result + ((getOverWorkTime() == null) ? 0 : getOverWorkTime().hashCode());		
		        result = prime * result + ((getOverWorkDesc() == null) ? 0 : getOverWorkDesc().hashCode());		
		        result = prime * result + ((getOutGetMoney() == null) ? 0 : getOutGetMoney().hashCode());		
		        result = prime * result + ((getShenheState() == null) ? 0 : getShenheState().hashCode());		
		        result = prime * result + ((getShenheUserId() == null) ? 0 : getShenheUserId().hashCode());		
		        result = prime * result + ((getShenheUserName() == null) ? 0 : getShenheUserName().hashCode());		
		        result = prime * result + ((getShenheTime() == null) ? 0 : getShenheTime().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

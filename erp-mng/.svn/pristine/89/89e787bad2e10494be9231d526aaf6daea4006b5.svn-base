package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_outputuser_task_work")
public class ErpOutputuserTaskWork implements Serializable{
	
		
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
	 * 属性描述:外勤人员ID
	 */
	@TableField(value="OUTPUT_USER_ID",exist=true)
	java.lang.String outputUserId;
	
	/**
	 * 属性描述:工单ID
	 */
	@TableField(value="OWNER_WORK_TASK_ID",exist=true)
	java.lang.String ownerWorkTaskId;
	
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
	
	
	public java.lang.String getOutputUserId(){
		return this.outputUserId;
	}
	
	public void setOutputUserId(java.lang.String outputUserId){
		this.outputUserId=outputUserId;
	}
	
	
	public java.lang.String getOwnerWorkTaskId(){
		return this.ownerWorkTaskId;
	}
	
	public void setOwnerWorkTaskId(java.lang.String ownerWorkTaskId){
		this.ownerWorkTaskId=ownerWorkTaskId;
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
			sb.append(", outputUserId=").append(outputUserId);			
			sb.append(", ownerWorkTaskId=").append(ownerWorkTaskId);			
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
        ErpOutputuserTaskWork other = (ErpOutputuserTaskWork) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getOutputUserId() == null ? other.getId() == null : this.getOutputUserId().equals(other.getOutputUserId()))		
				        		&&(this.getOwnerWorkTaskId() == null ? other.getId() == null : this.getOwnerWorkTaskId().equals(other.getOwnerWorkTaskId()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getOutputUserId() == null) ? 0 : getOutputUserId().hashCode());		
		        result = prime * result + ((getOwnerWorkTaskId() == null) ? 0 : getOwnerWorkTaskId().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

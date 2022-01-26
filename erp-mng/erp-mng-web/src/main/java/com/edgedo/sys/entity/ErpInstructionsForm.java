package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_instructions_form")
public class ErpInstructionsForm implements Serializable{
	
		
	/**
	 * 属性描述:主键
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:创建世间
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
	 * 属性描述:设备品牌
	 */
	@TableField(value="TERMINAL_BRAND",exist=true)
	java.lang.String terminalBrand;
	
	/**
	 * 属性描述:指令名称
	 */
	@TableField(value="INSTRUCTIONS_NAME",exist=true)
	java.lang.String instructionsName;
	
	/**
	 * 属性描述:指令内容
	 */
	@TableField(value="INSTRUCTIONS_CONTENT",exist=true)
	java.lang.String instructionsContent;
	
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
	
	
	public java.lang.String getTerminalBrand(){
		return this.terminalBrand;
	}
	
	public void setTerminalBrand(java.lang.String terminalBrand){
		this.terminalBrand=terminalBrand;
	}
	
	
	public java.lang.String getInstructionsName(){
		return this.instructionsName;
	}
	
	public void setInstructionsName(java.lang.String instructionsName){
		this.instructionsName=instructionsName;
	}
	
	
	public java.lang.String getInstructionsContent(){
		return this.instructionsContent;
	}
	
	public void setInstructionsContent(java.lang.String instructionsContent){
		this.instructionsContent=instructionsContent;
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
			sb.append(", terminalBrand=").append(terminalBrand);			
			sb.append(", instructionsName=").append(instructionsName);			
			sb.append(", instructionsContent=").append(instructionsContent);			
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
        ErpInstructionsForm other = (ErpInstructionsForm) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getTerminalBrand() == null ? other.getId() == null : this.getTerminalBrand().equals(other.getTerminalBrand()))		
				        		&&(this.getInstructionsName() == null ? other.getId() == null : this.getInstructionsName().equals(other.getInstructionsName()))		
				        		&&(this.getInstructionsContent() == null ? other.getId() == null : this.getInstructionsContent().equals(other.getInstructionsContent()))		
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
		        result = prime * result + ((getTerminalBrand() == null) ? 0 : getTerminalBrand().hashCode());		
		        result = prime * result + ((getInstructionsName() == null) ? 0 : getInstructionsName().hashCode());		
		        result = prime * result + ((getInstructionsContent() == null) ? 0 : getInstructionsContent().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

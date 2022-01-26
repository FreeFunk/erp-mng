package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_beidou_add_statistics")
public class ErpBeidouAddStatistics implements Serializable{
	
		
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
	 * 属性描述:县区ID
	 */
	@TableField(value="XIANQU_ID",exist=true)
	java.lang.String xianquId;
	
	/**
	 * 属性描述:县区名
	 */
	@TableField(value="XIANQU_NAME",exist=true)
	java.lang.String xianquName;
	
	/**
	 * 属性描述:平台名
	 */
	@TableField(value="PLATFORM_NAME",exist=true)
	java.lang.String platformName;
	
	/**
	 * 属性描述:统计日期
	 */
	@TableField(value="STATISTICS_TODAY",exist=true)
	java.lang.String statisticsToday;
	
	/**
	 * 属性描述:新增车辆数量
	 */
	@TableField(value="CAR_ADD_NUM",exist=true)
	java.lang.Integer carAddNum;
	
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
	
	
	public java.lang.String getXianquId(){
		return this.xianquId;
	}
	
	public void setXianquId(java.lang.String xianquId){
		this.xianquId=xianquId;
	}
	
	
	public java.lang.String getXianquName(){
		return this.xianquName;
	}
	
	public void setXianquName(java.lang.String xianquName){
		this.xianquName=xianquName;
	}
	
	
	public java.lang.String getPlatformName(){
		return this.platformName;
	}
	
	public void setPlatformName(java.lang.String platformName){
		this.platformName=platformName;
	}
	
	
	public java.lang.String getStatisticsToday(){
		return this.statisticsToday;
	}
	
	public void setStatisticsToday(java.lang.String statisticsToday){
		this.statisticsToday=statisticsToday;
	}
	
	
	public java.lang.Integer getCarAddNum(){
		return this.carAddNum;
	}
	
	public void setCarAddNum(java.lang.Integer carAddNum){
		this.carAddNum=carAddNum;
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
			sb.append(", xianquId=").append(xianquId);			
			sb.append(", xianquName=").append(xianquName);			
			sb.append(", platformName=").append(platformName);			
			sb.append(", statisticsToday=").append(statisticsToday);			
			sb.append(", carAddNum=").append(carAddNum);			
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
        ErpBeidouAddStatistics other = (ErpBeidouAddStatistics) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getXianquId() == null ? other.getId() == null : this.getXianquId().equals(other.getXianquId()))		
				        		&&(this.getXianquName() == null ? other.getId() == null : this.getXianquName().equals(other.getXianquName()))		
				        		&&(this.getPlatformName() == null ? other.getId() == null : this.getPlatformName().equals(other.getPlatformName()))		
				        		&&(this.getStatisticsToday() == null ? other.getId() == null : this.getStatisticsToday().equals(other.getStatisticsToday()))		
				        		&&(this.getCarAddNum() == null ? other.getId() == null : this.getCarAddNum().equals(other.getCarAddNum()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getXianquId() == null) ? 0 : getXianquId().hashCode());		
		        result = prime * result + ((getXianquName() == null) ? 0 : getXianquName().hashCode());		
		        result = prime * result + ((getPlatformName() == null) ? 0 : getPlatformName().hashCode());		
		        result = prime * result + ((getStatisticsToday() == null) ? 0 : getStatisticsToday().hashCode());		
		        result = prime * result + ((getCarAddNum() == null) ? 0 : getCarAddNum().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

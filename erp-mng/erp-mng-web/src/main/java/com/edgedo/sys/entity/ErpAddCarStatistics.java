package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_add_car_statistics")
public class ErpAddCarStatistics implements Serializable{
	
		
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
	 * 属性描述:供应商名
	 */
	@TableField(value="SUPPLIER_NAME",exist=true)
	java.lang.String supplierName;
	
	/**
	 * 属性描述:当天统计车辆数
	 */
	@TableField(value="CAR_TODAY_NUM",exist=true)
	java.lang.Integer carTodayNum;
	
	/**
	 * 属性描述:统计车辆日期
	 */
	@TableField(value="STATISTICS_CAR_TODAY",exist=true)
	java.lang.String statisticsCarToday;
	
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
	
	
	public java.lang.String getSupplierName(){
		return this.supplierName;
	}
	
	public void setSupplierName(java.lang.String supplierName){
		this.supplierName=supplierName;
	}
	
	
	public java.lang.Integer getCarTodayNum(){
		return this.carTodayNum;
	}
	
	public void setCarTodayNum(java.lang.Integer carTodayNum){
		this.carTodayNum=carTodayNum;
	}
	
	
	public java.lang.String getStatisticsCarToday(){
		return this.statisticsCarToday;
	}
	
	public void setStatisticsCarToday(java.lang.String statisticsCarToday){
		this.statisticsCarToday=statisticsCarToday;
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
			sb.append(", supplierName=").append(supplierName);			
			sb.append(", carTodayNum=").append(carTodayNum);			
			sb.append(", statisticsCarToday=").append(statisticsCarToday);			
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
        ErpAddCarStatistics other = (ErpAddCarStatistics) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getXianquId() == null ? other.getId() == null : this.getXianquId().equals(other.getXianquId()))		
				        		&&(this.getXianquName() == null ? other.getId() == null : this.getXianquName().equals(other.getXianquName()))		
				        		&&(this.getSupplierName() == null ? other.getId() == null : this.getSupplierName().equals(other.getSupplierName()))		
				        		&&(this.getCarTodayNum() == null ? other.getId() == null : this.getCarTodayNum().equals(other.getCarTodayNum()))		
				        		&&(this.getStatisticsCarToday() == null ? other.getId() == null : this.getStatisticsCarToday().equals(other.getStatisticsCarToday()))		
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
		        result = prime * result + ((getSupplierName() == null) ? 0 : getSupplierName().hashCode());		
		        result = prime * result + ((getCarTodayNum() == null) ? 0 : getCarTodayNum().hashCode());		
		        result = prime * result + ((getStatisticsCarToday() == null) ? 0 : getStatisticsCarToday().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_change_net_sum_month")
public class ErpChangeNetSumMonth implements Serializable{
	
		
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
	 * 属性描述:现平台
	 */
	@TableField(value="NOW_PT",exist=true)
	java.lang.String nowPt;

	/**
	 * 属性描述:转网类型
	 */
	@TableField(value="CHANGE_NET_TYPE",exist=true)
	java.lang.String changeNetType;
	
	/**
	 * 属性描述:海港区
	 */
	@TableField(value="HAI_GANG_QU",exist=true)
	java.lang.Integer haiGangQu;
	
	/**
	 * 属性描述:山海关区
	 */
	@TableField(value="SHAN_HAI_GUAN_QU",exist=true)
	java.lang.Integer shanHaiGuanQu;
	
	/**
	 * 属性描述:北戴河区
	 */
	@TableField(value="BEI_DAI_HE_QU",exist=true)
	java.lang.Integer beiDaiHeQu;
	
	/**
	 * 属性描述:抚宁区
	 */
	@TableField(value="FU_NING_QU",exist=true)
	java.lang.Integer fuNingQu;
	
	/**
	 * 属性描述:青龙县
	 */
	@TableField(value="QING_LONG_XIAN",exist=true)
	java.lang.Integer qingLongXian;
	
	/**
	 * 属性描述:昌黎县
	 */
	@TableField(value="CHANG_LI_XIAN",exist=true)
	java.lang.Integer changLiXian;
	
	/**
	 * 属性描述:卢龙县
	 */
	@TableField(value="LU_LONG_XIAN",exist=true)
	java.lang.Integer luLongXian;
	
	/**
	 * 属性描述:开发区
	 */
	@TableField(value="KAI_FA_QU",exist=true)
	java.lang.Integer kaiFaQu;
	
	/**
	 * 属性描述:统计月份
	 */
	@TableField(value="COUNT_MONTH",exist=true)
	java.lang.Integer countMonth;
	
	/**
	 * 属性描述:统计月份
	 */
	@TableField(value="COUNT_DATA",exist=true)
	java.lang.String countData;
	
	/**
	 * 属性描述:统计类型
	 */
	@TableField(value="COUNT_TYPE",exist=true)
	java.lang.String countType;
	
	/**
	 * 属性描述:sumCount
	 */
	@TableField(value="SUM_COUNT",exist=true)
	java.lang.Integer sumCount;
	
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
	
	
	public java.lang.String getNowPt(){
		return this.nowPt;
	}
	
	public void setNowPt(java.lang.String nowPt){
		this.nowPt=nowPt;
	}
	
	
	public java.lang.Integer getHaiGangQu(){
		return this.haiGangQu;
	}
	
	public void setHaiGangQu(java.lang.Integer haiGangQu){
		this.haiGangQu=haiGangQu;
	}
	
	
	public java.lang.Integer getShanHaiGuanQu(){
		return this.shanHaiGuanQu;
	}
	
	public void setShanHaiGuanQu(java.lang.Integer shanHaiGuanQu){
		this.shanHaiGuanQu=shanHaiGuanQu;
	}

	public String getChangeNetType() {
		return changeNetType;
	}

	public void setChangeNetType(String changeNetType) {
		this.changeNetType = changeNetType;
	}

	public java.lang.Integer getBeiDaiHeQu(){
		return this.beiDaiHeQu;
	}
	
	public void setBeiDaiHeQu(java.lang.Integer beiDaiHeQu){
		this.beiDaiHeQu=beiDaiHeQu;
	}
	
	
	public java.lang.Integer getFuNingQu(){
		return this.fuNingQu;
	}
	
	public void setFuNingQu(java.lang.Integer fuNingQu){
		this.fuNingQu=fuNingQu;
	}
	
	
	public java.lang.Integer getQingLongXian(){
		return this.qingLongXian;
	}
	
	public void setQingLongXian(java.lang.Integer qingLongXian){
		this.qingLongXian=qingLongXian;
	}
	
	
	public java.lang.Integer getChangLiXian(){
		return this.changLiXian;
	}
	
	public void setChangLiXian(java.lang.Integer changLiXian){
		this.changLiXian=changLiXian;
	}
	
	
	public java.lang.Integer getLuLongXian(){
		return this.luLongXian;
	}
	
	public void setLuLongXian(java.lang.Integer luLongXian){
		this.luLongXian=luLongXian;
	}
	
	
	public java.lang.Integer getKaiFaQu(){
		return this.kaiFaQu;
	}
	
	public void setKaiFaQu(java.lang.Integer kaiFaQu){
		this.kaiFaQu=kaiFaQu;
	}
	
	
	public java.lang.Integer getCountMonth(){
		return this.countMonth;
	}
	
	public void setCountMonth(java.lang.Integer countMonth){
		this.countMonth=countMonth;
	}
	
	
	public java.lang.String getCountData(){
		return this.countData;
	}
	
	public void setCountData(java.lang.String countData){
		this.countData=countData;
	}
	
	
	public java.lang.String getCountType(){
		return this.countType;
	}
	
	public void setCountType(java.lang.String countType){
		this.countType=countType;
	}
	
	
	public java.lang.Integer getSumCount(){
		return this.sumCount;
	}
	
	public void setSumCount(java.lang.Integer sumCount){
		this.sumCount=sumCount;
	}
	
	
	public java.lang.String getDataState(){
		return this.dataState;
	}
	
	public void setDataState(java.lang.String dataState){
		this.dataState=dataState;
	}


	@Override
	public String toString() {
		return "ErpChangeNetSumMonth{" +
				"id='" + id + '\'' +
				", createTime=" + createTime +
				", nowPt='" + nowPt + '\'' +
				", changeNetType='" + changeNetType + '\'' +
				", haiGangQu=" + haiGangQu +
				", shanHaiGuanQu=" + shanHaiGuanQu +
				", beiDaiHeQu=" + beiDaiHeQu +
				", fuNingQu=" + fuNingQu +
				", qingLongXian=" + qingLongXian +
				", changLiXian=" + changLiXian +
				", luLongXian=" + luLongXian +
				", kaiFaQu=" + kaiFaQu +
				", countMonth=" + countMonth +
				", countData='" + countData + '\'' +
				", countType='" + countType + '\'' +
				", sumCount=" + sumCount +
				", dataState='" + dataState + '\'' +
				'}';
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ErpChangeNetSumMonth that = (ErpChangeNetSumMonth) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
		if (nowPt != null ? !nowPt.equals(that.nowPt) : that.nowPt != null) return false;
		if (changeNetType != null ? !changeNetType.equals(that.changeNetType) : that.changeNetType != null)
			return false;
		if (haiGangQu != null ? !haiGangQu.equals(that.haiGangQu) : that.haiGangQu != null) return false;
		if (shanHaiGuanQu != null ? !shanHaiGuanQu.equals(that.shanHaiGuanQu) : that.shanHaiGuanQu != null)
			return false;
		if (beiDaiHeQu != null ? !beiDaiHeQu.equals(that.beiDaiHeQu) : that.beiDaiHeQu != null) return false;
		if (fuNingQu != null ? !fuNingQu.equals(that.fuNingQu) : that.fuNingQu != null) return false;
		if (qingLongXian != null ? !qingLongXian.equals(that.qingLongXian) : that.qingLongXian != null) return false;
		if (changLiXian != null ? !changLiXian.equals(that.changLiXian) : that.changLiXian != null) return false;
		if (luLongXian != null ? !luLongXian.equals(that.luLongXian) : that.luLongXian != null) return false;
		if (kaiFaQu != null ? !kaiFaQu.equals(that.kaiFaQu) : that.kaiFaQu != null) return false;
		if (countMonth != null ? !countMonth.equals(that.countMonth) : that.countMonth != null) return false;
		if (countData != null ? !countData.equals(that.countData) : that.countData != null) return false;
		if (countType != null ? !countType.equals(that.countType) : that.countType != null) return false;
		if (sumCount != null ? !sumCount.equals(that.sumCount) : that.sumCount != null) return false;
		return dataState != null ? dataState.equals(that.dataState) : that.dataState == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
		result = 31 * result + (nowPt != null ? nowPt.hashCode() : 0);
		result = 31 * result + (changeNetType != null ? changeNetType.hashCode() : 0);
		result = 31 * result + (haiGangQu != null ? haiGangQu.hashCode() : 0);
		result = 31 * result + (shanHaiGuanQu != null ? shanHaiGuanQu.hashCode() : 0);
		result = 31 * result + (beiDaiHeQu != null ? beiDaiHeQu.hashCode() : 0);
		result = 31 * result + (fuNingQu != null ? fuNingQu.hashCode() : 0);
		result = 31 * result + (qingLongXian != null ? qingLongXian.hashCode() : 0);
		result = 31 * result + (changLiXian != null ? changLiXian.hashCode() : 0);
		result = 31 * result + (luLongXian != null ? luLongXian.hashCode() : 0);
		result = 31 * result + (kaiFaQu != null ? kaiFaQu.hashCode() : 0);
		result = 31 * result + (countMonth != null ? countMonth.hashCode() : 0);
		result = 31 * result + (countData != null ? countData.hashCode() : 0);
		result = 31 * result + (countType != null ? countType.hashCode() : 0);
		result = 31 * result + (sumCount != null ? sumCount.hashCode() : 0);
		result = 31 * result + (dataState != null ? dataState.hashCode() : 0);
		return result;
	}
}

package com.edgedo.sys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("erp_supplier")
public class ErpSupplier implements Serializable{
	
		
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
	 * 属性描述:供应商名
	 */
	@TableField(value="SUPPLIER_NAME",exist=true)
	java.lang.String supplierName;
	
	/**
	 * 属性描述:供货负责人
	 */
	@TableField(value="GOODS_PERSON",exist=true)
	java.lang.String goodsPerson;
	
	/**
	 * 属性描述:供应商类型(SIM、GSP设备
	 */
	@TableField(value="SUPPLIER_TYPE",exist=true)
	java.lang.String supplierType;
	
	/**
	 * 属性描述:联系电话
	 */
	@TableField(value="CONTACT_PHONE",exist=true)
	java.lang.String contactPhone;
	
	/**
	 * 属性描述:公司名称
	 */
	@TableField(value="COMPANY_NAME",exist=true)
	java.lang.String companyName;
	
	/**
	 * 属性描述:公司税号
	 */
	@TableField(value="COMPANY_TAX_NUM",exist=true)
	java.lang.String companyTaxNum;
	
	/**
	 * 属性描述:开户银行
	 */
	@TableField(value="OPENING_BANK",exist=true)
	java.lang.String openingBank;
	
	/**
	 * 属性描述:公司地址
	 */
	@TableField(value="COMPANY_ADDRESS",exist=true)
	java.lang.String companyAddress;
	
	/**
	 * 属性描述:固话
	 */
	@TableField(value="COMPANY_TEL",exist=true)
	java.lang.String companyTel;
	
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
	
	
	public java.lang.String getSupplierName(){
		return this.supplierName;
	}
	
	public void setSupplierName(java.lang.String supplierName){
		this.supplierName=supplierName;
	}
	
	
	public java.lang.String getGoodsPerson(){
		return this.goodsPerson;
	}
	
	public void setGoodsPerson(java.lang.String goodsPerson){
		this.goodsPerson=goodsPerson;
	}
	
	
	public java.lang.String getSupplierType(){
		return this.supplierType;
	}
	
	public void setSupplierType(java.lang.String supplierType){
		this.supplierType=supplierType;
	}
	
	
	public java.lang.String getContactPhone(){
		return this.contactPhone;
	}
	
	public void setContactPhone(java.lang.String contactPhone){
		this.contactPhone=contactPhone;
	}
	
	
	public java.lang.String getCompanyName(){
		return this.companyName;
	}
	
	public void setCompanyName(java.lang.String companyName){
		this.companyName=companyName;
	}
	
	
	public java.lang.String getCompanyTaxNum(){
		return this.companyTaxNum;
	}
	
	public void setCompanyTaxNum(java.lang.String companyTaxNum){
		this.companyTaxNum=companyTaxNum;
	}
	
	
	public java.lang.String getOpeningBank(){
		return this.openingBank;
	}
	
	public void setOpeningBank(java.lang.String openingBank){
		this.openingBank=openingBank;
	}
	
	
	public java.lang.String getCompanyAddress(){
		return this.companyAddress;
	}
	
	public void setCompanyAddress(java.lang.String companyAddress){
		this.companyAddress=companyAddress;
	}
	
	
	public java.lang.String getCompanyTel(){
		return this.companyTel;
	}
	
	public void setCompanyTel(java.lang.String companyTel){
		this.companyTel=companyTel;
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
			sb.append(", supplierName=").append(supplierName);			
			sb.append(", goodsPerson=").append(goodsPerson);			
			sb.append(", supplierType=").append(supplierType);			
			sb.append(", contactPhone=").append(contactPhone);			
			sb.append(", companyName=").append(companyName);			
			sb.append(", companyTaxNum=").append(companyTaxNum);			
			sb.append(", openingBank=").append(openingBank);			
			sb.append(", companyAddress=").append(companyAddress);			
			sb.append(", companyTel=").append(companyTel);			
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
        ErpSupplier other = (ErpSupplier) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getSupplierName() == null ? other.getId() == null : this.getSupplierName().equals(other.getSupplierName()))		
				        		&&(this.getGoodsPerson() == null ? other.getId() == null : this.getGoodsPerson().equals(other.getGoodsPerson()))		
				        		&&(this.getSupplierType() == null ? other.getId() == null : this.getSupplierType().equals(other.getSupplierType()))		
				        		&&(this.getContactPhone() == null ? other.getId() == null : this.getContactPhone().equals(other.getContactPhone()))		
				        		&&(this.getCompanyName() == null ? other.getId() == null : this.getCompanyName().equals(other.getCompanyName()))		
				        		&&(this.getCompanyTaxNum() == null ? other.getId() == null : this.getCompanyTaxNum().equals(other.getCompanyTaxNum()))		
				        		&&(this.getOpeningBank() == null ? other.getId() == null : this.getOpeningBank().equals(other.getOpeningBank()))		
				        		&&(this.getCompanyAddress() == null ? other.getId() == null : this.getCompanyAddress().equals(other.getCompanyAddress()))		
				        		&&(this.getCompanyTel() == null ? other.getId() == null : this.getCompanyTel().equals(other.getCompanyTel()))		
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
		        result = prime * result + ((getSupplierName() == null) ? 0 : getSupplierName().hashCode());		
		        result = prime * result + ((getGoodsPerson() == null) ? 0 : getGoodsPerson().hashCode());		
		        result = prime * result + ((getSupplierType() == null) ? 0 : getSupplierType().hashCode());		
		        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());		
		        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());		
		        result = prime * result + ((getCompanyTaxNum() == null) ? 0 : getCompanyTaxNum().hashCode());		
		        result = prime * result + ((getOpeningBank() == null) ? 0 : getOpeningBank().hashCode());		
		        result = prime * result + ((getCompanyAddress() == null) ? 0 : getCompanyAddress().hashCode());		
		        result = prime * result + ((getCompanyTel() == null) ? 0 : getCompanyTel().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}

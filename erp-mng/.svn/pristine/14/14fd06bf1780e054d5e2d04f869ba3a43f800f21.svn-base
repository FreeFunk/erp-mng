package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpSupplierQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpSupplierView queryObj = new ErpSupplierView();

	public ErpSupplierView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpSupplierView queryObj) {
		this.queryObj = queryObj;
	}


}

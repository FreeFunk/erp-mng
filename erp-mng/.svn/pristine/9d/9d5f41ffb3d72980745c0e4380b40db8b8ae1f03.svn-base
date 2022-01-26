package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpCompCodeQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpCompCodeView queryObj = new ErpCompCodeView();

	public ErpCompCodeView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpCompCodeView queryObj) {
		this.queryObj = queryObj;
	}
}

package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpUnlocationRemindQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpUnlocationRemindView queryObj = new ErpUnlocationRemindView();

	public ErpUnlocationRemindView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpUnlocationRemindView queryObj) {
		this.queryObj = queryObj;
	}
}

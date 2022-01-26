package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpUnlocationRemindCarInfoQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpUnlocationRemindCarInfoView queryObj = new ErpUnlocationRemindCarInfoView();

	public ErpUnlocationRemindCarInfoView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpUnlocationRemindCarInfoView queryObj) {
		this.queryObj = queryObj;
	}
}

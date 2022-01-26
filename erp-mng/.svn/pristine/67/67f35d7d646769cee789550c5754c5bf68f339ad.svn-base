package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpCarInfoQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpCarInfoView queryObj = new ErpCarInfoView();

	public ErpCarInfoView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpCarInfoView queryObj) {
		this.queryObj = queryObj;
	}
}

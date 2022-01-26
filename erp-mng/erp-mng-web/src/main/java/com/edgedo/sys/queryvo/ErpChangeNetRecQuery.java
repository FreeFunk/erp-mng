package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpChangeNetRecQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpChangeNetRecView queryObj = new ErpChangeNetRecView();

	public ErpChangeNetRecView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpChangeNetRecView queryObj) {
		this.queryObj = queryObj;
	}
}

package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpUserWorkLogQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpUserWorkLogView queryObj = new ErpUserWorkLogView();

	public ErpUserWorkLogView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpUserWorkLogView queryObj) {
		this.queryObj = queryObj;
	}
}

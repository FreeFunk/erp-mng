package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpOperatorQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpOperatorView queryObj = new ErpOperatorView();

	public ErpOperatorView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpOperatorView queryObj) {
		this.queryObj = queryObj;
	}
}

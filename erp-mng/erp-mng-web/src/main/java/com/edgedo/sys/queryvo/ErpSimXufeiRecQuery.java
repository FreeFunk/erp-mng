package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpSimXufeiRecQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpSimXufeiRecView queryObj = new ErpSimXufeiRecView();

	public ErpSimXufeiRecView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpSimXufeiRecView queryObj) {
		this.queryObj = queryObj;
	}
}

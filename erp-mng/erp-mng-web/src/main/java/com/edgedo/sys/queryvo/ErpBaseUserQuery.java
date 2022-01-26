package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpBaseUserQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpBaseUserView queryObj = new ErpBaseUserView();

	public ErpBaseUserView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpBaseUserView queryObj) {
		this.queryObj = queryObj;
	}
}

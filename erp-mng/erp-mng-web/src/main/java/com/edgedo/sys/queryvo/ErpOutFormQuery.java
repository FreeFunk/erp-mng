package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpOutFormQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpOutFormView queryObj = new ErpOutFormView();

	public ErpOutFormView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpOutFormView queryObj) {
		this.queryObj = queryObj;
	}
}

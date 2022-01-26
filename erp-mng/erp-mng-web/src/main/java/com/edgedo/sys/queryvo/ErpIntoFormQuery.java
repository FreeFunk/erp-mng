package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpIntoFormQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpIntoFormView queryObj = new ErpIntoFormView();

	public ErpIntoFormView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpIntoFormView queryObj) {
		this.queryObj = queryObj;
	}
}

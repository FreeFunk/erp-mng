package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpInstructionsFormQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpInstructionsFormView queryObj = new ErpInstructionsFormView();

	public ErpInstructionsFormView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpInstructionsFormView queryObj) {
		this.queryObj = queryObj;
	}
}

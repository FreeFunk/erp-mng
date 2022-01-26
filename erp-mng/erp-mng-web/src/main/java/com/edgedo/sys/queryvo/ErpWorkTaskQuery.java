package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpWorkTaskQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpWorkTaskView queryObj = new ErpWorkTaskView();

	public ErpWorkTaskView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpWorkTaskView queryObj) {
		this.queryObj = queryObj;
	}
}

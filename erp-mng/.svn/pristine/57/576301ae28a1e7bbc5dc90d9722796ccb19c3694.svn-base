package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpGpsTerminalQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpGpsTerminalView queryObj = new ErpGpsTerminalView();

	public ErpGpsTerminalView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpGpsTerminalView queryObj) {
		this.queryObj = queryObj;
	}
}

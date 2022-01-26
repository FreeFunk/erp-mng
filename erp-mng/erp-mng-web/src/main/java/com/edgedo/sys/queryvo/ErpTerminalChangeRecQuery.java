package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpTerminalChangeRecQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpTerminalChangeRecView queryObj = new ErpTerminalChangeRecView();

	public ErpTerminalChangeRecView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpTerminalChangeRecView queryObj) {
		this.queryObj = queryObj;
	}
}

package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpSimQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpSimView queryObj = new ErpSimView();

	public ErpSimView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpSimView queryObj) {
		this.queryObj = queryObj;
	}
}

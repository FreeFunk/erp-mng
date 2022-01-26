package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpWorkTaskOrderQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpWorkTaskOrderView queryObj = new ErpWorkTaskOrderView();

	public ErpWorkTaskOrderView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpWorkTaskOrderView queryObj) {
		this.queryObj = queryObj;
	}
}

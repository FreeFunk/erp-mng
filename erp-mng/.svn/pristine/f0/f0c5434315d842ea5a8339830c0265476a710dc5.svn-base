package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpCarDetailedInfoQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpCarDetailedInfoView queryObj = new ErpCarDetailedInfoView();

	public ErpCarDetailedInfoView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpCarDetailedInfoView queryObj) {
		this.queryObj = queryObj;
	}
}

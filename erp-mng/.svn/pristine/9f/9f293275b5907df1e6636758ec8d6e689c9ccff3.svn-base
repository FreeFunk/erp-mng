package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpCarSimpleInfoQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpCarSimpleInfoView queryObj = new ErpCarSimpleInfoView();

	public ErpCarSimpleInfoView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpCarSimpleInfoView queryObj) {
		this.queryObj = queryObj;
	}
}

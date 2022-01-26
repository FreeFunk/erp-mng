package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpChangeNetSumMonthQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpChangeNetSumMonthView queryObj = new ErpChangeNetSumMonthView();

	public ErpChangeNetSumMonthView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpChangeNetSumMonthView queryObj) {
		this.queryObj = queryObj;
	}
}

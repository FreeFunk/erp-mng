package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpAddCarStatisticsQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpAddCarStatisticsView queryObj = new ErpAddCarStatisticsView();

	public ErpAddCarStatisticsView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpAddCarStatisticsView queryObj) {
		this.queryObj = queryObj;
	}
}

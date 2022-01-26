package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpBeidouAddStatisticsQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpBeidouAddStatisticsView queryObj = new ErpBeidouAddStatisticsView();

	public ErpBeidouAddStatisticsView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpBeidouAddStatisticsView queryObj) {
		this.queryObj = queryObj;
	}
}

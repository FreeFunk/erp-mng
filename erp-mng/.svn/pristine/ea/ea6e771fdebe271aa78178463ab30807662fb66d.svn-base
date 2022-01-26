package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpCarTeamQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpCarTeamView queryObj = new ErpCarTeamView();

	public ErpCarTeamView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpCarTeamView queryObj) {
		this.queryObj = queryObj;
	}
}

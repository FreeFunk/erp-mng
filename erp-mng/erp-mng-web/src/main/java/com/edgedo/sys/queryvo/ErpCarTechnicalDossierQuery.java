package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpCarTechnicalDossierQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpCarTechnicalDossierView queryObj = new ErpCarTechnicalDossierView();

	public ErpCarTechnicalDossierView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpCarTechnicalDossierView queryObj) {
		this.queryObj = queryObj;
	}
}

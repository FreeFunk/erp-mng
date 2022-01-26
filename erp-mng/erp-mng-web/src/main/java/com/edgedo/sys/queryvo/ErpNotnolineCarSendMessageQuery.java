package com.edgedo.sys.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpNotnolineCarSendMessageQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private ErpNotnolineCarSendMessageView queryObj = new ErpNotnolineCarSendMessageView();

	public ErpNotnolineCarSendMessageView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(ErpNotnolineCarSendMessageView queryObj) {
		this.queryObj = queryObj;
	}
}

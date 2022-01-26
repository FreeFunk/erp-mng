package com.edgedo.sys.queryvo;

import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class ErpChangeNetCounMonthQuery extends QueryObj {
    @JsonSerialize(include=Inclusion.NON_EMPTY)
    private ErpChangeNetCounMonthView queryObj = new ErpChangeNetCounMonthView();

    public ErpChangeNetCounMonthView getQueryObj() {
        return queryObj;
    }

    public void setQueryObj(ErpChangeNetCounMonthView queryObj) {
        this.queryObj = queryObj;
    }
}

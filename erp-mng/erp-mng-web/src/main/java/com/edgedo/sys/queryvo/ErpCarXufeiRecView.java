package com.edgedo.sys.queryvo;

import com.edgedo.sys.entity.ErpCarXufeiRec;

import java.util.Date;

public class ErpCarXufeiRecView extends ErpCarXufeiRec {

    private Date xufeiTimeStart;
    private Date xufeiTimeEnd;
    private String xufeiTimeStartStr;
    private String xufeiTimeEndStr;

    public String getXufeiTimeStartStr() {
        return xufeiTimeStartStr;
    }

    public void setXufeiTimeStartStr(String xufeiTimeStartStr) {
        this.xufeiTimeStartStr = xufeiTimeStartStr;
    }

    public String getXufeiTimeEndStr() {
        return xufeiTimeEndStr;
    }

    public void setXufeiTimeEndStr(String xufeiTimeEndStr) {
        this.xufeiTimeEndStr = xufeiTimeEndStr;
    }

    public Date getXufeiTimeStart() {
        return xufeiTimeStart;
    }

    public void setXufeiTimeStart(Date xufeiTimeStart) {
        this.xufeiTimeStart = xufeiTimeStart;
    }

    public Date getXufeiTimeEnd() {
        return xufeiTimeEnd;
    }

    public void setXufeiTimeEnd(Date xufeiTimeEnd) {
        this.xufeiTimeEnd = xufeiTimeEnd;
    }
}

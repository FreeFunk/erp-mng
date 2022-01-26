package com.edgedo.sys.queryvo;

import com.edgedo.sys.entity.ErpSimXufeiRec;

public class ErpSimXufeiRecView extends ErpSimXufeiRec {

    private String selectTime;
    private String newEndTime;

    public String getNewEndTime() {
        return newEndTime;
    }

    public void setNewEndTime(String newEndTime) {
        this.newEndTime = newEndTime;
    }


    public String getSelectTime() {
        return selectTime;
    }

    public void setSelectTime(String selectTime) {
        this.selectTime = selectTime;
    }
}

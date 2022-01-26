package com.edgedo.sys.queryvo;

import com.edgedo.sys.entity.ErpChannelAgent;

public class ErpChannelAgentView extends ErpChannelAgent {
    //所属用户名
    private String userName;

    //所属用户密码
    private String password;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}

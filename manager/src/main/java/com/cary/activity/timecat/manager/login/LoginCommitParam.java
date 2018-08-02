package com.cary.activity.timecat.manager.login;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cary on 2018/4/6.
 */

public class LoginCommitParam {
    private String mobile;
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginCommitParam{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public Map<String, String> createCommitParams(){
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("password", password);
        return params;
    }
}

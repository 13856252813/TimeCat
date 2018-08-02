package com.cary.activity.timecat.manager.register;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cary on 2018/4/6.
 */

public class ForgetPassCommitParam implements Serializable {
    private String mobile;//手机号
    private String password;//密码
    private String smsCode;//验证码

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

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    public String toString() {
        return "ForgetPassCommitParam{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", smsCode='" + smsCode + '\'' +
                '}';
    }

    public Map<String, String> createCommitParams(){
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("password", password);
        params.put("smsCode", smsCode);
        return params;
    }

}

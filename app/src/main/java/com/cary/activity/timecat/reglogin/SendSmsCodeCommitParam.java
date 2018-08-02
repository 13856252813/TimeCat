package com.cary.activity.timecat.reglogin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cary on 2018/4/6.
 */

public class SendSmsCodeCommitParam {
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "SendSmsCodeCommitParam{" +
                "mobile='" + mobile + '\'' +
                '}';
    }

    public Map<String, String> createCommitParams(){
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        return params;
    }


}

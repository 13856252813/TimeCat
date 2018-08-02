package com.cary.activity.timecat.manager.register;

import java.io.Serializable;

/**
 * Created by Cary on 2018/4/6.
 */

public class NormalCommitResult implements Serializable {
    private String code;

    private String data;

    private String msg;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }


}

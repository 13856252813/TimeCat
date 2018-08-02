package com.cary.activity.timecat.manager.http.base;

/**
 * @see 网络访问的结果
 * Created by Cary on 2018/3/28.
 */

public class HttpResult<T> {
    private String desc;
    private int code;
    private T data;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

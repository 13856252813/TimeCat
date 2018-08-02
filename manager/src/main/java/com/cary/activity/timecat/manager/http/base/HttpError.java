package com.cary.activity.timecat.manager.http.base;

/**
 * @see 错误码
 * Created by Cary on 2018/3/28.
 */

public interface HttpError {

    interface Code {
        int SUCCESS = 10001;
        int PARAM_INVALID = 10002;
    }

    interface Desc {
        String SUCCESS = "success!";
        String PARAM_INVALID = "param is invalid!";
    }
}

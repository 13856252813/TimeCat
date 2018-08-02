package com.cary.activity.timecat.http.base;

import android.support.annotation.NonNull;

/**
 * @see 网络访问异常
 * Created by Cary on 2018/3/28.
 */

public class HttpException extends RuntimeException {
    public HttpException(String msg) {
        super(msg);
    }

    public HttpException(@NonNull HttpResult result) {
        super(result.getDesc() + ", code=" + result.getCode());
    }
}

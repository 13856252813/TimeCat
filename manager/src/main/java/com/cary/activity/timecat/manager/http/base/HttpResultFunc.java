package com.cary.activity.timecat.manager.http.base;

import rx.functions.Func1;

/**
 * @see 网络访问的基础拦截器
 * Created by Cary on 2018/3/28.
 */

public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
    @Override
    public T call(HttpResult<T> result) {
        if (result.getCode() != HttpError.Code.SUCCESS) {
            throw new HttpException(result);
        }
        return result.getData();
    }
}
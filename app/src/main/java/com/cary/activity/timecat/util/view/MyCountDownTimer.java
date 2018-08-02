package com.cary.activity.timecat.util.view;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Cary on 2018/4/1.
 * 继承 CountDownTimer 防范
 * 重写 父类的方法 onTick() 、 onFinish()
 */

public class MyCountDownTimer extends CountDownTimer {
    /**
     *
     * @param millisInFuture
     *      表示以毫秒为单位 倒计时的总数
     *
     *      例如 millisInFuture=1000 表示1秒
     *
     * @param countDownInterval
     *      表示 间隔 多少微秒 调用一次 onTick 方法
     *
     *      例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
     *
     */
    private TextView tv;
    public MyCountDownTimer(long millisInFuture, long countDownInterval,TextView tv) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;
    }

    @Override
    public void onFinish() {
        tv.setText("重新发送");
    }

    @Override
    public void onTick(long millisUntilFinished) {
        Log.i("MainActivity", millisUntilFinished + "");
        tv.setText("" + millisUntilFinished / 1000+"后重新发送");
    }
}

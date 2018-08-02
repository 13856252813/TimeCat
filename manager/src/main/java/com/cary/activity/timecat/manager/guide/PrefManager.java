package com.cary.activity.timecat.manager.guide;

import android.content.Context;
import android.content.SharedPreferences;

import com.cary.activity.timecat.manager.util.TimeUtil;


/**
 * Created by Cary on 2018/3/30.
 */

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    //SharedPreferences 文件名
    private static final String PREF_NAME = "intro_slider";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    private static final String SAVE_TIME = "SaveQuitTime";
    TimeUtil tu = new TimeUtil();

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setSaveTime() {
        editor.putString(SAVE_TIME, tu.getNowTime());
        editor.commit();
    }

    public String getSaveTime() {
        return pref.getString(SAVE_TIME, tu.getNowTime());
    }

}

package com.cary.activity.timecat.util;

import android.text.TextUtils;

public class StringUtils {

    public static String getCityNameFromDate(String data){
        if(!TextUtils.isEmpty(data)){
            String[] arr=data.split(" ");
            return arr[1];
        }
        return "";

    }
}

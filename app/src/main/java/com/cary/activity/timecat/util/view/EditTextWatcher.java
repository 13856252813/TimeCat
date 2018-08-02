package com.cary.activity.timecat.util.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Cary on 2018/4/1.
 */

public class EditTextWatcher implements TextWatcher {
    private EditText etText;

    public EditTextWatcher(EditText etText) {
        this.etText = etText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String editable = etText.getText().toString();
        String regEx = "[^a-zA-Z0-9]";  //只能输入字母或数字
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(editable);
        String str = m.replaceAll("").trim();    //删掉不是字母或数字的字符
        if (!editable.equals(str)) {
            etText.setText(str);  //设置EditText的字符
            etText.setSelection(str.length()); //因为删除了字符，要重写设置新的光标所在位置
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

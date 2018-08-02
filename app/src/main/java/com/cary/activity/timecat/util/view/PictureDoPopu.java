package com.cary.activity.timecat.util.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.cary.activity.timecat.R;

/**
 *
 */

public class PictureDoPopu implements View.OnClickListener,View.OnTouchListener {

    private PopupWindow popupWindow;
    private Button btnCopy, btnCut,btnDel,btnNote, btnCancel;
    private View mMenuView;
    private Activity mContext;
    private View.OnClickListener clickListener;

    public PictureDoPopu(Activity context, View.OnClickListener clickListener) {
        LayoutInflater inflater = LayoutInflater.from(context);
        this.clickListener=clickListener;
        mContext=context;
        mMenuView = inflater.inflate(R.layout.activity_picture_do, null);
        btnCopy = (Button) mMenuView.findViewById(R.id.btn_picture_do_copy);
        btnCut = (Button) mMenuView.findViewById(R.id.btn_picture_do_cut);
        btnCancel = (Button) mMenuView.findViewById(R.id.btn_picture_do_cancel);
        btnDel = (Button) mMenuView.findViewById(R.id.btn_picture_do_delete);
        btnNote = (Button) mMenuView.findViewById(R.id.btn_picture_do_note);
        btnCancel.setOnClickListener(this);
        btnCopy.setOnClickListener(this);
        btnCut.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnNote.setOnClickListener(this);
        popupWindow=new PopupWindow(mMenuView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.semitransparent));
        popupWindow.setBackgroundDrawable(dw);
        mMenuView.setOnTouchListener(this);
    }

    /**
     * 显示菜单
     */
    public void show(){
        //得到当前activity的rootView
        View rootView=((ViewGroup)mContext.findViewById(android.R.id.content)).getChildAt(0);
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onClick(View view) {
        popupWindow.dismiss();
        switch (view.getId()) {
            default:
                clickListener.onClick(view);
                break;
        }
    }

    @Override
    public boolean onTouch(View arg0, MotionEvent event) {
        int height = mMenuView.findViewById(R.id.pop_layout).getTop();
        int y=(int) event.getY();
        if(event.getAction()==MotionEvent.ACTION_UP){
            if(y<height){
                popupWindow. dismiss();
            }
        }
        return true;
    }


}
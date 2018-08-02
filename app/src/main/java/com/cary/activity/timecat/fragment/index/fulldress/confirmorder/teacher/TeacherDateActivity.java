package com.cary.activity.timecat.fragment.index.fulldress.confirmorder.teacher;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.confirmorder.teacher.view.DataUtils;
import com.cary.activity.timecat.fragment.index.fulldress.confirmorder.teacher.view.DateMonthAdapter;
import com.cary.activity.timecat.util.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 老师排期
 */
public class TeacherDateActivity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.now_day)
    TextView nowDay;
    @BindView(R.id.ll_now_day)
    LinearLayout llNowDay;
    @BindView(R.id.grid_date)
    GridView gridView;
    @BindView(R.id.iv_front_month)
    ImageView ivFrontMonth;
    @BindView(R.id.front_month)
    TextView frontMonth;
    @BindView(R.id.next_month)
    TextView nextMonth;

    private DateMonthAdapter adapter;
    private String dateid;
    private String date;
    private TimeUtil timeUtil;
    final int RIGHT = 0;
    final int LEFT = 1;
    private GestureDetector gestureDetector;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_teacher_date);
        ButterKnife.bind(this);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        titleText.setText("老师排期");
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dateid = getIntent().getStringExtra("id");

        timeUtil = new TimeUtil();
        date = timeUtil.getNowMonth();
        gestureDetector = new GestureDetector(this, onGestureListener);

        nowDay.setText(date);

        adapter = new DateMonthAdapter(this);
        adapter.setData(DataUtils.getMonth(date));
        gridView.setAdapter(adapter);
        adapter.setDateString(date);
        adapter.setSelectedPosition(DataUtils.getSelectPosition());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!TextUtils.isEmpty(adapter.getItem(position).date)) {
                    adapter.setSelectedPosition(position);
                    nowDay.setText("当前月份：" + DataUtils.formatDate(adapter.getItem(position).date, "yyyy年MM月"));
                    date = adapter.getItem(position).date;
                }
            }
        });
        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.activity_teacher_date;
    }

    /**
     * 手势监听是否是左右滑动
     */
    private GestureDetector.OnGestureListener onGestureListener =
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                       float velocityY) {
                    float x = e2.getX() - e1.getX();
                    float y = e2.getY() - e1.getY();

                    if (x > 100) {
                        doResult(RIGHT);
                    } else if (x < -100) {
                        doResult(LEFT);
                    }
                    return true;
                }
            };

    public void doResult(int action) {

        switch (action) {
            case RIGHT:
                date = DataUtils.getSomeMonthDay(date, -1);
                adapter.setData(DataUtils.getMonth(date));
                adapter.setDateString(date);
                adapter.setSelectedPosition(DataUtils.getSelectPosition());
                nowDay.setText(DataUtils.formatDate(date, "yyyy年MM月"));
                Log.e("wenzihao", "go right");
                break;
            case LEFT:
                date = DataUtils.getSomeMonthDay(date, +1);
                adapter.setData(DataUtils.getMonth(date));
                adapter.setDateString(date);
                adapter.setSelectedPosition(DataUtils.getSelectPosition());
                nowDay.setText(DataUtils.formatDate(date, "yyyy年MM月"));
                Log.e("wenzihao", "go left");
                break;

        }
    }


    @OnClick({R.id.iv_front_month, R.id.front_month, R.id.next_month})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_front_month:
                date = DataUtils.getSomeMonthDay(date, -1);
                adapter.setData(DataUtils.getMonth(date));
                adapter.setDateString(date);
                adapter.setSelectedPosition(DataUtils.getSelectPosition());
                nowDay.setText(DataUtils.formatDate(date, "yyyy年MM月"));

                break;
            case R.id.front_month:
                date = DataUtils.getSomeMonthDay(date, -1);
                adapter.setData(DataUtils.getMonth(date));
                adapter.setDateString(date);
                adapter.setSelectedPosition(DataUtils.getSelectPosition());
                nowDay.setText(DataUtils.formatDate(date, "yyyy年MM月"));

                break;
            case R.id.next_month:
                date = DataUtils.getSomeMonthDay(date, +1);
                adapter.setData(DataUtils.getMonth(date));
                adapter.setDateString(date);
                adapter.setSelectedPosition(DataUtils.getSelectPosition());
                nowDay.setText(DataUtils.formatDate(date, "yyyy年MM月"));

                break;
        }
    }
}

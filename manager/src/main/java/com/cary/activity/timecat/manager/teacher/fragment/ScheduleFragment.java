package com.cary.activity.timecat.manager.teacher.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.teacher.fragment.schedule.ScheduleApi;
import com.cary.activity.timecat.manager.teacher.fragment.schedule.ScheduleFragmentAdapter;
import com.cary.activity.timecat.manager.teacher.fragment.schedule.ScheduleResult;
import com.cary.activity.timecat.manager.teacher.fragment.schedule.view.DataUtils;
import com.cary.activity.timecat.manager.teacher.fragment.schedule.view.DateMonthAdapter;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;
import com.cary.activity.timecat.manager.util.TimeUtil;
import com.cary.activity.timecat.manager.util.ToastUtil;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 排期
 */
public class ScheduleFragment extends Fragment {

    private static final String TAG = ScheduleResult.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_front_month)
    ImageView ivFrontMonth;
    @BindView(R.id.front_month)
    TextView frontMonth;
    @BindView(R.id.now_day)
    TextView nowDay;
    @BindView(R.id.next_month)
    TextView nextMonth;
    @BindView(R.id.ll_now_day)
    LinearLayout llNowDay;
    @BindView(R.id.grid_date)
    GridView gridDate;
    Unbinder unbinder;
    @BindView(R.id.ll_grid_show)
    LinearLayout llGridShow;
    @BindView(R.id.tab_essence)
    TabLayout tabEssence;
    @BindView(R.id.vp_essence)
    ViewPager vpEssence;
    @BindView(R.id.ll_list_show)
    LinearLayout llListShow;

    private View viewContent;
    private DateMonthAdapter adapter;
    private String dateid;
    private String date;
    private TimeUtil timeUtil;
    final int RIGHT = 0;
    final int LEFT = 1;
    private GestureDetector gestureDetector;

    private int clickFlag = 0;

    private ScheduleApi mApi;
    private ScheduleResult mRes;
    private List<ScheduleResult.Data> mData;
    private SharedPreferencesHelper sharehelper;
    private int uid;
    private String token;
    private int currentpage = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_schedule_layout, container, false);
        unbinder = ButterKnife.bind(this, viewContent);

        mApi = ScheduleApi.getApi();
        sharehelper = new SharedPreferencesHelper(getActivity());
        token = (String) sharehelper.getSharedPreference("token", "");
        uid = (int) sharehelper.getSharedPreference("id", 0);

        initConentView(viewContent);

        timeUtil = new TimeUtil();
        date = timeUtil.getNowMonth();
        gestureDetector = new GestureDetector(getActivity(), onGestureListener);
        nowDay.setText(date);


        gridDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!TextUtils.isEmpty(adapter.getItem(position).date)) {
                    adapter.setSelectedPosition(position);
                    nowDay.setText("" + DataUtils.formatDate(adapter.getItem(position).date, "yyyy-MM-dd"));
                    date = adapter.getItem(position).date;
                }
            }
        });
        gridDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
        createSingle();
        return viewContent;
    }

    public void initConentView(View viewContent) {
        this.titleText.setText("排期");
        this.titleTextRight.setText("切换显示");
        this.titleBack.setVisibility(View.GONE);
        this.titleTextRight.setVisibility(View.VISIBLE);
        this.rlTitle.setBackgroundColor(getResources().getColor(R.color.white));
        this.titleText.setTextColor(getResources().getColor(R.color.color_three));
        this.titleTextRight.setTextColor(getResources().getColor(R.color.login_color_btn));
        this.titleTextRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickFlag == 0) {
                    initData();
                    clickFlag = 1;
                    llGridShow.setVisibility(View.GONE);
                    llListShow.setVisibility(View.VISIBLE);
                } else if (clickFlag == 1) {
                    clickFlag = 0;
                    llGridShow.setVisibility(View.VISIBLE);
                    llListShow.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
                adapter.setData(DataUtils.getMonth(date, mData));
                adapter.setDateString(date);
                adapter.setSelectedPosition(DataUtils.getSelectPosition());
                nowDay.setText(DataUtils.formatDate(date, "yyyy-MM-dd"));
                break;
            case LEFT:
                date = DataUtils.getSomeMonthDay(date, +1);
                adapter.setData(DataUtils.getMonth(date, mData));
                adapter.setDateString(date);
                adapter.setSelectedPosition(DataUtils.getSelectPosition());
                nowDay.setText(DataUtils.formatDate(date, "yyyy-MM-dd"));
                break;

        }
    }


    @OnClick({R.id.iv_front_month, R.id.front_month, R.id.next_month})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_front_month:
                date = DataUtils.getSomeMonthDay(date, -1);
                adapter.setData(DataUtils.getMonth(date, mData));
                adapter.setDateString(date);
                adapter.setSelectedPosition(DataUtils.getSelectPosition());
                nowDay.setText(DataUtils.formatDate(date, "yyyy-MM-dd"));

                break;
            case R.id.front_month:
                date = DataUtils.getSomeMonthDay(date, -1);
                adapter.setData(DataUtils.getMonth(date, mData));
                adapter.setDateString(date);
                adapter.setSelectedPosition(DataUtils.getSelectPosition());
                nowDay.setText(DataUtils.formatDate(date, "yyyy-MM-dd"));

                break;
            case R.id.next_month:
                date = DataUtils.getSomeMonthDay(date, +1);
                adapter.setData(DataUtils.getMonth(date, mData));
                adapter.setDateString(date);
                adapter.setSelectedPosition(DataUtils.getSelectPosition());
                nowDay.setText(DataUtils.formatDate(date, "yyyy-MM-dd"));

                break;
        }
    }

    public void initData() {
        //获取标签数据
        String[] titles = getResources().getStringArray(R.array.schedule_tab);

        //创建一个viewpager的adapter
        ScheduleFragmentAdapter adapter = new ScheduleFragmentAdapter(getFragmentManager(), Arrays.asList(titles));
        this.vpEssence.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来
        this.tabEssence.setupWithViewPager(vpEssence);
    }

    private Callback<ScheduleResult> callback = new Callback<ScheduleResult>() {
        @Override
        public void onResponse(Call<ScheduleResult> call, Response<ScheduleResult> response) {
            if (response.isSuccessful()) {
                Log.i(TAG, "success!!!");
                Log.i(TAG, "---" + response.body().toString());
                mRes = response.body();
                if ("00".equals(mRes.getCode())) {
                    mData = mRes.getData();
                    adapter = new DateMonthAdapter(getActivity());
                    adapter.setData(DataUtils.getMonth(date, mData));
                    gridDate.setAdapter(adapter);
                    adapter.setDateString(date);
                    adapter.setSelectedPosition(DataUtils.getSelectPosition());
                    adapter.setLists(mRes.getData());

                } else {
                    ToastUtil.showShort(getActivity(), mRes.getMsg());
                }
            } else {
                Log.e(TAG, "+++" + response.message());
            }

        }

        @Override
        public void onFailure(Call<ScheduleResult> call, Throwable t) {
            Log.e(TAG, "***" + t.getMessage());
        }
    };

    private void createSingle() {
        Call<ScheduleResult> call = mApi.getService().createCommitPage(token, currentpage);
        call.enqueue(callback);
    }

}
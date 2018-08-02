package com.cary.activity.timecat.fragment.person.attention;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的关注
 */
public class MyAttentionActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_meal_text)
    TextView tvMealText;
    @BindView(R.id.tv_meal_line)
    TextView tvMealLine;
    @BindView(R.id.ll_meal)
    LinearLayout llMeal;
    @BindView(R.id.tv_teacher_text)
    TextView tvTeacherText;
    @BindView(R.id.tv_teacher_line)
    TextView tvTeacherLine;
    @BindView(R.id.ll_teacher)
    LinearLayout llTeacher;
    @BindView(R.id.tv_store_text)
    TextView tvStoreText;
    @BindView(R.id.tv_store_line)
    TextView tvStoreLine;
    @BindView(R.id.ll_store)
    LinearLayout llStore;
    @BindView(R.id.tv_news_text)
    TextView tvNewsText;
    @BindView(R.id.tv_news_line)
    TextView tvNewsLine;
    @BindView(R.id.ll_news)
    LinearLayout llNews;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.lincontent)
    LinearLayout lincontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_attention);
        ButterKnife.bind(this);
        titleText.setText("我的关注");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));

        //调用切换Fragmnet方法
        changeFragmnet(new AttentionMealFragment());

    }

    //切换fragment的方法
    public void changeFragmnet(Fragment fr) {

        //第一步：得到fragment管理类
        FragmentManager manager = getSupportFragmentManager();
        //得到事务
        FragmentTransaction beginTransaction = manager.beginTransaction();
        //调用replace 添加fragment
        beginTransaction.replace(R.id.lincontent, fr);
        //提交
        beginTransaction.commit();

    }

    @OnClick({R.id.title_back, R.id.ll_meal, R.id.ll_teacher, R.id.ll_store, R.id.ll_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.ll_meal:
                changeFragmnet(new AttentionMealFragment());
                SetTextColor(tvMealText, tvMealLine, true);
                SetTextColor(tvTeacherText, tvTeacherLine, false);
                SetTextColor(tvStoreText, tvStoreLine, false);
                SetTextColor(tvNewsText, tvNewsLine, false);

                break;
            case R.id.ll_teacher:
                changeFragmnet(new TeacherMealFragment());
                SetTextColor(tvMealText, tvMealLine, false);
                SetTextColor(tvTeacherText, tvTeacherLine, true);
                SetTextColor(tvStoreText, tvStoreLine, false);
                SetTextColor(tvNewsText, tvNewsLine, false);

                break;
            case R.id.ll_store:
                changeFragmnet(new StoreMealFragment());
                SetTextColor(tvMealText, tvMealLine, false);
                SetTextColor(tvTeacherText, tvTeacherLine, false);
                SetTextColor(tvStoreText, tvStoreLine, true);
                SetTextColor(tvNewsText, tvNewsLine, false);

                break;
            case R.id.ll_news:
                changeFragmnet(new NewsMealFragment());
                SetTextColor(tvMealText, tvMealLine, false);
                SetTextColor(tvTeacherText, tvTeacherLine, false);
                SetTextColor(tvStoreText, tvStoreLine, false);
                SetTextColor(tvNewsText, tvNewsLine, true);

                break;
        }
    }

    private void SetTextColor(TextView text, TextView line, boolean isShow) {
        if (isShow) {
            text.setTextColor(getResources().getColor(R.color.login_color_btn));
            line.setBackgroundColor(getResources().getColor(R.color.login_color_btn));
        } else {
            text.setTextColor(getResources().getColor(R.color.color_three));
            line.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
    }
}

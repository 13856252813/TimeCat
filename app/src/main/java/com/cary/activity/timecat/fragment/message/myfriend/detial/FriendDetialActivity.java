package com.cary.activity.timecat.fragment.message.myfriend.detial;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.timeclouddish.showimage.SpaceItemDecoration;
import com.cary.activity.timecat.util.SharedPreferencesHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendDetialActivity extends AppCompatActivity {
    private static final String TAG = FriendDetialActivity.class.getSimpleName();

    Drawable drawable1, drawable2;
    int flag = 0;
    @BindView(R.id.iv_friend_detial_back)
    ImageView ivFriendDetialBack;
    @BindView(R.id.iv_friend_detial_head)
    ImageView ivFriendDetialHead;
    @BindView(R.id.tv_friend_detial_name)
    TextView tvFriendDetialName;
    @BindView(R.id.tv_friend_detial_cloudfish)
    TextView tvFriendDetialCloudfish;
    @BindView(R.id.tv_friend_detial_collect)
    TextView tvFriendDetialCollect;
    @BindView(R.id.recycler_friend_deital_news)
    RecyclerView recyclerFriendDeitalNews;
    @BindView(R.id.tv_friend_detial_sendmsg)
    TextView tvFriendDetialSendmsg;

    private FriendNewsAdapter mFriendAdapter;
    private SharedPreferencesHelper shareHelper;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detial);
        ButterKnife.bind(this);


        //默认API 最低19
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup contentView = window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
            contentView.getChildAt(0).setFitsSystemWindows(false);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION  //该参数指布局能延伸到navigationbar，我们场景中不应加这个参数
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT); //设置navigationbar颜色为透明
        }


        drawable2 = getResources().getDrawable(R.mipmap.heart4);// 找到资源图片
// 这一步必须要做，否则不会显示。
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());// 设置图片宽高
        drawable1 = getResources().getDrawable(R.mipmap.heart3);// 找到资源图片
// 这一步必须要做，否则不会显示。
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());// 设置图片宽高
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerFriendDeitalNews.setLayoutManager(linearLayoutManager);
        mFriendAdapter = new FriendNewsAdapter(this);
        //设置item间距，30dp
        recyclerFriendDeitalNews.addItemDecoration(new SpaceItemDecoration(20));
        recyclerFriendDeitalNews.setAdapter(mFriendAdapter);
        shareHelper = new SharedPreferencesHelper(this);
        mFriendAdapter.setItemClickListener(new FriendNewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(FriendDetialActivity.this, FriendDetialActivity.class);
                intent.putExtra("id", "123");
                startActivity(intent);
                finish();
            }
        });
        id = getIntent().getIntExtra("id",0);
    }

    @OnClick({R.id.iv_friend_detial_back, R.id.tv_friend_detial_sendmsg, R.id.iv_friend_detial_head, R.id.tv_friend_detial_cloudfish, R.id.tv_friend_detial_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_friend_detial_back:
                finish();
                break;
            case R.id.iv_friend_detial_head:

                break;
            case R.id.tv_friend_detial_cloudfish:

                break;
            case R.id.tv_friend_detial_collect:
                if (flag == 0) {
                    tvFriendDetialCollect.setCompoundDrawables(null, drawable1, null, null);// 设置到控件中
                    flag = 1;
                } else if (flag == 1) {
                    tvFriendDetialCollect.setCompoundDrawables(null, drawable2, null, null);// 设置到控件中
                    flag = 0;
                }
                break;
            case R.id.tv_friend_detial_sendmsg:

                break;
        }
    }

}

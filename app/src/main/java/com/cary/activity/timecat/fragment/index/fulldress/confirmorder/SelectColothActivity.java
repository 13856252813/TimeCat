package com.cary.activity.timecat.fragment.index.fulldress.confirmorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.detial.FullDressDetialActivity;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectColothActivity extends AppCompatActivity {
    private static final String TAG = SelectTeacherActivity.class.getSimpleName();

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_text_right)
    TextView titleTextRight;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.fulldess_recyclerview_grid)
    RecyclerView fulldessRecyclerviewGrid;

    private String sex;
    private List<String> mGridList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_coloth);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        titleText.setText("选择服装");
        rlTitle.setBackgroundColor(getResources().getColor(android.R.color.white));
        titleText.setTextColor(getResources().getColor(R.color.color_three));
        titleBack.setPadding(20, 0, 0, 0);
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.leftarrow));
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sex = getIntent().getStringExtra("sex");
        for (int i = 0; i < 18; i++) {
            mGridList.add(sex+"title" + i);
        }

        loadListDate(false, true, fulldessRecyclerviewGrid, mGridList);
        fulldessRecyclerviewGrid.setNestedScrollingEnabled(false);
    }

    //此处是底部的gridview 的列表
    //RecyclerView实现ListView效果，实际就是布局管理器参数改为GridLayoutManager
    private void loadListDate(Boolean inversion, Boolean orientation,
                              final RecyclerView recyclerViewGrid, List<String> mGridList) {

//创建适配器adapter对象 参数1.上下文 2.数据加载集合
        SelectColothGridAdapter recyclerViewGridAdapter = new SelectColothGridAdapter(this, mGridList);
//设置适配器
        recyclerViewGrid.setAdapter(recyclerViewGridAdapter);
//布局管理器对象 参数1.上下文 2.规定显示的行数
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//通过布局管理器可以控制条目排列的顺序 true反向显示 false正常显示(默认)
        gridLayoutManager.setReverseLayout(inversion);
//设置RecycleView显示的方向是水平还是垂直
//GridLayout.HORIZONTAL水平 GridLayout.VERTICAL默认垂直
// 三元运算符
        gridLayoutManager.setOrientation(orientation ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);
//设置布局管理器， 参数linearLayoutManager对象
        recyclerViewGrid.setLayoutManager(gridLayoutManager);
        recyclerViewGridAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(SelectColothActivity.this, FullDressDetialActivity.class);
                intent.putExtra("id","123");
                intent.putExtra("flagTag",sex);
                startActivity(intent);

            }
        });
    }
}

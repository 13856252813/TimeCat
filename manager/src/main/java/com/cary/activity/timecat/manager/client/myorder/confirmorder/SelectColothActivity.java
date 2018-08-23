package com.cary.activity.timecat.manager.client.myorder.confirmorder;

import android.annotation.SuppressLint;
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

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("Registered")
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
        titleBack.setImageDrawable(getResources().getDrawable(R.mipmap.left_arrow));
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

        SelectColothGridAdapter recyclerViewGridAdapter = new SelectColothGridAdapter(this, mGridList);
        recyclerViewGrid.setAdapter(recyclerViewGridAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setReverseLayout(inversion);
// 三元运算符
        gridLayoutManager.setOrientation(orientation ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);
//设置布局管理器， 参数linearLayoutManager对象
        recyclerViewGrid.setLayoutManager(gridLayoutManager);
        recyclerViewGridAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
//                Intent intent = new Intent(SelectColothActivity.this, FullDressDetialActivity.class);
//                intent.putExtra("id","123");
//                intent.putExtra("flagTag",sex);
//                startActivity(intent);
            }

            @Override
            public void onItemClick(int postion) {

            }
        });
    }
}

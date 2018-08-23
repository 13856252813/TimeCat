package com.cary.activity.timecat.fragment.index.fulldress.confirmorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cary.activity.timecat.BaseActivity;
import com.cary.activity.timecat.R;
import com.cary.activity.timecat.fragment.index.fulldress.detial.FullDressDetialActivity;
import com.cary.activity.timecat.main.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SelectColothActivity extends BaseActivity {
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

    @Override
    public int getLayout() {
        return R.layout.activity_select_coloth;
    }

    private void loadListDate(Boolean inversion, Boolean orientation,
                              final RecyclerView recyclerViewGrid, List<String> mGridList) {

        SelectColothGridAdapter recyclerViewGridAdapter = new SelectColothGridAdapter(this, mGridList);
        recyclerViewGrid.setAdapter(recyclerViewGridAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setReverseLayout(inversion);
        gridLayoutManager.setOrientation(orientation ? GridLayout.VERTICAL : GridLayout.HORIZONTAL);
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

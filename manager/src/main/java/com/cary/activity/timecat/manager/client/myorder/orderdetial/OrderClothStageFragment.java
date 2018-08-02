package com.cary.activity.timecat.manager.client.myorder.orderdetial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cary.activity.timecat.manager.R;
import com.cary.activity.timecat.manager.adapter.SpaceItemDecoration;
import com.cary.activity.timecat.manager.util.SharedPreferencesHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 服装风格
 */
public class OrderClothStageFragment extends Fragment {
    private static String TAG = OrderClothStageFragment.class.getSimpleName();
    Unbinder unbinder;
    @BindView(R.id.order_clothmould_recycler)
    RecyclerView orderClothmouldRecycler;
    private OrderClothItemAdapter myAdapter;
    private SharedPreferencesHelper sharePH;

    public OrderClothStageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_clothmould, container, false);
        unbinder = ButterKnife.bind(this, view);
        sharePH = new SharedPreferencesHelper(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderClothmouldRecycler.setLayoutManager(linearLayoutManager);
        myAdapter = new OrderClothItemAdapter(getActivity());
        //设置item间距，30dp
        orderClothmouldRecycler.addItemDecoration(new SpaceItemDecoration(15));
        orderClothmouldRecycler.setAdapter(myAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

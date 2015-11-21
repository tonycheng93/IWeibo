package com.tony.iweibo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tony.iweibo.BaseFragment;
import com.tony.iweibo.R;
import com.tony.iweibo.adapter.StatusAdapter;
import com.tony.iweibo.api.IWeiBoApi;
import com.tony.iweibo.api.SimpleRequestListener;
import com.tony.iweibo.entity.response.StatusTimeLineResponse;
import com.tony.iweibo.utils.TitleBuilder;
import com.tony.iweibo.utils.ToastUtils;

public class HomeFragment extends BaseFragment {

    private View mView;
    private ListView lv_home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initView();
        loadData(1);
        return mView;
    }

    private void loadData(int page) {
        IWeiBoApi api = new IWeiBoApi(activity);
        api.statusesHome_timeline(page, new SimpleRequestListener(activity, null) {
            @Override
            public void onComplete(String response) {
                super.onComplete(response);

                StatusTimeLineResponse timeLineResponse =
                        new Gson().fromJson(response, StatusTimeLineResponse.class);
                lv_home.setAdapter(new StatusAdapter(activity,timeLineResponse.getStatuses()));
            }
        });
    }

    private void initView() {
        // Inflate the layout for this fragment
        mView = View.inflate(activity, R.layout.fragment_home, null);

        new TitleBuilder(mView)
                .setTitleText("首页")
                .setLeftText("Left")
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtils.showToast(activity, "left click", Toast.LENGTH_SHORT);
                    }
                });
        lv_home = (ListView) mView.findViewById(R.id.lv_home);
    }
}

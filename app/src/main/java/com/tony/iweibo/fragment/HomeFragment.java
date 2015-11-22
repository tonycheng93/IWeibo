package com.tony.iweibo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tony.iweibo.BaseFragment;
import com.tony.iweibo.R;
import com.tony.iweibo.adapter.StatusAdapter;
import com.tony.iweibo.api.IWeiBoApi;
import com.tony.iweibo.api.SimpleRequestListener;
import com.tony.iweibo.entity.Status;
import com.tony.iweibo.entity.response.StatusTimeLineResponse;
import com.tony.iweibo.utils.TitleBuilder;
import com.tony.iweibo.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private View mView;
    private PullToRefreshListView plv_home;
    private View footView;

    private StatusAdapter mStatusAdapter;
    private List<Status> mStatusList = new ArrayList<Status>();
    private int curPage = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initView();
        loadData(1);
        return mView;
    }

    private void loadData(final int page) {
        IWeiBoApi api = new IWeiBoApi(activity);
        api.statusesHome_timeline(page, new SimpleRequestListener(activity, null) {
            @Override
            public void onComplete(String response) {
                super.onComplete(response);
                if (page == 1) {
                    mStatusList.clear();
                }
                curPage = page;
                addData(new Gson().fromJson(response, StatusTimeLineResponse.class));
            }

            @Override
            public void onAllDone() {
                super.onAllDone();
                plv_home.onRefreshComplete();
            }
        });
    }

    private void addData(StatusTimeLineResponse resBean) {
        for (Status status : resBean.getStatuses()) {
            if (!mStatusList.contains(status)) {
                mStatusList.add(status);
            }
        }
        mStatusAdapter.notifyDataSetChanged();
        if (curPage < resBean.getTotal_number()) {
            addFootView(plv_home, footView);
        } else {
            removeFootView(plv_home, footView);
        }
    }

    private void addFootView(PullToRefreshListView plv, View footView) {
        ListView lv = plv.getRefreshableView();
        if (lv.getFooterViewsCount() == 1) {
            lv.addFooterView(footView);
        }
    }

    private void removeFootView(PullToRefreshListView plv, View footView) {
        ListView lv = plv.getRefreshableView();
        if (lv.getFooterViewsCount() > 1) {
            lv.removeFooterView(footView);
        }
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
        plv_home = (PullToRefreshListView) mView.findViewById(R.id.plv_home);
        mStatusAdapter = new StatusAdapter(activity, mStatusList);
        plv_home.setAdapter(mStatusAdapter);
        plv_home.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                loadData(1);
            }
        });
        plv_home.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                loadData(curPage + 1);
            }
        });
        footView = View.inflate(activity, R.layout.footview_loading, null);
    }
}

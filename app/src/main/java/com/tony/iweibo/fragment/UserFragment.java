package com.tony.iweibo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tony.iweibo.BaseFragment;
import com.tony.iweibo.R;

public class UserFragment extends BaseFragment {
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = View.inflate(activity,R.layout.fragment_user,null);
        return mView;
    }
}

package com.tony.iweibo;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import com.tony.iweibo.activity.MainActivity;

/**
 * Created by tonycheng on 2015/11/17.
 */
public class BaseFragment extends Fragment {
    protected MainActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
    }

    protected void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(activity, tarActivity);
        startActivity(intent);
    }
}

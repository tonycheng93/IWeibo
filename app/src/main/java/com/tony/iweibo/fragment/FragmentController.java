package com.tony.iweibo.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * Created by tonycheng on 2015/11/17.
 */
public class FragmentController {

    private int containerId;
    private FragmentManager mFragmentManager;

    private ArrayList<Fragment> mFragments;
    private static FragmentController ourInstance;

    public static FragmentController getInstance(FragmentActivity activity,int containerId) {
        if (ourInstance == null){
            ourInstance = new FragmentController(activity,containerId);
        }
        return ourInstance;
    }

    private FragmentController(FragmentActivity activity,int containerId) {
        this.containerId = containerId;
        mFragmentManager = activity.getSupportFragmentManager();
        initFragment();
    }

    private void initFragment() {
        mFragments = new ArrayList<Fragment>();
        mFragments.add(new HomeFragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new SearchFragment());
        mFragments.add(new UserFragment());

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        for (Fragment fragment:mFragments){
            ft.add(containerId,fragment);
        }
        ft.commit();
    }

    public void showFragment(int position){
        hideFragment();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Fragment fragment = mFragments.get(position);
        ft.show(fragment);
        ft.commit();
    }

    public void hideFragment(){
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        for (Fragment fragment:mFragments){
            if (fragment != null){
                ft.hide(fragment);
            }
        }
        ft.commit();
    }

    public Fragment getFragment(int position){
        return mFragments.get(position);
    }
}

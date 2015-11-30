package com.tony.iweibo.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 * Created by tonycheng on 2015/11/28.
 */
public class Pull2RefreshScrollView extends PullToRefreshScrollView{
    public Pull2RefreshScrollView(Context context) {
        super(context);
    }

    public Pull2RefreshScrollView(Context context, Mode mode, AnimationStyle style) {
        super(context, mode, style);
    }

    public Pull2RefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Pull2RefreshScrollView(Context context, Mode mode) {
        super(context, mode);
    }

    private OnScrollChangeListener mOnScrollChangeListener;

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        mOnScrollChangeListener = onScrollChangeListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (mOnScrollChangeListener != null){
            mOnScrollChangeListener.onScrollChanged(l,t,oldl,oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public static interface OnScrollChangeListener{
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}

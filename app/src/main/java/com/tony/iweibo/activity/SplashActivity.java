package com.tony.iweibo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.tony.iweibo.BaseActivity;
import com.tony.iweibo.R;
import com.tony.iweibo.constants.AccessTokenKeeper;

/**
 * Created by tonycheng on 2015/11/16.
 */
public class SplashActivity extends BaseActivity {

    private static final int WHAT_INTENT2LOGIN = 0;
    private static final int WHAT_INTENT2MAIN = 1;
    private static final int SPLASH_DUR_TIME = 1 * 1000;
    private Oauth2AccessToken mAccessToken;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_INTENT2LOGIN:
                    intent2Activity(LoginActivity.class);
                    finish();
                    break;
                case WHAT_INTENT2MAIN:
                    intent2Activity(MainActivity.class);
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        if (mAccessToken.isSessionValid()) {
            mHandler.sendEmptyMessageDelayed(WHAT_INTENT2LOGIN, SPLASH_DUR_TIME);
        } else {
            mHandler.sendEmptyMessageDelayed(WHAT_INTENT2MAIN, SPLASH_DUR_TIME);
        }
    }
}

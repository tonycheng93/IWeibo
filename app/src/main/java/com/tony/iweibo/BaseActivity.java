package com.tony.iweibo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.tony.iweibo.constants.CommonConstants;
import com.tony.iweibo.utils.Logger;
import com.tony.iweibo.utils.ToastUtils;



/**
 * Created by tonycheng on 2015/11/12.
 */
public class BaseActivity extends Activity {

    protected String TAG;
    protected BaseApplication mApplication;
    protected SharedPreferences sp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = this.getClass().getSimpleName();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mApplication = (BaseApplication) getApplication();
        sp = getSharedPreferences(CommonConstants.SP_NAME,MODE_PRIVATE);
    }

    protected void intent2Activity(Class<? extends Activity> tarActivity){
        Intent intent = new Intent(this,tarActivity);
        startActivity(intent);
    }

    protected void showToast(String msg){
        ToastUtils.showToast(this,msg, Toast.LENGTH_SHORT);
    }

    protected void showLog(String msg){
        Logger.show(TAG,msg);
    }
}

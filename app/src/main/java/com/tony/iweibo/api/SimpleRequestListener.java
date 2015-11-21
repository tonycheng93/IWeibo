package com.tony.iweibo.api;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.tony.iweibo.utils.Logger;
import com.tony.iweibo.utils.ToastUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by tonycheng on 2015/11/21.
 */
public class SimpleRequestListener implements RequestListener {

    private Context context;
    private Dialog progressDialog;

    public SimpleRequestListener(Context context, Dialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
    }

    @Override
    public void onComplete(String response) {
        onAllDone();
        Logger.show("REQUEST onComplete", response);
    }

    @Override
    public void onComplete4binary(ByteArrayOutputStream responseOS) {
        onAllDone();
        Logger.show("REQUEST onComplete4binary", responseOS.size() + "");
    }

    @Override
    public void onIOException(IOException e) {
        onAllDone();
        ToastUtils.showToast(context, e.getMessage(), Toast.LENGTH_LONG);
        Logger.show("REQUEST onIOException", e.toString());
    }

    @Override
    public void onError(WeiboException e) {
        onAllDone();
        ToastUtils.showToast(context, e.getMessage(), Toast.LENGTH_LONG);
        Logger.show("REQUEST onError", e.toString());
    }

    public void onAllDone() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}

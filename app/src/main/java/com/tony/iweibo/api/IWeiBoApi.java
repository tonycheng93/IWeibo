package com.tony.iweibo.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboParameters;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.legacy.WeiboAPI;
import com.tony.iweibo.constants.AccessTokenKeeper;
import com.tony.iweibo.constants.URLs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by tonycheng on 2015/11/20.
 */
public class IWeiBoApi extends WeiboAPI {

    private Handler mainLooperHandler = new Handler(Looper.getMainLooper());

    /**
     * 构造函数，使用各个API接口提供的服务前必须先获取Oauth2AccessToken
     *
     * @param oauth2AccessToken
     */
    public IWeiBoApi(Oauth2AccessToken oauth2AccessToken) {
        super(oauth2AccessToken);
    }

    public IWeiBoApi(Context context) {
        this(AccessTokenKeeper.readAccessToken(context));
    }

    public void requestInMainLooper(String url, WeiboParameters parameters,
                                    String httpMethod, final RequestListener listener) {
        request(url, parameters, httpMethod, new RequestListener() {
            @Override
            public void onComplete(final String response) {
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onComplete(response);
                    }
                });
            }

            @Override
            public void onComplete4binary(final ByteArrayOutputStream responseOS) {
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onComplete4binary(responseOS);
                    }
                });
            }

            @Override
            public void onIOException(final IOException e) {
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onIOException(e);
                    }
                });
            }

            @Override
            public void onError(final WeiboException e) {
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onError(e);
                    }
                });
            }
        });
    }

    @Override
    protected void request(String url, WeiboParameters params, String httpMethod, RequestListener listener) {
        super.request(url, params, httpMethod, listener);
    }

    public void statusesHome_timeline(long page, RequestListener listener) {
        WeiboParameters parameters = new WeiboParameters();
        parameters.add("page", page);
        requestInMainLooper(URLs.statusesHome_timeline, parameters, HTTPMETHOD_GET, listener);
    }
}

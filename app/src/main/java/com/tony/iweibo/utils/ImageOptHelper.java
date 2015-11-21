package com.tony.iweibo.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tony.iweibo.R;

/**
 * Created by tonycheng on 2015/11/21.
 */
public class ImageOptHelper {

    public static DisplayImageOptions getImgOptions() {
        DisplayImageOptions imgOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .cacheInMemory()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showStubImage(R.mipmap.timeline_image_loading)
                .showImageForEmptyUri(R.mipmap.timeline_image_loading)
                .showImageOnFail(R.mipmap.timeline_image_failure)
                .build();
        return imgOptions;
    }
    public static DisplayImageOptions getAvatarOptions() {
        DisplayImageOptions	avatarOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .cacheInMemory()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showStubImage(R.mipmap.avatar_default)
                .showImageForEmptyUri(R.mipmap.avatar_default)
                .showImageOnFail(R.mipmap.avatar_default)
                .displayer(new RoundedBitmapDisplayer(999))
                .build();
        return avatarOptions;
    }


    public static DisplayImageOptions getCornerOptions(int cornerRadiusPixels) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .cacheInMemory()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showStubImage(R.mipmap.timeline_image_loading)
                .showImageForEmptyUri(R.mipmap.timeline_image_loading)
                .showImageOnFail(R.mipmap.timeline_image_loading)
                .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels)).build();
        return options;
    }
}

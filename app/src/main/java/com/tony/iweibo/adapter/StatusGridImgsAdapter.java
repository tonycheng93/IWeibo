package com.tony.iweibo.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tony.iweibo.R;
import com.tony.iweibo.entity.PicUrls;

import java.util.ArrayList;

/**
 * Created by tonycheng on 2015/11/21.
 */
public class StatusGridImgsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<PicUrls> datas;
    private ImageLoader mImageLoader;

    public StatusGridImgsAdapter(Context context, ArrayList<PicUrls> datas) {
        this.mContext = context;
        this.datas = datas;
        mImageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_grid_image, null);
            viewHolder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GridView gv = (GridView) parent;
        int horizontalSpacing = gv.getHorizontalSpacing();
        int numColumns = gv.getNumColumns();
        int itemWidth = (gv.getWidth() - (numColumns - 1) * horizontalSpacing
                - gv.getPaddingLeft() - gv.getPaddingRight()) / numColumns;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(itemWidth, itemWidth);
        viewHolder.iv_image.setLayoutParams(params);

        PicUrls urls = (PicUrls) getItem(position);
        mImageLoader.displayImage(urls.getThumbnail_pic(), viewHolder.iv_image);
        return convertView;
    }

    public static class ViewHolder {
        public ImageView iv_image;
    }
}

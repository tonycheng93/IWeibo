package com.tony.iweibo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.tony.iweibo.R;
import com.tony.iweibo.utils.EmotionUtils;

import java.util.List;

/**
 * Created by tonycheng on 2015/11/30.
 */
public class EmotionGvAdapter extends BaseAdapter {

    private Context context;
    private List<String> emotionNames;
    private int itemWidth;

    public EmotionGvAdapter(List<String> emotionNames, int itemWidth, Context context) {
        this.emotionNames = emotionNames;
        this.itemWidth = itemWidth;
        this.context = context;
    }

    @Override
    public int getCount() {
        return emotionNames.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return emotionNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv = new ImageView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(itemWidth,itemWidth);
        iv.setPadding(itemWidth / 8, itemWidth / 8, itemWidth / 8, itemWidth / 8);
        iv.setLayoutParams(params);

        if (position == getCount() - 1){
            iv.setImageResource(R.drawable.emotion_delete_icon);
        }else {
            String emotionName = emotionNames.get(position);
            iv.setImageResource(EmotionUtils.getImgByName(emotionName));
        }
        return iv;
    }
}

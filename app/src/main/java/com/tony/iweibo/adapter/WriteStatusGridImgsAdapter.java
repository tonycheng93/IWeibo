package com.tony.iweibo.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tony.iweibo.R;

import java.util.List;

/**
 * Created by tonycheng on 2015/11/30.
 */
public class WriteStatusGridImgsAdapter extends BaseAdapter {
    private Context context;
    private List<Uri> datas;
    private GridView gv;

    public WriteStatusGridImgsAdapter(GridView gv, List<Uri> datas, Context context) {
        this.gv = gv;
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size() > 0 ? datas.size() + 1 : 0;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_grid_image, null);
            viewHolder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.iv_delete_image = (ImageView) convertView.findViewById(R.id.iv_delete_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int horizontalSpacing = gv.getHorizontalSpacing();
        int width = (gv.getWidth() - horizontalSpacing * 2
                - gv.getPaddingRight() - gv.getPaddingLeft()) / 3;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width,width);
        viewHolder.iv_image.setLayoutParams(params);
        if (position < getCount() - 1){
            //set data
            Uri item = (Uri) getItem(position);
            viewHolder.iv_image.setImageURI(item);

            viewHolder.iv_delete_image.setVisibility(View.VISIBLE);
            viewHolder.iv_delete_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datas.remove(position);
                    notifyDataSetChanged();
                }
            });
        }else {
            viewHolder.iv_image.setImageResource(R.drawable.compose_pic_add_more);
            viewHolder.iv_delete_image.setVisibility(View.GONE);
        }
        return convertView;
    }

    public static class ViewHolder {
        private ImageView iv_image;
        private ImageView iv_delete_image;
    }
}

package com.tony.iweibo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tony.iweibo.R;
import com.tony.iweibo.entity.PicUrls;
import com.tony.iweibo.entity.Status;
import com.tony.iweibo.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonycheng on 2015/11/21.
 */
public class StatusAdapter extends BaseAdapter {
    private Context mContext;
    private List<Status> datas;
    private ImageLoader mImageLoader;

    public StatusAdapter(Context context, List<Status> datas) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_status, null);
            viewHolder.ll_card_content = (LinearLayout) convertView
                    .findViewById(R.id.ll_card_content);
            viewHolder.iv_avatar = (ImageView) convertView
                    .findViewById(R.id.iv_avatar);
            viewHolder.rl_content = (RelativeLayout) convertView
                    .findViewById(R.id.rl_content);
            viewHolder.tv_subhead = (TextView) convertView
                    .findViewById(R.id.tv_subhead);
            viewHolder.tv_caption = (TextView) convertView
                    .findViewById(R.id.tv_caption);

            viewHolder.tv_content = (TextView) convertView
                    .findViewById(R.id.tv_content);
            viewHolder.include_status_image = (FrameLayout) convertView
                    .findViewById(R.id.include_status_image);
            viewHolder.gv_images = (GridView) viewHolder.include_status_image
                    .findViewById(R.id.gv_images);
            viewHolder.iv_image = (ImageView) viewHolder.include_status_image
                    .findViewById(R.id.iv_image);

            viewHolder.include_retweeted_status = (LinearLayout) convertView
                    .findViewById(R.id.include_retweeted_status);
            viewHolder.tv_retweeted_content = (TextView) viewHolder.include_retweeted_status
                    .findViewById(R.id.tv_retweeted_content);
            viewHolder.include_retweeted_status_image = (FrameLayout) viewHolder.include_retweeted_status
                    .findViewById(R.id.include_status_image);
            viewHolder.gv_retweeted_images = (GridView) viewHolder.include_retweeted_status_image
                    .findViewById(R.id.gv_images);
            viewHolder.iv_retweeted_image = (ImageView) viewHolder.include_retweeted_status_image
                    .findViewById(R.id.iv_image);

            viewHolder.ll_share_bottom = (LinearLayout) convertView
                    .findViewById(R.id.ll_share_bottom);
            viewHolder.iv_share_bottom = (ImageView) convertView
                    .findViewById(R.id.iv_share_bottom);
            viewHolder.tv_share_bottom = (TextView) convertView
                    .findViewById(R.id.tv_share_bottom);
            viewHolder.ll_comment_bottom = (LinearLayout) convertView
                    .findViewById(R.id.ll_comment_bottom);
            viewHolder.iv_comment_bottom = (ImageView) convertView
                    .findViewById(R.id.iv_comment_bottom);
            viewHolder.tv_comment_bottom = (TextView) convertView
                    .findViewById(R.id.tv_comment_bottom);
            viewHolder.ll_like_bottom = (LinearLayout) convertView
                    .findViewById(R.id.ll_like_bottom);
            viewHolder.iv_like_bottom = (ImageView) convertView
                    .findViewById(R.id.iv_like_bottom);
            viewHolder.tv_like_bottom = (TextView) convertView
                    .findViewById(R.id.tv_like_bottom);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //bind data
        final Status status = (Status) getItem(position);
        User user = status.getUser();
        mImageLoader.displayImage(user.getProfile_image_url(), viewHolder.iv_avatar);
        viewHolder.tv_subhead.setText(user.getName());
        viewHolder.tv_caption.setText(status.getCreated_at()
                + " 来自 " + status.getSource());
        viewHolder.tv_content.setText(status.getText());

        setImages(status, viewHolder.include_status_image, viewHolder.gv_images, viewHolder.iv_image);

        Status retweeted_status = status.getRetweeted_status();
        if (retweeted_status != null) {
            User retUser = retweeted_status.getUser();

            viewHolder.include_retweeted_status.setVisibility(View.VISIBLE);
            viewHolder.tv_retweeted_content.setText("@" + retUser.getName() + ":"
                    + retweeted_status.getText());

            setImages(retweeted_status,
                    viewHolder.include_retweeted_status_image,
                    viewHolder.gv_retweeted_images, viewHolder.iv_retweeted_image);
        } else {
            viewHolder.include_retweeted_status.setVisibility(View.GONE);
        }

        viewHolder.tv_share_bottom.setText(status.getReposts_count() == 0 ?
                "转发" : status.getReposts_count() + "");

        viewHolder.tv_comment_bottom.setText(status.getComments_count() == 0 ?
                "评论" : status.getComments_count() + "");

        viewHolder.tv_like_bottom.setText(status.getAttitudes_count() == 0 ?
                "赞" : status.getAttitudes_count() + "");
        return convertView;
    }

    private void setImages(Status status, FrameLayout imgContaniner,
                           GridView gv_images, ImageView iv_image) {
        ArrayList<PicUrls> pic_urls = status.getPic_urls();
        String thumbnail_pic = status.getThumbnail_pic();
        if (pic_urls != null && pic_urls.size() > 1) {
            imgContaniner.setVisibility(View.VISIBLE);
            gv_images.setVisibility(View.VISIBLE);
            iv_image.setVisibility(View.GONE);

            StatusGridImgsAdapter gvAdapter = new StatusGridImgsAdapter(mContext, pic_urls);
            gv_images.setAdapter(gvAdapter);
        } else if (thumbnail_pic != null) {
            imgContaniner.setVisibility(View.VISIBLE);
            gv_images.setVisibility(View.GONE);
            iv_image.setVisibility(View.VISIBLE);

            mImageLoader.displayImage(thumbnail_pic, iv_image);
        } else {
            imgContaniner.setVisibility(View.GONE);
        }
    }

    public static class ViewHolder {
        public LinearLayout ll_card_content;
        public ImageView iv_avatar;
        public RelativeLayout rl_content;
        public TextView tv_subhead;
        public TextView tv_caption;

        public TextView tv_content;
        public FrameLayout include_status_image;
        public GridView gv_images;
        public ImageView iv_image;

        public LinearLayout include_retweeted_status;
        public TextView tv_retweeted_content;
        public FrameLayout include_retweeted_status_image;
        public GridView gv_retweeted_images;
        public ImageView iv_retweeted_image;

        public LinearLayout ll_share_bottom;
        public ImageView iv_share_bottom;
        public TextView tv_share_bottom;
        public LinearLayout ll_comment_bottom;
        public ImageView iv_comment_bottom;
        public TextView tv_comment_bottom;
        public LinearLayout ll_like_bottom;
        public ImageView iv_like_bottom;
        public TextView tv_like_bottom;
    }
}

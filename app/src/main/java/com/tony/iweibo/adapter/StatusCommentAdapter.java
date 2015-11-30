package com.tony.iweibo.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tony.iweibo.R;
import com.tony.iweibo.entity.Comment;
import com.tony.iweibo.entity.User;
import com.tony.iweibo.utils.DateUtils;
import com.tony.iweibo.utils.ImageOptHelper;
import com.tony.iweibo.utils.StringUtils;
import com.tony.iweibo.utils.ToastUtils;

import java.util.List;

/**
 * Created by tonycheng on 2015/11/28.
 */
public class StatusCommentAdapter extends BaseAdapter {
    private Context mContext;
    private List<Comment> mComments;
    private ImageLoader mImageLoader;

    public StatusCommentAdapter(Context context, List<Comment> comments) {
        mContext = context;
        mComments = comments;
        mImageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return mComments.size();
    }

    @Override
    public Object getItem(int position) {
        return mComments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_comment, null);
            viewHolder = new ViewHolder();
            viewHolder.ll_comments = (LinearLayout) convertView.findViewById(R.id.ll_comments);
            viewHolder.iv_avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
            viewHolder.tv_subhead = (TextView) convertView.findViewById(R.id.tv_subhead);
            viewHolder.tv_caption = (TextView) convertView.findViewById(R.id.tv_caption);
            viewHolder.tv_comment = (TextView) convertView.findViewById(R.id.tv_comment);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Comment comment = (Comment) getItem(position);
        User user = comment.getUser();
        if (user != null) {
            mImageLoader.displayImage(user.getProfile_image_url(), viewHolder.iv_avatar, ImageOptHelper.getAvatarOptions());
            viewHolder.tv_subhead.setText(user.getName());
            viewHolder.tv_caption.setText(DateUtils.getShortTime(comment.getCreated_at()));
            SpannableString weiboContent = StringUtils.getWeiboContent(mContext, viewHolder.tv_comment, comment.getText());
            viewHolder.tv_comment.setText(weiboContent);
            viewHolder.ll_comments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtils.showToast(mContext, "回复评论", Toast.LENGTH_LONG);
                }
            });
        }
        return convertView;
    }

    public static class ViewHolder {
        private LinearLayout ll_comments;
        private ImageView iv_avatar;
        private TextView tv_subhead;
        private TextView tv_caption;
        private TextView tv_comment;
    }
}

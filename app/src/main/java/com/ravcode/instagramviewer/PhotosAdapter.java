package com.ravcode.instagramviewer;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ravi on 9/13/14.
 */
public class PhotosAdapter extends ArrayAdapter<Photo> {

    private static class ViewHolder {
        ImageView userProfilePictureView;
        TextView usernameView;
        TextView createdTimeView;
        ImageView photoView;
        TextView likesView;
        TextView captionView;
        TextView commentsView;
    }

    public PhotosAdapter(Context context, List<Photo> photos) {
        super(context, R.layout.photo_view, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Photo photo = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.photo_view, parent, false);
            viewHolder.userProfilePictureView = (ImageView)convertView.findViewById(R.id.ivUserProfilePhoto);
            viewHolder.usernameView = (TextView)convertView.findViewById(R.id.tvUsername);
            viewHolder.createdTimeView = (TextView)convertView.findViewById(R.id.tvCreatedTime);
            viewHolder.photoView = (ImageView)convertView.findViewById(R.id.ivPhoto);
            viewHolder.likesView = (TextView)convertView.findViewById(R.id.tvLikeCount);
            viewHolder.captionView = (TextView)convertView.findViewById(R.id.tvCaption);
            viewHolder.commentsView = (TextView)convertView.findViewById(R.id.tvComments);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // User profile picture
        if (photo.user.profileURL != null) {
            Picasso.with(getContext()).load(photo.user.profileURL).placeholder(R.drawable.user_placeholder).into(viewHolder.userProfilePictureView);
        }
        else {
            viewHolder.userProfilePictureView.setImageResource(R.drawable.user_placeholder);
        }

        // Username
        viewHolder.usernameView.setText("@" + photo.user.username);

        // Created time
        viewHolder.createdTimeView.setText(DateUtils.getRelativeTimeSpanString(photo.createdTime * 1000, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS));

        // Actual photo
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        // Calculate new width and height while keeping the same aspect ratio
        int width = dm.widthPixels;
        int height = width * photo.imageHeight / photo.imageWidth;

        // Reset height and image source
        viewHolder.photoView.getLayoutParams().height = height;
        viewHolder.photoView.setImageResource(0);
        Picasso.with(getContext()).load(photo.imageURL).resize(width, height).into(viewHolder.photoView);

        // Likes
        viewHolder.likesView.setText(photo.likesCount + " likes");

        // Caption
        if (photo.caption != null) {
            viewHolder.captionView.setText(photo.caption);
            viewHolder.captionView.setVisibility(View.VISIBLE);
        }
        else {
            viewHolder.captionView.setVisibility(View.GONE);
        }

        // Comments
        if (photo.comments.size() > 0) {
            StringBuilder commentsHTML = new StringBuilder();
            for (Comment comment : photo.comments) {
                if (commentsHTML.length() != 0) {
                    commentsHTML.append("<br/>");
                }
                commentsHTML.append("<font color=\"#134169\">@");
                commentsHTML.append(comment.user.username);
                commentsHTML.append("</font> ");
                commentsHTML.append(comment.text);
            }

            Log.i("INFO", commentsHTML.toString());
            viewHolder.commentsView.setText(Html.fromHtml(commentsHTML.toString()));
            viewHolder.commentsView.setVisibility(View.VISIBLE);
        }
        else {
            viewHolder.commentsView.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
}

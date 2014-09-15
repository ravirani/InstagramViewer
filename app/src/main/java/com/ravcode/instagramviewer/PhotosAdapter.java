package com.ravcode.instagramviewer;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
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
        ImageView photoView;
        TextView usernameView;
        TextView captionView;
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
            viewHolder.photoView = (ImageView)convertView.findViewById(R.id.ivPhoto);
            viewHolder.usernameView = (TextView)convertView.findViewById(R.id.tvUsername);
            viewHolder.captionView = (TextView)convertView.findViewById(R.id.tvCaption);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.usernameView.setText("@" + photo.username);
        if (photo.caption != null) {
            viewHolder.captionView.setText(photo.caption);
            viewHolder.captionView.setVisibility(View.VISIBLE);
        }
        else {
            viewHolder.captionView.setVisibility(View.GONE);
        }

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        // Calculate new width and height while keeping the same aspect ratio
        int width = dm.widthPixels;
        int height = width * photo.imageHeight / photo.imageWidth;

        // Reset height and image source
        viewHolder.photoView.getLayoutParams().height = height;
        viewHolder.photoView.setImageResource(0);
        Picasso.with(getContext()).load(photo.imageURL).resize(width, height).into(viewHolder.photoView);
        return convertView;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
}

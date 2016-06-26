package com.kashkash.applicaster.applicaster.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kashkash.applicaster.applicaster.api.TwitterMedia;
import com.squareup.picasso.Picasso;
import com.kashkash.applicaster.applicaster.R;
import com.kashkash.applicaster.applicaster.api.TweetList;

/**
 * Created by tsahkashkash on 25/06/16.
 */

public class TweetAdapter extends BaseAdapter {

    private Context mContext;
    private TweetList tweetList;

    public WindowManager wm;
    public Display display;
    public DisplayMetrics metrics;


    public TweetAdapter(Context mContext, TweetList tweetList) {
        this.mContext = mContext;
        this.tweetList = tweetList;
        this.wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        this.display = wm.getDefaultDisplay();
        this.metrics = new DisplayMetrics();
        display.getMetrics(metrics);
    }

    public void setTweetList(TweetList tweetList) {
        this.tweetList = tweetList;
    }

    @Override
    public int getCount() {
        if (tweetList.tweets != null) {
            return tweetList.tweets.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return null; // we don't need it now
    }

    @Override
    public long getItemId(int position) {
        return 0; // we don't need it now
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.row_tweet, parent, false);
            holder = new ViewHolder();
            holder.textTweet = (TextView) row.findViewById(R.id.text_tweet);
            holder.textUser = (TextView) row.findViewById(R.id.text_user);
            holder.imageLogo = (ImageView) row.findViewById(R.id.image_user_logo);
            holder.tweetMedia = (ImageView) row.findViewById(R.id.tweet_image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        holder.textTweet.setText(tweetList.tweets.get(position).text);
        holder.textUser.setText(tweetList.tweets.get(position).user.name);
        Picasso.with(mContext).load(tweetList.tweets.get(position).user.profileImageUrl).into(holder.imageLogo);
        if(tweetList.tweets.get(position).tweetEntities.media != null) {
            this.updateTextViewSize(holder);
//            Log.d("image url",tweetList.tweets.get(position).tweetEntities.media.get(0).mediaUrl);
            Picasso.with(mContext).load(tweetList.tweets.get(position).tweetEntities.media.get(0).mediaUrl).into(holder.tweetMedia);
//
//            holder.tweetMedia.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int userImagewidth = (int) (48 * Resources.getSystem().getDisplayMetrics().density);//48dp Predefined in the xml layout
//                    Toast.makeText(mContext, "Image Tapped"+view.getId(), Toast.LENGTH_LONG).show();
//                    ResizeAnimation animation = new ResizeAnimation(holder.tweetMedia,userImagewidth,userImagewidth,Resources.getSystem().getDisplayMetrics().widthPixels,Resources.getSystem().getDisplayMetrics().heightPixels);
//                    Transformation transformation = new Transformation();
//                    animation.applyTransformation(1200,transformation);
//
//                }
//            });

        }

        return row;
    }

    static class ViewHolder {
        TextView textTweet;
        TextView textUser;
        ImageView imageLogo;
        ImageView tweetMedia;
    }

    public void updateTextViewSize (ViewHolder holder){

        ViewGroup.LayoutParams params = holder.textTweet.getLayoutParams();
        int userImagewidth = (int) (48 * Resources.getSystem().getDisplayMetrics().density);//48dp Predefined in the xml layout
        int paddingCalc =  (int) (16 * Resources.getSystem().getDisplayMetrics().density);//4dp(padding from each side of image * 2 images *2 sides = 16)

        params.width = metrics.widthPixels-((userImagewidth*2)-paddingCalc);

        holder.textTweet.setLayoutParams(params);
    }

}
package com.kashkash.applicaster.applicaster;

import android.app.ListFragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kashkash.applicaster.applicaster.api.Tweet;
import com.kashkash.applicaster.applicaster.api.TwitterEntities;
import com.kashkash.applicaster.applicaster.api.TwitterMedia;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.kashkash.applicaster.applicaster.api.TweetList;
import com.kashkash.applicaster.applicaster.event.*;
import com.kashkash.applicaster.applicaster.util.BusProvider;
import com.kashkash.applicaster.applicaster.util.PrefsController;
import com.kashkash.applicaster.applicaster.util.TweetAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import static com.kashkash.applicaster.applicaster.util.Util.makeToast;

/**
 * Created by tsahkashkash on 25/06/16.
 */

public class SearchResultsFragment extends ListFragment {

    private static final String TAG = SearchResultsFragment.class.getName();
    private Bus mBus;
    private String request;

    private TweetAdapter brandAdapter;

    private TextView textResult;

    public static final String ARG_SEARCH_REQUEST = "request";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_tweets, container, false);
        brandAdapter = new TweetAdapter(getActivity(), new TweetList());
        setListAdapter(brandAdapter);
        request = getArguments().getString(ARG_SEARCH_REQUEST);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getBus().register(this);
        if (TextUtils.isEmpty(PrefsController.getAccessToken(getActivity()))) {
            getBus().post(new TwitterGetTokenEvent());
        } else {
            String token = PrefsController.getAccessToken(getActivity());
            getBus().post(new SearchTweetsEvent(token, request));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        getBus().unregister(this);
    }

    @Subscribe
    public void onTwitterGetTokenOk(TwitterGetTokenEventOk event) {
        getBus().post(new SearchTweetsEvent(PrefsController.getAccessToken(getActivity()), request));
    }

    @Subscribe
    public void onTwitterGetTokenFailed(TwitterGetTokenEventFailed event) {
        makeToast(getActivity(), "Failed to get token");
    }

    @Subscribe
    public void onSearchTweetsEventOk(final SearchTweetsEventOk event) {
        brandAdapter.setTweetList(event.tweetsList);

        for (Tweet tweet : event.tweetsList.tweets){
//            if(){

//            }
//            for(TwitterMedia media: tweet.tweetEntities.media){
//                Log.i("Media Url: ", media.toString());
//            }

        }

//        for (Tweet tweet : event.tweetsList){
//            Log.i("Member name: ", member);
//        }
//        ArrayList list = event.tweetsList.tweets;
//        for (int i =0 ;i<list.length;i++)
//        {
//            Log.v("Array Value","Array Value"+list[i]);
//        }
//        for(Tweet tw : Arra){

//        }

        brandAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onSearchTweetsEventFailed(SearchTweetsEventFailed event) {
        makeToast(getActivity(), "Search of tweets failed");
    }


    // TODO migrate to DI
    private Bus getBus() {
        if (mBus == null) {
            mBus = BusProvider.getInstance();
        }
        return mBus;
    }

    public void setBus(Bus bus) {
        mBus = bus;
    }

}

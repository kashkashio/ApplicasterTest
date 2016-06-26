package com.kashkash.applicaster.applicaster.api;

import android.util.Log;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.kashkash.applicaster.applicaster.TwitterSearchApplication;
import com.kashkash.applicaster.applicaster.event.*;
import com.kashkash.applicaster.applicaster.util.PrefsController;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.io.*;

import static com.kashkash.applicaster.applicaster.util.Util.getBase64String;

/**
 * Created by tsahkashkash on 25/06/16.
 */

public class TwitterServiceProvider {

    private static final String TAG = TwitterServiceProvider.class.getName();

    private TwitterApiService mApi;
    private Bus mBus;

    public TwitterServiceProvider(TwitterApiService api, Bus bus) {
        this.mApi = api;
        this.mBus = bus;
    }

    @Subscribe
    public void onLoadTweets(final SearchTweetsEvent event) {
        mApi.getTweetList("Bearer " + event.twitterToken, event.hashtag, new Callback<TweetList>() {
            @Override
            public void success(TweetList response, Response rawResponse) {
                mBus.post(new SearchTweetsEventOk(response));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.toString(), error);
                mBus.post(new SearchTweetsEventFailed());
            }
        });
    }

    @Subscribe
    public void onGetToken(TwitterGetTokenEvent event) {
        try {
            mApi.getToken("Basic " + getBase64String(ApiConstants.BEARER_TOKEN_CREDENTIALS), "client_credentials", new Callback<TwitterTokenType>() {
                @Override
                public void success(TwitterTokenType token, Response response) {
                    PrefsController.setAccessToken(TwitterSearchApplication.getAppContext(), token.accessToken);
                    PrefsController.setTokenType(TwitterSearchApplication.getAppContext(), token.tokenType);
                    mBus.post(new TwitterGetTokenEventOk());
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e(TAG, error.toString(), error);
                    mBus.post(new TwitterGetTokenEventFailed());
                }
            });
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.toString(), e);
        }
    }

}

package com.kashkash.applicaster.applicaster;

import android.app.Application;
import android.content.Context;
import com.squareup.otto.Bus;
import com.kashkash.applicaster.applicaster.api.ApiConstants;
import com.kashkash.applicaster.applicaster.api.TwitterApiService;
import com.kashkash.applicaster.applicaster.api.TwitterServiceProvider;
import com.kashkash.applicaster.applicaster.util.BusProvider;
import retrofit.RestAdapter;

/**
 * Created by tsahkashkash on 25/06/16.
 */

public class TwitterSearchApplication extends Application{
    private static TwitterSearchApplication mInstance;
    private static Context mAppContext;

    private TwitterServiceProvider mTwitterService;
    private Bus bus = BusProvider.getInstance();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        this.setAppContext(getApplicationContext());
        mTwitterService = new TwitterServiceProvider(buildApi(), bus);
        bus.register(mTwitterService);
        bus.register(this); //listen to "global" events
    }

    private TwitterApiService buildApi() {
        return new RestAdapter.Builder()
                .setEndpoint(ApiConstants.TWITTER_SEARCH_URL)
                .build()
                .create(TwitterApiService.class);
    }

    public static TwitterSearchApplication getInstance(){
        return mInstance;
    }
    public static Context getAppContext() {
        return mAppContext;
    }
    public void setAppContext(Context mAppContext) {
        this.mAppContext = mAppContext;
    }


}
package com.kashkash.applicaster.applicaster.event;
import com.kashkash.applicaster.applicaster.api.TweetList;

/**
 * Created by tsahkashkash on 25/06/16.
 */
public class SearchTweetsEventOk {

    public final TweetList tweetsList;

    public SearchTweetsEventOk(TweetList tweets) {
        this.tweetsList = tweets;
    }

}

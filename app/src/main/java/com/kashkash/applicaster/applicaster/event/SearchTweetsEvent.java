package com.kashkash.applicaster.applicaster.event;

/**
 * Created by tsahkashkash on 25/06/16.
 */
public class SearchTweetsEvent {

    public final String hashtag;
    public final String twitterToken;

    public SearchTweetsEvent(String twitterToken, String hashtag) {
        this.hashtag = hashtag;
        this.twitterToken = twitterToken;
    }

}

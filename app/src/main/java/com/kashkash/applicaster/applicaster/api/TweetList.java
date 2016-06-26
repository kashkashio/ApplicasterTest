package com.kashkash.applicaster.applicaster.api;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
/**
 * Created by tsahkashkash on 25/06/16.
 */
public class TweetList {
    @SerializedName("statuses")
    public ArrayList<Tweet> tweets;
}

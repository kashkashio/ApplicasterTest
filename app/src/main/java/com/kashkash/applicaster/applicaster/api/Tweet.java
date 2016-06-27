package com.kashkash.applicaster.applicaster.api;
import com.google.gson.annotations.SerializedName;


/**
 * Created by tsahkashkash on 25/06/16.
 */
public class Tweet {
    @SerializedName("created_at")
    public String dateCreated;

    @SerializedName("id")
    public String id;

    @SerializedName("text")
    public String text;

    @SerializedName("in_reply_to_status_id")
    public String inReplyToStatusId;

    @SerializedName("in_reply_to_user_id")
    public String inReplyToUserId;

    @SerializedName("in_reply_to_screen_name")
    public String inReplyToScreenName;

    @SerializedName("user")
    public TwitterUser user;

    @SerializedName("entities")
    public TwitterEntities tweetEntities;

    @Override
    public String  toString(){
        return text;
    }
}

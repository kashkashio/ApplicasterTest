package com.kashkash.applicaster.applicaster.api;
import com.google.gson.annotations.SerializedName;
/**
 * Created by tsahkashkash on 25/06/16.
 */
public class TwitterUser {
    @SerializedName("screen_name")
    public String screenName;

    @SerializedName("name")
    public String name;

    @SerializedName("profile_image_url")
    public String profileImageUrl;
}

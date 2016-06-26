package com.kashkash.applicaster.applicaster.api;
import com.google.gson.annotations.SerializedName;
/**
 * Created by tsahkashkash on 25/06/16.
 */
public class TwitterTokenType {

    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("access_token")
    public String accessToken;

}

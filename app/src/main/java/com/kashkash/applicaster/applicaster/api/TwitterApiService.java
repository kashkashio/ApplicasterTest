package com.kashkash.applicaster.applicaster.api;
import retrofit.Callback;
import retrofit.http.*;
/**
 * Created by tsahkashkash on 25/06/16.
 */
public interface TwitterApiService {

    @GET(ApiConstants.TWITTER_HASHTAG_SEARCH_CODE )
    void getTweetList(
            @Header("Authorization") String authorization,
            @Query("q") String hashtag,
            Callback<TweetList> callback
    );

    @FormUrlEncoded
    @POST("/oauth2/token")
    void getToken(
            @Header("Authorization") String authorization,
            @Field("grant_type") String grantType,
            Callback<TwitterTokenType> response
    );

}

package com.example.apitesting;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIclient {

    @GET("posts")
    Call<List<Post>> getPost(@Query("userId") int[] userId);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int commentId);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id")int id,@Body Post post); //completely replace....

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id")int id,@Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}

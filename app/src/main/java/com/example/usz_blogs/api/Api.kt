package com.example.usz_blogs.api

import com.example.usz_blogs.model.PostModel
import com.example.usz_blogs.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface Api {
    @Headers("app-id:62eb8302b58126d4eb42b515")
    @GET("user")
    fun getUsers(): Call<BaseResponse<List<UserModel>>>

    @Headers("app-id:62eb8302b58126d4eb42b515")
    @GET("post")
    fun getPost(): Call<BaseResponse<List<PostModel>>>
    @Headers("app-id:62eb8302b58126d4eb42b515")
    @GET("user/{user_id}/post")
    fun getPostByUser(@Path("user_id") id: String): Call<BaseResponse<List<PostModel>>>
}


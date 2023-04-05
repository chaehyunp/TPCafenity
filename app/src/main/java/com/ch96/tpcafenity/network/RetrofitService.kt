package com.ch96.tpcafenity.network

import com.ch96.tpcafenity.model.UserAccount
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitService {

    @FormUrlEncoded
    @POST("Account/emailAccount.php")
    fun saveEmailAccount(@Field("nick") nick:String, @Field("email") email:String, @Field("password") password:String):Call<UserAccount>

}
package com.ch96.tpcafenity.network

import com.ch96.tpcafenity.model.UserAccount
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PartMap

interface RetrofitService {

    @Multipart
    @POST("Cafenity/emailAccount.php")
    fun saveEmailAccount(@PartMap emailUser : Map<String, String>):Call<String>

}
package com.ch96.tpcafenity.network

import com.ch96.tpcafenity.model.UserAccount
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PartMap

interface RetrofitService {

    //계정 정보 DB에 저장
    @Multipart
    @POST("Cafenity/emailAccount.php")
    fun saveEmailAccount(@PartMap emailUser : Map<String, String>):Call<String>

    //닉네임과 아이디 중복체크를 위해서 서버에서 받아오기
    @GET("Cafenity/emailAccount.php")
    fun getNickAndEmail():Call<MutableList<UserAccount>>

}
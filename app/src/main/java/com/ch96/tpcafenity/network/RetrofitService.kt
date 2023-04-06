package com.ch96.tpcafenity.network

import com.ch96.tpcafenity.model.KakaoSearchPlaceResponse
import com.ch96.tpcafenity.model.NickEmail
import com.ch96.tpcafenity.model.UserAccount
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PartMap
import retrofit2.http.Query

interface RetrofitService {

    //계정 정보 DB에 저장
    @Multipart
    @POST("Cafenity/emailAccount.php")
    fun saveEmailAccount(@PartMap emailUser : Map<String, String>):Call<String>

    //field에 동일 value값 갯수 확인 - nick(닉네임),email(이메일) 중복여부파악
    @GET("Cafenity/checkNickEmail.php")
    fun checkNickEmail(@Query("nick") nick:String, @Query("email") email:String):Call<NickEmail>

    //Kakao 장소 검색 API
    @Headers("Authorization: KakaoAK ddbb92f9f90921d871078f7bed4f5369")
    @GET("/v2/local/search/category.json")
    fun searchPlace(@Query("category_group_code")categoryCode:String, @Query("y")latitude:String, @Query("x")longitude:String):Call<KakaoSearchPlaceResponse>

}
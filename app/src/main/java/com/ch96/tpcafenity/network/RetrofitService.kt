package com.ch96.tpcafenity.network

import com.ch96.tpcafenity.model.CommunityList
import com.ch96.tpcafenity.model.KakaoSearchPlaceResponse
import com.ch96.tpcafenity.model.LoginUserAccount
import com.ch96.tpcafenity.model.LoginUserData
import com.ch96.tpcafenity.model.Place
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Query


interface RetrofitService {

    //회원가입 - 계정 정보 DB에 저장
    @Multipart
    @POST("Cafenity/saveEmailAccount.php")
    fun saveEmailAccount(@PartMap emailUser : Map<String, String>):Call<String>


    //회원가입 - DB에 같은 "닉네임" 있는지 확인
    @FormUrlEncoded
    @POST("Cafenity/checkNick.php")
    fun checkNick(@Field ("nick") nick:String):Call<String>

    //회원가입 - DB에 같은 "이메일" 있는지 확인
    @FormUrlEncoded
    @POST("Cafenity/checkEmail.php")
    fun checkEmail(@Field ("email") email:String):Call<String>

    //로그인 - 입력한 계정 정보가 DB에 있는지 확인, 해당 정보 받아오기
    @Multipart
    @POST("Cafenity/loginEmailAccount.php")
    fun loginEmailAccount(@PartMap emailUser : Map<String, String>):Call<MutableList<LoginUserData>>

    //DB에서 게시글 받아오기
    @GET("Cafenity/getCommunityPosts.php")
    fun getCommunityPosts() : Call<ArrayList<CommunityList>>

    //작성한 게시글 DB에 저장
    @Multipart
    @POST("Cafenity/saveCommunityPost.php")
    fun savePost(@PartMap dataPat : Map<String, String>, @Part filePart : MutableList<MultipartBody.Part>):Call<String>

    //즐겨찾기 DB에 저장
    @Multipart
    @POST("Cafenity/saveMarkedShop.php")
    fun saveMarkedShop(@PartMap markedShop : Map<String, String>):Call<String>

    //DB에서 즐겨찾기 받아오기
    @GET("Cafenity/getMarkedShop.php")
    fun getMarkedShop(@Query("accountNo") accountNo:String):Call<MutableList<Place>>

    //서버 DB에서 특정 즐겨찾기를 삭제
    @GET("Cafenity/deleteMark.php")
    fun deleteMark(@Query("accountNo") accountNo:String?, @Query("id") id:String?): Call<String>

    //DB에서 프로필 받아오기
    @GET("Cafenity/getAccountProfile.php")
    fun getAccountProfile(@Query("no") no:String?) : Call<MutableList<LoginUserAccount>>

    //수정한 프로필 DB에 저장
    @Multipart
    @POST("Cafenity/saveEditedAccount.php")
//    fun saveEditedAccount(@PartMap dataPat : Map<String, String>, filePart : MultipartBody.Part?):Call<String>
    fun saveEditedAccount(@PartMap dataPat : Map<String, String>):Call<String>

    //Kakao 장소 검색 API
    @Headers("Authorization: KakaoAK ddbb92f9f90921d871078f7bed4f5369")
    @GET("/v2/local/search/category.json")
    fun searchPlace(@Query("category_group_code")categoryCode:String, @Query("y")latitude:String, @Query("x")longitude:String):Call<KakaoSearchPlaceResponse>


}
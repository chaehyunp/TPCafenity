package com.ch96.tpcafenity.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.adapters.RecyclerShopInfoAdapter
import com.ch96.tpcafenity.adapters.RecyclerShopSearchAdapter
import com.ch96.tpcafenity.databinding.ActivitySearchBinding
import com.ch96.tpcafenity.fragments.TabListFragment
import com.ch96.tpcafenity.model.KakaoSearchPlaceResponse
import com.ch96.tpcafenity.model.Place
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }

    //검색응답 결과객체 참조변수
    var searchPlaceResponse:KakaoSearchPlaceResponse ?= null

    //카테고리 그룹 코드
    var categoryCode:String = "CE7" //카페

    var lat = ""
    var lng = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lat = intent.getStringExtra("lat") ?: ""
        lng = intent.getStringExtra("lng") ?: ""

        Log.i("what lat", "$lat")
        Log.i("what lng", "$lng")

        binding.tvCancel.setOnClickListener { finish() }

//        searchPlace()
//        Log.i("what res1", "${searchPlaceResponse?.documents}")
//        binding.recycler.adapter = RecyclerShopSearchAdapter(this, searchPlaceResponse?.documents)
//        Log.i("what res2", "${searchPlaceResponse?.documents}")

    }

    override fun onResume() {
        super.onResume()

        searchPlace()
//        binding.recycler.adapter = RecyclerShopSearchAdapter(this, searchPlaceResponse?.documents)
//        Log.i("what res2", "${searchPlaceResponse?.documents}")

    }

    //Kakao 장소 검색 API 파싱 메소드
    private fun searchPlace() {

        //REST API
        val retrofit = RetrofitHelper.getRetrofitInstance(GV.kakaoUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.searchPlace(categoryCode, lat, lng)
            .enqueue(object: Callback<KakaoSearchPlaceResponse> {
                override fun onResponse(
                    call: Call<KakaoSearchPlaceResponse>, response: Response<KakaoSearchPlaceResponse>
                ) {
                    searchPlaceResponse = response.body()
                    Log.i("what res0", "${searchPlaceResponse?.documents}")
                    binding.recycler.adapter = RecyclerShopSearchAdapter(this@SearchActivity, searchPlaceResponse!!.documents)
                }
                override fun onFailure(call: Call<KakaoSearchPlaceResponse>, t: Throwable) {
                    Toast.makeText(this@SearchActivity, "데이터를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
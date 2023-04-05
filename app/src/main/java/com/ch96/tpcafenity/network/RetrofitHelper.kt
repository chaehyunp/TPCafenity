package com.ch96.tpcafenity.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitHelper {

    companion object {
        fun getRetrofitInstance(baseUrl:String):Retrofit {
            val retrofit:Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
            return retrofit
        }
    }
}
package com.ch96.tpcafenity.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.activities.MainActivity
import com.ch96.tpcafenity.adapters.RecyclerMarkedShopAdapter
import com.ch96.tpcafenity.adapters.RecyclerShopInfoAdapter
import com.ch96.tpcafenity.databinding.FragmentInterestsBinding
import com.ch96.tpcafenity.model.LoginUserData
import com.ch96.tpcafenity.model.Place
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InterestsFragment : Fragment() {

    private val binding:FragmentInterestsBinding by lazy { FragmentInterestsBinding.inflate(layoutInflater)}
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }


    fun loadData() {
        //즐겨찾기한 데이터 받아오기
        val retrofit = RetrofitHelper.getRetrofitInstance(GV.baseUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.getMarkedShop(GV.loginUserNo?:"").enqueue(object : Callback<MutableList<Place>>{
            override fun onResponse(
                call: Call<MutableList<Place>>, response: Response<MutableList<Place>>
            ) {
                var res = response.body()
                if(res!!.isEmpty()) binding.layoutNothing.visibility = View.VISIBLE
                else binding.recycler.adapter = RecyclerMarkedShopAdapter(requireContext(), res!!)
            }
            override fun onFailure(call: Call<MutableList<Place>>, t: Throwable) {
                Log.i("what_getMarked_failed","$t")
            }
        })
    }


}
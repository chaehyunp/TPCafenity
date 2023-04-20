package com.ch96.tpcafenity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.adapters.RecyclerMarkedShopAdapter
import com.ch96.tpcafenity.adapters.ReviewsAdapter
import com.ch96.tpcafenity.databinding.FragmentTabMyreviewBinding
import com.ch96.tpcafenity.model.ReviewData
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TabMyreviewFragment : Fragment() {

    val binding:FragmentTabMyreviewBinding by lazy { FragmentTabMyreviewBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun loadData() {
        //DB에 있는 리뷰 데이터 받아오기
        val retrofit = RetrofitHelper.getRetrofitInstance(GV.baseUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)

        retrofitService.getMyReviews(GV.loginUserNo?:"").enqueue(object : Callback<MutableList<ReviewData>> {
            override fun onResponse(
                call: Call<MutableList<ReviewData>>, response: Response<MutableList<ReviewData>>
            ) {
                var res = response.body()
                res?.reverse()
                if(res!!.isEmpty()) binding.layoutNothing.visibility = View.VISIBLE
                else binding.recycler.adapter = ReviewsAdapter(requireContext(), res!!)
            }
            override fun onFailure(call: Call<MutableList<ReviewData>>, t: Throwable) {}
        })
    }

}
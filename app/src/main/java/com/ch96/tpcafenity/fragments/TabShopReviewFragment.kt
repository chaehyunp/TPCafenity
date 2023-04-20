package com.ch96.tpcafenity.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.activities.NewWriteActivity
import com.ch96.tpcafenity.activities.ShopInfoActivity
import com.ch96.tpcafenity.activities.WriteReviewActivity
import com.ch96.tpcafenity.adapters.ListCommunityAdapter
import com.ch96.tpcafenity.adapters.ReviewsAdapter
import com.ch96.tpcafenity.databinding.FragmentTabShopReviewBinding
import com.ch96.tpcafenity.model.CommunityList
import com.ch96.tpcafenity.model.ReviewData
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TabShopReviewFragment : Fragment() {

    private val binding:FragmentTabShopReviewBinding by lazy { FragmentTabShopReviewBinding.inflate(layoutInflater) }
    var shopId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //ShopInfoActivity에서 adapter를 통해 intent가 가져온 값 가져오기
        val sia = requireActivity() as ShopInfoActivity
        shopId = sia.intent.getStringExtra("id") ?:""

        binding.fabWrite.setOnClickListener { clickFabWrite() }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun clickFabWrite() {
        var intent = Intent(activity, WriteReviewActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.putExtra("shopId", shopId)
        startActivity(intent)
    }

    fun loadData() {
        //DB에 있는 리뷰 데이터 받아오기
        val retrofit = RetrofitHelper.getRetrofitInstance(GV.baseUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)

        retrofitService.getReviews(shopId).enqueue(object : Callback<MutableList<ReviewData>> {
            override fun onResponse(
                call: Call<MutableList<ReviewData>>, response: Response<MutableList<ReviewData>>
            ) {
                var res = response.body()
                res?.reverse()
                val adapter = ReviewsAdapter(requireContext(), res!!)
                binding.recycler.adapter = adapter
                binding.tvReviewNum.text = adapter.getReviewNum().toString()
            }
            override fun onFailure(call: Call<MutableList<ReviewData>>, t: Throwable) {}
        })
    }

}
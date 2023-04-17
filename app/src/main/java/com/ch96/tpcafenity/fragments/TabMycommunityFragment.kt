package com.ch96.tpcafenity.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.adapters.ListCommunityAdapter
import com.ch96.tpcafenity.databinding.FragmentTabMycommunityBinding
import com.ch96.tpcafenity.model.CommunityList
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TabMycommunityFragment : Fragment() {

    val binding:FragmentTabMycommunityBinding by lazy { FragmentTabMycommunityBinding.inflate(layoutInflater) }

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
        //DB에 있는 게시글 데이터 받아오기
        val retrofit = RetrofitHelper.getRetrofitInstance(GV.baseUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.getMyCommunityPosts(GV.loginUserNo.toString()).enqueue(object : Callback<ArrayList<CommunityList>> {
            override fun onResponse(
                call: Call<ArrayList<CommunityList>>, response: Response<ArrayList<CommunityList>>
            ) {
                var res = response.body()
                res?.reverse()
                binding.recycler.adapter = ListCommunityAdapter(requireContext(), res!!)
            }
            override fun onFailure(call: Call<ArrayList<CommunityList>>, t: Throwable) {
                Log.i("what_post_failed", "$t")
            }
        })
    }

}
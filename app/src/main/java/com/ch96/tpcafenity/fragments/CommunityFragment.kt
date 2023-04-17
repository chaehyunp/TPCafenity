package com.ch96.tpcafenity.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.activities.NewWriteActivity
import com.ch96.tpcafenity.adapters.ListCommunityAdapter
import com.ch96.tpcafenity.databinding.FragmentCommunityBinding
import com.ch96.tpcafenity.model.CommunityList
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommunityFragment : Fragment() {

    private val binding:FragmentCommunityBinding by lazy { FragmentCommunityBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding.fabWrite.setOnClickListener { clickFabWrite() }

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun clickFabWrite() {
        var intent = Intent(activity, NewWriteActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

    fun loadData() {
        //DB에 있는 게시글 데이터 받아오기
        val retrofit = RetrofitHelper.getRetrofitInstance(GV.baseUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.getCommunityPosts().enqueue(object : Callback<ArrayList<CommunityList>> {
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
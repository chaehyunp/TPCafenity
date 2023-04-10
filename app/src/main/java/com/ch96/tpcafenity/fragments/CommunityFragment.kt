package com.ch96.tpcafenity.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.fragment.app.FragmentManager
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
    var baseUrl:String = "http://testhue96.dothome.co.kr/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //DB에 있는 게시글 데이터 받아오기
        val retrofit = RetrofitHelper.getRetrofitInstance(baseUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.getCommunityPosts().enqueue(object : Callback<MutableList<CommunityList>> {
            override fun onResponse(
                call: Call<MutableList<CommunityList>>,
                response: Response<MutableList<CommunityList>>
            ) {
                var res = response.body()
                Log.i("what_post", "$res")
            }

            override fun onFailure(call: Call<MutableList<CommunityList>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //binding.layoutListInterests.adapter = ListCommunityAdapter(requireContext())

        binding.fabWrite.setOnClickListener { clickFabWrite() }


        return binding.root

    }

    private fun clickFabWrite() {
        var intent = Intent(activity, NewWriteActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

}
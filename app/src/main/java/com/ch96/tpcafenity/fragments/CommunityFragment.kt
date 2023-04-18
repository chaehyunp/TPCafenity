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
import android.widget.TextView
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
    var activeTag = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding.fabWrite.setOnClickListener { clickFabWrite() }
        checkList()
        loadData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //tag - 이야기
        binding.tagTalk.setOnClickListener{
            binding.tagTalk.visibility = View.INVISIBLE
            binding.tagTalkActive.visibility = View.VISIBLE
            checkList()
            loadData()}
        binding.tagTalkActive.setOnClickListener{
            binding.tagTalkActive.visibility = View.INVISIBLE
            binding.tagTalk.visibility = View.VISIBLE
            checkList()
            loadData()}

        //tag - TIP
        binding.tagTip.setOnClickListener {
            binding.tagTip.visibility = View.INVISIBLE
            binding.tagTipActive.visibility = View.VISIBLE
            checkList()
            loadData()}
        binding.tagTipActive.setOnClickListener {
            binding.tagTipActive.visibility = View.INVISIBLE
            binding.tagTip.visibility = View.VISIBLE
            checkList()
            loadData()}

        //tag - review
        binding.tagReview.setOnClickListener {
            binding.tagReview.visibility = View.INVISIBLE
            binding.tagReviewActive.visibility = View.VISIBLE
            checkList()
            loadData()}
        binding.tagReviewActive.setOnClickListener {
            binding.tagReviewActive.visibility = View.INVISIBLE
            binding.tagReview.visibility = View.VISIBLE
            checkList()
            loadData()}

        //tag - recom
        binding.tagRecom.setOnClickListener {
            binding.tagRecom.visibility = View.INVISIBLE
            binding.tagRecomActive.visibility = View.VISIBLE
            checkList()
            loadData()}
        binding.tagRecomActive.setOnClickListener {
            binding.tagRecomActive.visibility = View.INVISIBLE
            binding.tagRecom.visibility = View.VISIBLE
            checkList()
            loadData()}

        //tag - depre
        binding.tagDepre.setOnClickListener {
            binding.tagDepre.visibility = View.INVISIBLE
            binding.tagDepreActive.visibility = View.VISIBLE
            checkList()
            loadData()}
        binding.tagDepreActive.setOnClickListener {
            binding.tagDepreActive.visibility = View.INVISIBLE
            binding.tagDepre.visibility = View.VISIBLE
            checkList()
            loadData()}

        //tag - else
        binding.tagEtc.setOnClickListener {
            binding.tagEtc.visibility = View.INVISIBLE
            binding.tagEtcActive.visibility = View.VISIBLE
            checkList()
            loadData()}
        binding.tagEtcActive.setOnClickListener {
            binding.tagEtcActive.visibility = View.INVISIBLE
            binding.tagEtc.visibility = View.VISIBLE
            checkList()
            loadData()}
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

        Log.i("111", "${activeTag.joinToString(",")}")
        //DB에 있는 게시글 데이터 받아오기
        val retrofit = RetrofitHelper.getRetrofitInstance(GV.baseUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.getCommunityPosts(activeTag.joinToString(",")).enqueue(object : Callback<MutableList<CommunityList>> {
            override fun onResponse(
                call: Call<MutableList<CommunityList>>, response: Response<MutableList<CommunityList>>
            ) {
                var res = response.body()
                res?.reverse()
                val adapter = ListCommunityAdapter(requireContext(), res!!)
                Log.i("what_tag", "$activeTag")

                binding.recycler.adapter = adapter
            }
            override fun onFailure(call: Call<MutableList<CommunityList>>, t: Throwable) {
                Log.i("what_post_failed", "$t")
            }
        })
    }

    fun checkList() {

        var talk = binding.tagTalkActive.text.toString()
        var tip = binding.tagTipActive.text.toString()
        var review = binding.tagReviewActive.text.toString()
        var recom = binding.tagRecomActive.text.toString()
        var depre = binding.tagDepreActive.text.toString()
        var etc = binding.tagEtcActive.text.toString()

        if ( (binding.tagTalkActive.visibility == View.VISIBLE) && !(activeTag.contains(talk)) ) activeTag.add(talk)
        else if ( binding.tagTalkActive.visibility == View.INVISIBLE && (activeTag.contains(talk)) ) activeTag.remove(talk)

        if ( (binding.tagTipActive.visibility == View.VISIBLE) && !(activeTag.contains(tip)) ) activeTag.add(tip)
        else if ( binding.tagTipActive.visibility == View.INVISIBLE && (activeTag.contains(tip)) ) activeTag.remove(tip)

        if ( (binding.tagReviewActive.visibility == View.VISIBLE) && !(activeTag.contains(review)) ) activeTag.add(review)
        else if (binding.tagReviewActive.visibility == View.INVISIBLE && (activeTag.contains(review)) ) activeTag.remove(review)

        if ( (binding.tagRecomActive.visibility == View.VISIBLE) && !(activeTag.contains(recom)) ) activeTag.add(recom)
        else if ( binding.tagRecomActive.visibility == View.INVISIBLE && (activeTag.contains(recom)) ) activeTag.remove(recom)

        if ( (binding.tagDepreActive.visibility == View.VISIBLE) && !(activeTag.contains(depre)) ) activeTag.add(depre)
        else if ( binding.tagDepreActive.visibility == View.INVISIBLE && (activeTag.contains(depre)) ) activeTag.remove(depre)

        if ( (binding.tagEtcActive.visibility == View.VISIBLE) && !(activeTag.contains(etc)) ) activeTag.add(etc)
        else if  (binding.tagEtcActive.visibility == View.INVISIBLE && (activeTag.contains(etc)) ) activeTag.remove(etc)
    }

}
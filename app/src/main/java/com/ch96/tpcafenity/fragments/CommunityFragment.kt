package com.ch96.tpcafenity.fragments

import android.content.Intent
import android.os.Bundle
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


class CommunityFragment : Fragment() {

    private val binding:FragmentCommunityBinding by lazy { FragmentCommunityBinding.inflate(layoutInflater) }

    //임의 데이터
    val items:MutableList<CommunityList> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //임의 데이터
        items.add(CommunityList("아침에 아메리카노 안먹어도 버틸 수 있나요", "가짜커피중독자", "23.03.31" ))
        items.add(CommunityList("아침에 아메리카노 안먹어도 버틸 수 있나요", "가짜커피중독자", "23.03.31" ))
        items.add(CommunityList("아침에 아메리카노 안먹어도 버틸 수 있나요", "가짜커피중독자", "23.03.31" ))
        items.add(CommunityList("아침에 아메리카노 안먹어도 버틸 수 있나요", "가짜커피중독자", "23.03.31" ))
        items.add(CommunityList("아침에 아메리카노 안먹어도 버틸 수 있나요", "가짜커피중독자", "23.03.31" ))
        items.add(CommunityList("아침에 아메리카노 안먹어도 버틸 수 있나요", "가짜커피중독자", "23.03.31" ))
        items.add(CommunityList("아침에 아메리카노 안먹어도 버틸 수 있나요", "가짜커피중독자", "23.03.31" ))
        items.add(CommunityList("아침에 아메리카노 안먹어도 버틸 수 있나요", "가짜커피중독자", "23.03.31" ))
        items.add(CommunityList("아침에 아메리카노 안먹어도 버틸 수 있나요", "가짜커피중독자", "23.03.31" ))


        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.layoutListInterests.adapter = ListCommunityAdapter(requireContext(), items)

        binding.fabWrite.setOnClickListener { clickFabWrite() }


        return binding.root

    }

    private fun clickFabWrite() {
        var intent = Intent(activity, NewWriteActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

}
package com.ch96.tpcafenity.fragments

import android.content.ClipData.Item
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.adapters.RecyclerInterestsAdapter
import com.ch96.tpcafenity.databinding.FragmentInterestsBinding
import com.ch96.tpcafenity.model.ShopInfo


class InterestsFragment : Fragment() {

    private val binding:FragmentInterestsBinding by lazy { FragmentInterestsBinding.inflate(layoutInflater)}

    //임의 데이터
    val items:MutableList<ShopInfo> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //임의 데이터
        items.add(ShopInfo("빽다방 왕십리점", "4.3"))
        items.add(ShopInfo("빽다방 왕십리점", "4.3"))
        items.add(ShopInfo("빽다방 왕십리점", "4.3"))
        items.add(ShopInfo("빽다방 왕십리점", "4.3"))
        items.add(ShopInfo("빽다방 왕십리점", "4.3"))
        items.add(ShopInfo("빽다방 왕십리점", "4.3"))
        items.add(ShopInfo("빽다방 왕십리점", "4.3"))


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.recyclerInterests.adapter = RecyclerInterestsAdapter(requireContext(), items)

        return binding.root
    }

}
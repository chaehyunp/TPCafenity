package com.ch96.tpcafenity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.adapters.RecyclerShopInfoAdapter
import com.ch96.tpcafenity.databinding.FragmentTabListBinding
import com.ch96.tpcafenity.model.ShopInfo

class TabListFragment : Fragment() {

    private val binding:FragmentTabListBinding by lazy { FragmentTabListBinding.inflate(layoutInflater) }

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

        binding.recyclerList.adapter = RecyclerShopInfoAdapter(requireContext(), items)

        return binding .root
    }

}
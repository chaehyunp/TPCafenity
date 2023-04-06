package com.ch96.tpcafenity.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ch96.tpcafenity.activities.MainActivity
import com.ch96.tpcafenity.activities.ShopInfoActivity
import com.ch96.tpcafenity.adapters.RecyclerShopInfoAdapter
import com.ch96.tpcafenity.databinding.FragmentTabListBinding

class TabListFragment : Fragment() {

    private val binding:FragmentTabListBinding by lazy { FragmentTabListBinding.inflate(layoutInflater) }

    //임의 데이터
//    val items:MutableList<ShopInfo> = mutableListOf()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        //임의 데이터
//        items.add(ShopInfo("빽다방 왕십리점", "4.3"))
//        items.add(ShopInfo("빽다방 왕십리점", "4.3"))
//        items.add(ShopInfo("빽다방 왕십리점", "4.3"))
//        items.add(ShopInfo("빽다방 왕십리점", "4.3"))
//        items.add(ShopInfo("빽다방 왕십리점", "4.3"))
//        items.add(ShopInfo("빽다방 왕십리점", "4.3"))
//        items.add(ShopInfo("빽다방 왕십리점", "4.3"))
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //MainActivity에서 받아온 데이터 불러오기
        val mData = requireActivity() as MainActivity
        mData.searchPlaceResponse?.apply {
            binding.recyclerList.adapter = RecyclerShopInfoAdapter(mData, documents)
        }
    }

}
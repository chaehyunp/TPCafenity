package com.ch96.tpcafenity.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ch96.tpcafenity.activities.MainActivity
import com.ch96.tpcafenity.adapters.RecyclerShopInfoAdapter
import com.ch96.tpcafenity.databinding.FragmentTabListBinding

class TabListFragment : Fragment() {

    val binding:FragmentTabListBinding by lazy { FragmentTabListBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("what_mData1","fragment")

        //MainActivity에서 받아온 데이터 불러오기
        val mData : MainActivity = requireActivity() as MainActivity
        mData.searchPlaceResponse?.apply {
            Log.i("what_mData1","${mData}")
            Log.i("what_mData2","${mData.searchPlaceResponse}")
            binding.recyclerList.adapter = RecyclerShopInfoAdapter(requireActivity(), documents)
        }
    }
}
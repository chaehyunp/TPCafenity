package com.ch96.tpcafenity.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ch96.tpcafenity.activities.MainActivity
import com.ch96.tpcafenity.adapters.RecyclerShopInfoAdapter
import com.ch96.tpcafenity.databinding.FragmentTabListBinding

class TabListFragment : Fragment() {

    lateinit var binding:FragmentTabListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //MainActivity에서 받아온 데이터 불러오기
        val mData : MainActivity = requireActivity() as MainActivity
        mData.searchPlaceResponse?.apply {
            binding.recycler.adapter = RecyclerShopInfoAdapter(requireActivity(), documents)
        }

    }
}
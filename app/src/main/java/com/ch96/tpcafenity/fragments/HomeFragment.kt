package com.ch96.tpcafenity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.ch96.tpcafenity.adapters.ViewPagerHomeAdapter
import com.ch96.tpcafenity.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private val binding:FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    private val tabTextList = listOf("리스트로 보기", "지도로 보기")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //네트워크에서 불러올때
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.pager.adapter = ViewPagerHomeAdapter(requireActivity())

        TabLayoutMediator(binding.layoutTab, binding.pager) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()
        return binding.root
    }
}
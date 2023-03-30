package com.ch96.tpcafenity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.adapters.ViewPagerHomeAdapter
import com.ch96.tpcafenity.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    val binding:FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //탭 설정
        binding.layoutTab.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //탭이 선택되었을때
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //탭이 선택되지 않은 상태로 변경되었을때
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //이미 선택된 탭이 다시 선택되었을때
            }

        })

        //ViewPager에 아답터 연결
        binding.pager.adapter = ViewPagerHomeAdapter(this)
        
        TabLayoutMediator(binding.layoutTab, binding.pager) { tab, position ->
            when(position) {
                0 -> tab.text = "리스트로 보기"
                else -> tab.text = "지도로 보기"
            }
        }.attach()
        
    }

}
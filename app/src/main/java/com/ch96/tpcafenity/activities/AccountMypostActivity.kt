package com.ch96.tpcafenity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.adapters.ViewPagerMypostAdapter
import com.ch96.tpcafenity.databinding.ActivityAccountMypostBinding
import com.ch96.tpcafenity.fragments.TabMycommunityFragment
import com.ch96.tpcafenity.fragments.TabMyreviewFragment
import com.google.android.material.tabs.TabLayoutMediator

class AccountMypostActivity : AppCompatActivity() {

    val binding:ActivityAccountMypostBinding by lazy { ActivityAccountMypostBinding.inflate(layoutInflater) }
    private val tabTextList = listOf("커뮤니티", "리뷰")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_action_back)

        //아답터 불러오기
        val mypostAdapter = ViewPagerMypostAdapter(this)

        //아답터에 프래그먼트 add
        mypostAdapter.addFragment(TabMycommunityFragment())
        mypostAdapter.addFragment(TabMyreviewFragment())

        binding.pager.adapter = mypostAdapter
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })

        TabLayoutMediator(binding.layoutTab, binding.pager) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()



    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
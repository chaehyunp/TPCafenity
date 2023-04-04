package com.ch96.tpcafenity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.adapters.ViewPagerShopAdapter
import com.ch96.tpcafenity.databinding.ActivityShopInfoBinding
import com.ch96.tpcafenity.fragments.TabShopDetailFragment
import com.ch96.tpcafenity.fragments.TabShopReviewFragment
import com.google.android.material.tabs.TabLayoutMediator

class ShopInfoActivity : AppCompatActivity() {

    val binding:ActivityShopInfoBinding by lazy { ActivityShopInfoBinding.inflate(layoutInflater) }
    private val tabTextList = listOf("매장정보", "리뷰")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_action_back)

        val shopAdapter = ViewPagerShopAdapter(this)

        shopAdapter.addFragment(TabShopDetailFragment())
        shopAdapter.addFragment(TabShopReviewFragment())
        
        binding.pager.adapter = shopAdapter
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
        
        TabLayoutMediator(binding.layoutTab, binding.pager) { tab, position ->
            tab.text = tabTextList[position]
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
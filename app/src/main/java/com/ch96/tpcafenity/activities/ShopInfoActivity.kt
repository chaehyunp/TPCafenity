package com.ch96.tpcafenity.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.adapters.ViewPagerShopAdapter
import com.ch96.tpcafenity.databinding.ActivityShopInfoBinding
import com.ch96.tpcafenity.fragments.TabShopDetailFragment
import com.ch96.tpcafenity.fragments.TabShopReviewFragment
import com.google.android.material.tabs.TabLayoutMediator

class ShopInfoActivity : AppCompatActivity() {

    val binding:ActivityShopInfoBinding by lazy { ActivityShopInfoBinding.inflate(layoutInflater) }
    private val tabTextList = listOf("매장정보", "카프니티 리뷰")

    //아답터에서 받아온 주소 변수
    var place_url:String ?= null
    var id:String ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_action_back)

        binding.pager.adapter = ViewPagerShopAdapter(this)

        TabLayoutMediator(binding.layoutTab, binding.pager) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()

        id = intent.getStringExtra("id")
        place_url = intent.getStringExtra("place_url")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
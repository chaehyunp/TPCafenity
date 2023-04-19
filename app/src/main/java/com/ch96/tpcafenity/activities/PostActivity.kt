package com.ch96.tpcafenity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.adapters.ViewPagerImageAdapter
import com.ch96.tpcafenity.databinding.ActivityPostBinding
import com.ch96.tpcafenity.fragments.ImageFourFragment
import com.ch96.tpcafenity.fragments.ImageOneFragment
import com.ch96.tpcafenity.fragments.ImageThreeFragment
import com.ch96.tpcafenity.fragments.ImageTwoFragment
import com.ch96.tpcafenity.fragments.ImageZeroFragment

class PostActivity : AppCompatActivity() {

    val binding:ActivityPostBinding by lazy { ActivityPostBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_action_back)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.tvTag.text = intent.getStringExtra("tag")
        binding.tvTitle.text = intent.getStringExtra("title")
        binding.tvNick.text = intent.getStringExtra("nick")
        binding.tvDate.text = intent.getStringExtra("date")
        binding.tvText.text = intent.getStringExtra("text")

        val images = mutableListOf <String>()
        if (intent.getStringExtra("image0") !== "") images.add(intent.getStringExtra("image0")!!)
        if (intent.getStringExtra("image1") !== "") images.add(intent.getStringExtra("image1")!!)
        if (intent.getStringExtra("image2") !== "") images.add(intent.getStringExtra("image2")!!)
        if (intent.getStringExtra("image3") !== "") images.add(intent.getStringExtra("image3")!!)
        if (intent.getStringExtra("image4") !== "") images.add(intent.getStringExtra("image4")!!)

        var fragment0 = ImageZeroFragment()
        var fragment1 = ImageOneFragment()
        var fragment2 = ImageTwoFragment()
        var fragment3 = ImageThreeFragment()
        var fragment4 = ImageFourFragment()

        var fragments = mutableListOf<Fragment>()
        fragments.add(fragment0)
        fragments.add(fragment1)
        fragments.add(fragment2)
        fragments.add(fragment3)
        fragments.add(fragment4)

        binding.pager.adapter = ViewPagerImageAdapter(this, fragments)
        binding.indicator.setViewPager2(binding.pager)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
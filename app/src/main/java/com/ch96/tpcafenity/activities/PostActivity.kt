package com.ch96.tpcafenity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {

    val binding:ActivityPostBinding by lazy { ActivityPostBinding.inflate(layoutInflater) }
    var image0 = ""
    var image1 = ""
    var image2 = ""
    var image3 = ""
    var image4 = ""

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

        image0 = intent.getStringExtra("image0") ?: ""
        image1 = intent.getStringExtra("image1") ?: ""
        image2 = intent.getStringExtra("image2") ?: ""
        image3 = intent.getStringExtra("image3") ?: ""
        image4 = intent.getStringExtra("image4") ?: ""

        //페이저에 아답터 붙이기

        binding.indicator.setViewPager(binding.pager)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
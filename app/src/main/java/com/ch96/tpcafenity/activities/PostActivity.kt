package com.ch96.tpcafenity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.adapters.ViewPagerImageAdapter
import com.ch96.tpcafenity.databinding.ActivityPostBinding

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

        Log.i("imageNum0", "${intent.getStringExtra("image0")}")
        Log.i("imageNum1", "${intent.getStringExtra("image1")}")
        Log.i("imageNum2", "${intent.getStringExtra("image2")}")
        Log.i("imageNum3", "${intent.getStringExtra("image3")}")
        Log.i("imageNum4", "${intent.getStringExtra("image4")}")

        if (intent.getStringExtra("image0") != "") images.add(intent.getStringExtra("image0")!!)
        if (intent.getStringExtra("image1") != "") images.add(intent.getStringExtra("image1")!!)
        if (intent.getStringExtra("image2") != "") images.add(intent.getStringExtra("image2")!!)
        if (intent.getStringExtra("image3") != "") images.add(intent.getStringExtra("image3")!!)
        if (intent.getStringExtra("image4") != "") images.add(intent.getStringExtra("image4")!!)

        Log.i("imageNum_images", "${images}")


        binding.pager.adapter = ViewPagerImageAdapter(this, images)
        binding.indicator.setupWithViewPager(binding.pager, true )

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
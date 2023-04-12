package com.ch96.tpcafenity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ch96.tpcafenity.R
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

        binding.tvTitle.text = intent.getStringExtra("title")
        binding.tvNick.text = intent.getStringExtra("nick")
        binding.tvDate.text = intent.getStringExtra("date")
        binding.tvText.text = intent.getStringExtra("text")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
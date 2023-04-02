package com.ch96.tpcafenity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.databinding.ActivityNewWriteBinding

class NewWriteActivity : AppCompatActivity() {

    val binding:ActivityNewWriteBinding by lazy { ActivityNewWriteBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //툴바 설정
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_action_close)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}
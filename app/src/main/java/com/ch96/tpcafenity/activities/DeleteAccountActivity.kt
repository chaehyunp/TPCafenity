package com.ch96.tpcafenity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.databinding.ActivityDeleteAccountBinding

class DeleteAccountActivity : AppCompatActivity() {

    val binding:ActivityDeleteAccountBinding by lazy { ActivityDeleteAccountBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}
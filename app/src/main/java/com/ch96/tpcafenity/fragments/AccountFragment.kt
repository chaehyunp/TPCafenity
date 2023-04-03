package com.ch96.tpcafenity.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.activities.AccountAlertActivity
import com.ch96.tpcafenity.activities.AccountMypostActivity
import com.ch96.tpcafenity.activities.AccountProfileActivity
import com.ch96.tpcafenity.activities.AccountSettingActivity
import com.ch96.tpcafenity.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

    private val binding: FragmentAccountBinding by lazy { FragmentAccountBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding.layoutProfile.setOnClickListener { clickProfile() }
        binding.layoutMypost.setOnClickListener { clickMypost() }
        binding.layoutAlert.setOnClickListener { clickAlert() }
        binding.layoutSetting.setOnClickListener { clickSetting() }

        return binding.root
    }


    private fun clickProfile(){
        var intent = Intent(activity, AccountProfileActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

    private fun clickMypost() {
        var intent = Intent(activity, AccountMypostActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

    private fun clickAlert() {
        var intent = Intent(activity, AccountAlertActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

    private fun clickSetting() {
        var intent = Intent(activity, AccountSettingActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }
}
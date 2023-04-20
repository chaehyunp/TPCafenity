package com.ch96.tpcafenity.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.ch96.tpcafenity.adapters.ViewPagerHomeAdapter
import com.ch96.tpcafenity.databinding.FragmentHomeBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private val binding:FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val tabTextList = listOf("리스트로 보기", "지도로 보기")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding.pager.run { isUserInputEnabled = false }
        binding.pager.adapter = ViewPagerHomeAdapter(requireActivity())

        TabLayoutMediator(binding.layoutTab, binding.pager) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()

        return binding.root
    }


}
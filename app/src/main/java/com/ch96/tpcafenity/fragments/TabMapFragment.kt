package com.ch96.tpcafenity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.databinding.FragmentTabListBinding
import com.ch96.tpcafenity.databinding.FragmentTabMapBinding

class TabMapFragment : Fragment() {

    lateinit var binding: FragmentTabMapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabMapBinding.inflate(inflater, container, false)
        return binding .root
    }
}
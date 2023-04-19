package com.ch96.tpcafenity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.databinding.FragmentPagerImageBinding

class PagerImageFragment : Fragment() {

    val binding by lazy { FragmentPagerImageBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return binding.root
    }

}
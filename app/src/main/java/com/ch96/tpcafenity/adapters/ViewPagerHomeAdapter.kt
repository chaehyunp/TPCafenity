package com.ch96.tpcafenity.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ch96.tpcafenity.fragments.HomeFragment
import com.ch96.tpcafenity.fragments.TabListFragment
import com.ch96.tpcafenity.fragments.TabMapFragment

class ViewPagerHomeAdapter(fragment: HomeFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabListFragment()
            else -> TabMapFragment()
        }
    }
}
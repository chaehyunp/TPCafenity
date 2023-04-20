package com.ch96.tpcafenity.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ch96.tpcafenity.fragments.TabListFragment
import com.ch96.tpcafenity.fragments.TabMapFragment

class ViewPagerHomeAdapter(fragmentActivity:FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount():Int = 2

    override fun createFragment(position:Int):Fragment {
        return when (position) {
            0 -> TabListFragment()
            else -> TabMapFragment()
        }
    }

}
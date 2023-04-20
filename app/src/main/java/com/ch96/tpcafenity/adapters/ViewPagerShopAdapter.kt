package com.ch96.tpcafenity.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ch96.tpcafenity.fragments.TabShopDetailFragment
import com.ch96.tpcafenity.fragments.TabShopReviewFragment

class ViewPagerShopAdapter (fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int =2
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabShopDetailFragment()
            else -> TabShopReviewFragment()
        }
    }

}
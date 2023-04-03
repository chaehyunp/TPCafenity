package com.ch96.tpcafenity.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ch96.tpcafenity.fragments.TabMycommunityFragment
import com.ch96.tpcafenity.fragments.TabMyreviewFragment

class ViewPagerMypostAdapter (fragmentActivity: FragmentActivity) :FragmentStateAdapter(fragmentActivity){

    var fragments : ArrayList<Fragment> = ArrayList()
    override fun getItemCount(): Int =2

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }


}
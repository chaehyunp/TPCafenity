package com.ch96.tpcafenity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.ch96.tpcafenity.R
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.ch96.tpcafenity.fragments.ImageFourFragment
import com.ch96.tpcafenity.fragments.ImageOneFragment
import com.ch96.tpcafenity.fragments.ImageThreeFragment
import com.ch96.tpcafenity.fragments.ImageTwoFragment
import com.ch96.tpcafenity.fragments.ImageZeroFragment

class ViewPagerImageAdapter (val fragmentActivity: FragmentActivity, var images:MutableList<Fragment>):FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = images.size

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> return ImageZeroFragment()
            1 -> return ImageOneFragment()
            2 -> return ImageTwoFragment()
            3 -> return ImageThreeFragment()
            else -> return ImageFourFragment()
        }
    }


}
package com.ch96.tpcafenity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class ViewPagerImageAdapter (var context: Context):PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var view:View ?= null
        var inflater = LayoutInflater.from(context)
//        view = inflater.inflate(R.layout.)

        return super.instantiateItem(container, position)
    }
}
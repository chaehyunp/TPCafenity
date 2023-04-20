package com.ch96.tpcafenity.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ch96.tpcafenity.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.ch96.tpcafenity.GV

class ViewPagerImageAdapter (var context:Context, var images:MutableList<String>): PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var inflator = LayoutInflater.from(context)
        var view = inflator.inflate(R.layout.recycler_item_image, container, false)
        var imageView = view.findViewById<ImageView>(R.id.iv)

        Glide.with(context).load("${GV.baseUrl}/Cafenity/${images.get(position)}").into(imageView)

        Log.i("imageNum_position", "$position")
        Log.i("imageNum_images[position]", "${images[position]}")

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) = container.removeView(`object` as View)
    override fun getCount(): Int = images.size
    override fun isViewFromObject(view: View, `object`: Any): Boolean = view==`object`


}
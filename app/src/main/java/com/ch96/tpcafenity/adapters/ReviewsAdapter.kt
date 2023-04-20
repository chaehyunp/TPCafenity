package com.ch96.tpcafenity.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ch96.tpcafenity.activities.NewWriteActivity
import com.ch96.tpcafenity.activities.PostActivity
import com.ch96.tpcafenity.databinding.RecyclerItemReviewBinding
import com.ch96.tpcafenity.fragments.TabShopReviewFragment
import com.ch96.tpcafenity.model.ReviewData

class ReviewsAdapter (var context: Context, var items:MutableList<ReviewData>): Adapter<ReviewsAdapter.VH>(){
    inner class VH(var binding: RecyclerItemReviewBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    = VH(RecyclerItemReviewBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item:ReviewData = items[position]

        holder.binding.tvNick.text = item.nick
        holder.binding.tvText.text = item.text
        holder.binding.tvDate.text = item.writeDate

    }
    fun getReviewNum() :Int {
        return items.size
    }
}
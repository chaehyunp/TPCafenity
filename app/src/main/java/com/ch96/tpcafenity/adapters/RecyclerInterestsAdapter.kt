package com.ch96.tpcafenity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ch96.tpcafenity.databinding.RecyclerItemInterestsBinding
import com.ch96.tpcafenity.model.ShopInfo

class RecyclerInterestsAdapter (var context: Context, var items:MutableList<ShopInfo>): Adapter<RecyclerInterestsAdapter.VH>() {
    inner class VH(var binding: RecyclerItemInterestsBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    = VH(RecyclerItemInterestsBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item:ShopInfo = items[position]

        holder.binding.tvShopName.setText(item.shopName)
        holder.binding.tvRate.setText(item.shopRate)

    }

}
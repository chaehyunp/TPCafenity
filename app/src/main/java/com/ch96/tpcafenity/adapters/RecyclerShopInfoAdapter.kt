package com.ch96.tpcafenity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ch96.tpcafenity.databinding.RecyclerItemShopInfoBinding
import com.ch96.tpcafenity.model.ShopInfo

class RecyclerShopInfoAdapter (var context: Context, var items:MutableList<ShopInfo>): Adapter<RecyclerShopInfoAdapter.VH>() {
    inner class VH(var binding: RecyclerItemShopInfoBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    = VH(RecyclerItemShopInfoBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item:ShopInfo = items[position]

        holder.binding.tvShopName.setText(item.shopName)
        holder.binding.tvRate.setText(item.shopRate)

    }

}
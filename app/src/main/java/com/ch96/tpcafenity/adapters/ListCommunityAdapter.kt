package com.ch96.tpcafenity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ch96.tpcafenity.databinding.RecyclerItemCommunityBinding
import com.ch96.tpcafenity.model.CommunityList

class ListCommunityAdapter (var context: Context, var items:MutableList<CommunityList>): Adapter<ListCommunityAdapter.VH>(){
    inner class VH(var binding: RecyclerItemCommunityBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    =VH(RecyclerItemCommunityBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item:CommunityList = items[position]

        holder.binding.tvTitle.setText(item.title)
        holder.binding.tvNick.setText(item.nick)
        holder.binding.tvDate.setText(item.date)
    }
}
package com.ch96.tpcafenity.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ch96.tpcafenity.activities.PostActivity
import com.ch96.tpcafenity.databinding.RecyclerItemCommunityBinding
import com.ch96.tpcafenity.model.CommunityList

class ListMyCommunityAdapter (var context: Context, var items:ArrayList<CommunityList>): Adapter<ListMyCommunityAdapter.VH>(){
    inner class VH(var binding: RecyclerItemCommunityBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    = VH(RecyclerItemCommunityBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item:CommunityList = items[position]

        holder.binding.tvTitle.text = item.title
        holder.binding.tvNick.text = item.nick
        holder.binding.tvDate.text = item.postDate

        holder.binding.root.setOnClickListener {
            val intent = Intent(context, PostActivity::class.java)
            intent.putExtra("title", item.title)
            intent.putExtra("nick", item.nick)
            intent.putExtra("date", item.postDate)
            intent.putExtra("text", item.text)
            context.startActivity(intent)
        }

    }
}
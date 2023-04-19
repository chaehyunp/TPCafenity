package com.ch96.tpcafenity.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.activities.PostActivity
import com.ch96.tpcafenity.databinding.RecyclerItemCommunityBinding
import com.ch96.tpcafenity.fragments.CommunityFragment
import com.ch96.tpcafenity.model.CommunityList

class ListCommunityAdapter (var context: Context, var items:MutableList<CommunityList>): Adapter<ListCommunityAdapter.VH>(){
    inner class VH(var binding: RecyclerItemCommunityBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    = VH(RecyclerItemCommunityBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item:CommunityList = items[position]

        holder.binding.tvTag.text = item.postTag
        holder.binding.tvTitle.text = item.title
        holder.binding.tvNick.text = item.nick
        holder.binding.tvDate.text = item.postDate

        var imageAddress = ""
        if (item.image0 != "") imageAddress = "${GV.baseUrl}/Cafenity/${item.image0}"
        Glide.with(context).load(imageAddress).into(holder.binding.ivImg)

        holder.binding.root.setOnClickListener {
            val intent = Intent(context, PostActivity::class.java)
            intent.putExtra("tag", item.postTag)
            intent.putExtra("title", item.title)
            intent.putExtra("nick", item.nick)
            intent.putExtra("date", item.postDate)
            intent.putExtra("text", item.text)

            intent.putExtra("image0", item.image0)
            intent.putExtra("image1", item.image1)
            intent.putExtra("image2", item.image2)
            intent.putExtra("image3", item.image3)
            intent.putExtra("image4", item.image4)

            context.startActivity(intent)
        }

    }

}
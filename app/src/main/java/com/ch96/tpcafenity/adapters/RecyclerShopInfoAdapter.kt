package com.ch96.tpcafenity.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ch96.tpcafenity.activities.ShopInfoActivity
import com.ch96.tpcafenity.databinding.RecyclerItemShopInfoBinding
import com.ch96.tpcafenity.model.Place


class RecyclerShopInfoAdapter (var context: Context, var documents:MutableList<Place>): Adapter<RecyclerShopInfoAdapter.VH>() {
    inner class VH(var binding: RecyclerItemShopInfoBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    = VH(RecyclerItemShopInfoBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = documents.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val place:Place = documents[position]

        holder.binding.tvShopName.text = place.place_name
        holder.binding.tvRate.text = "${place.distance}m"

        holder.binding.root.setOnClickListener {
            val intent = Intent(context, ShopInfoActivity::class.java)
            intent.putExtra("place_name", place.place_name)
            intent.putExtra("place_", place.place_url)
            context.startActivity(intent)
        }

    }



}
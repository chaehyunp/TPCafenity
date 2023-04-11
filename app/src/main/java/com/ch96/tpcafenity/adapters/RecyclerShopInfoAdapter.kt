package com.ch96.tpcafenity.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.activities.ShopInfoActivity
import com.ch96.tpcafenity.databinding.RecyclerItemShopInfoBinding
import com.ch96.tpcafenity.fragments.TabListFragment
import com.ch96.tpcafenity.model.Place


class RecyclerShopInfoAdapter (var context: Context, var documents:MutableList<Place>): Adapter<RecyclerShopInfoAdapter.VH>() {
    inner class VH(var binding: RecyclerItemShopInfoBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    = VH(RecyclerItemShopInfoBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = documents.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val place:Place = documents[position]

        holder.binding.tvShopName.text = place.place_name
        holder.binding.tvDistance.text = "${place.distance}m"

        holder.binding.root.setOnClickListener {
            val intent = Intent(context, ShopInfoActivity::class.java)
            intent.putExtra("place_name", place.place_name)
            intent.putExtra("place_url", place.place_url)
            context.startActivity(intent)
        }

        holder.binding.toggleMark.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                Toast.makeText(context, "즐겨찾기에 추가되었습니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "즐겨찾기가 해제되었습니다.", Toast.LENGTH_SHORT).show()
        }

    }



}
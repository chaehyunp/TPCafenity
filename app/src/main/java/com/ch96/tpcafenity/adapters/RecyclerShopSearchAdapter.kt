package com.ch96.tpcafenity.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.activities.ShopInfoActivity
import com.ch96.tpcafenity.databinding.RecyclerItemShopInfoBinding
import com.ch96.tpcafenity.databinding.RecyclerItemShopSearchBinding
import com.ch96.tpcafenity.fragments.TabListFragment
import com.ch96.tpcafenity.model.Place
import com.ch96.tpcafenity.model.ShopId
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecyclerShopSearchAdapter (var context: Context, var documents:MutableList<Place>): Adapter<RecyclerShopSearchAdapter.VH>() {
    inner class VH(var binding: RecyclerItemShopSearchBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    = VH(RecyclerItemShopSearchBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = documents.size ?: 0

    override fun onBindViewHolder(holder: VH, position: Int) {

        Log.i("what size", "${documents.size}")

        if (documents.size != 0) {
            val place:Place = documents[position]

            holder.binding.tvPlaceName.text = place.place_name
            holder.binding.tvAddress.text = if (place.road_address_name == "") place.address_name else place.road_address_name

            holder.binding.root.setOnClickListener {
                val intent = Intent(context, ShopInfoActivity::class.java)
                intent.putExtra("id", place.id)
                intent.putExtra("place_url", place.place_url)
                context.startActivity(intent)
            }

        }

    }

}
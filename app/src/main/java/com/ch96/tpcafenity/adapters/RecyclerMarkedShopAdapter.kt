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
import com.ch96.tpcafenity.fragments.InterestsFragment
import com.ch96.tpcafenity.fragments.TabListFragment
import com.ch96.tpcafenity.model.Place
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecyclerMarkedShopAdapter (var context: Context, var documents:MutableList<Place>): Adapter<RecyclerMarkedShopAdapter.VH>() {
    inner class VH(var binding: RecyclerItemShopInfoBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    = VH(RecyclerItemShopInfoBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = documents.size

    override fun onBindViewHolder(holder: VH, position: Int) {

        //토글버튼 활성화 상태
        holder.binding.toggleMark.isChecked = !holder.binding.toggleMark.isChecked

        val place:Place = documents[position]
        Log.i("what _place", "$place")
        holder.binding.tvShopName.text = place.place_name

        holder.binding.root.setOnClickListener {
            val intent = Intent(context, ShopInfoActivity::class.java)
            intent.putExtra("place_name", place.place_name)
            intent.putExtra("place_url", place.place_url)
            context.startActivity(intent)
        }

        holder.binding.toggleMark.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                //delete
            } else {

            }
        }

    }



}
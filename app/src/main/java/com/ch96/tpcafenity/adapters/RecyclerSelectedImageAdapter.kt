package com.ch96.tpcafenity.adapters

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.ch96.tpcafenity.activities.NewWriteActivity
import com.ch96.tpcafenity.databinding.RecyclerItemNewWriteBinding

class RecyclerSelectedImageAdapter (var context:Context, var images:MutableList<Uri>) : Adapter<RecyclerSelectedImageAdapter.VH>() {

    inner class VH(var binding: RecyclerItemNewWriteBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(RecyclerItemNewWriteBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        Glide.with(context).load(images.get(position)).into(holder.binding.ivAdded)

        holder.binding.ivRemove.setOnClickListener {
            (context as NewWriteActivity).clickedItem(position)
        }

    }
}

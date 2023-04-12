package com.ch96.tpcafenity.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.ch96.tpcafenity.databinding.ActivityNewWriteBinding

class SelectedImageAdapter (var context:Context, var images:MutableList<Uri>) : Adapter<SelectedImageAdapter.VH>(){

    inner class VH (var binding:ActivityNewWriteBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    = VH(ActivityNewWriteBinding.inflate(LayoutInflater.from(context), parent, false))
    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        Glide.with(context).load(images.get(0)).into(holder.binding.ivAdded1)
        Glide.with(context).load(images.get(1)).into(holder.binding.ivAdded2)
        Glide.with(context).load(images.get(2)).into(holder.binding.ivAdded3)
        Glide.with(context).load(images.get(3)).into(holder.binding.ivAdded4)
        Glide.with(context).load(images.get(4)).into(holder.binding.ivAdded5)
    }
}
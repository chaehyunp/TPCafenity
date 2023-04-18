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
import com.ch96.tpcafenity.fragments.TabListFragment
import com.ch96.tpcafenity.model.Place
import com.ch96.tpcafenity.model.ShopId
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecyclerShopInfoAdapter (var context: Context, var documents:MutableList<Place>): Adapter<RecyclerShopInfoAdapter.VH>() {
    inner class VH(var binding: RecyclerItemShopInfoBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    = VH(RecyclerItemShopInfoBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = documents.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val place:Place = documents[position]

        holder.binding.tvPlaceName.text = place.place_name
        holder.binding.tvDistance.text = "${place.distance}m"
        holder.binding.tvAddress.text = if (place.road_address_name == "") place.address_name else place.road_address_name
        holder.binding.tvTel.text = if (place.phone == "") "전화번호 정보없음" else place.phone

        holder.binding.root.setOnClickListener {
            val intent = Intent(context, ShopInfoActivity::class.java)
            intent.putExtra("id", place.id)
            intent.putExtra("place_url", place.place_url)
            context.startActivity(intent)
        }

        //즐겨찾기한 가게 id 받아오기
//        val retrofit = RetrofitHelper.getRetrofitInstance(GV.baseUrl)
//        val retrofitService = retrofit.create(RetrofitService::class.java)
//        retrofitService.getMarkedShopId(GV.loginUserNo?:"").enqueue(object :
//            Callback<MutableList<ShopId>> {
//            override fun onResponse(
//                call: Call<MutableList<ShopId>>, response: Response<MutableList<ShopId>>
//            ) {
//                var res = response.body()
//                if (res<MutableList<ShopId>>?.contains(place.id)?:false)
//            }
//            override fun onFailure(call: Call<MutableList<ShopId>>, t: Throwable) {}
//        })

        holder.binding.toggleMark.setOnCheckedChangeListener { compoundButton, b ->

            //DB로 넘길 데이터 (MutableList -> MutableMap)
            var markedShop = mutableMapOf<String, String>()
            markedShop["accountNo"] = GV.loginUserNo.toString()
            markedShop["id"] = documents[position].id
            markedShop["place_name"] = documents[position].place_name
            markedShop["phone"] = documents[position].phone
            markedShop["address_name"] = documents[position].address_name
            markedShop["road_address_name"] = documents[position].road_address_name
            markedShop["x"] = documents[position].x
            markedShop["y"] = documents[position].y
            markedShop["place_url"] = documents[position].place_url
            markedShop["distance"] = documents[position].distance
            Log.i("what_marked", "$markedShop")

            if (b) {
                val retrofit = RetrofitHelper.getRetrofitInstance(GV.baseUrl)
                val retrofitService = retrofit.create(RetrofitService::class.java)
                retrofitService.saveMarkedShop(markedShop).enqueue(object : Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        Toast.makeText(context, "즐겨찾기에 추가되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Toast.makeText(context, "즐겨찾기 추가가 실패하였습니다.", Toast.LENGTH_SHORT).show()
                        Log.i("what_marked_failed", "$t")
                    }
                })
            } else {
                val retrofit = RetrofitHelper.getRetrofitInstance(GV.baseUrl)
                val retrofitService = retrofit.create(RetrofitService::class.java)
                retrofitService.deleteMark(GV.loginUserNo, markedShop["id"]).enqueue(object : Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        Toast.makeText(context, "즐겨찾기가 해제되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Toast.makeText(context, "즐겨찾기 해제가 실패하였습니다.", Toast.LENGTH_SHORT).show()
                        Log.i("what_del_marked_failed", "$t")
                    }
                })
            }
        }
    }



}
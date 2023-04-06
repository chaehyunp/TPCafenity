package com.ch96.tpcafenity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.databinding.FragmentTabListBinding
import com.ch96.tpcafenity.databinding.FragmentTabMapBinding
import net.daum.mf.map.api.MapView

class TabMapFragment : Fragment() {

    lateinit var binding: FragmentTabMapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabMapBinding.inflate(inflater, container, false)
        return binding .root
    }

    //맵뷰 객체 생성
    val mapView by lazy { MapView(context) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //xml로 만든 맵뷰 컨테이너에 맵뷰 붙이기
        binding.mapviewContainer.addView(mapView)

        //지도 위치 및 마커 설정
        setMapMarkers()
    }

    private  fun setMapMarkers() {
        //맵 중심좌표 - 내위치 (위도, 경도 좌표)
        var lat:Double
        var lng:Double
    }
}
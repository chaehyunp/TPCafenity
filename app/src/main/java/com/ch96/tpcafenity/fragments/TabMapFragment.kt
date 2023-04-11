package com.ch96.tpcafenity.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.activities.MainActivity
import com.ch96.tpcafenity.activities.ShopInfoActivity
import com.ch96.tpcafenity.databinding.FragmentTabListBinding
import com.ch96.tpcafenity.databinding.FragmentTabMapBinding
import com.ch96.tpcafenity.model.Place
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapView.POIItemEventListener

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
        mapView.setPOIItemEventListener(markerEventListner)

        //지도 위치 및 마커 설정
        setMapMarkers()
    }

    val markerEventListner : POIItemEventListener = object : POIItemEventListener {
        override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
            TODO("Not yet implemented")
        }

        override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
            TODO("Not yet implemented")
        }

        override fun onCalloutBalloonOfPOIItemTouched(
            p0: MapView?,
            p1: MapPOIItem?,
            p2: MapPOIItem.CalloutBalloonButtonType?
        ) {
            p1?.userObject?:return
            val place = p1.userObject as Place
            startActivity(Intent(context, ShopInfoActivity::class.java).putExtra("place_url", place.place_url))
        }

        override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
            TODO("Not yet implemented")
        }

    }

    private  fun setMapMarkers() {
        //맵 중심좌표 - 내위치 (위도, 경도 좌표)
        var lat:Double = (activity as MainActivity).myLocation?.latitude ?: 37.5663
        var lng:Double = (activity as MainActivity).myLocation?.longitude ?: 126.9779

        var mapPoint = MapPoint.mapPointWithCONGCoord(lat,lng)
        mapView.setMapCenterPointAndZoomLevel(mapPoint, 3, true)
        mapView.zoomIn(true)
        mapView.zoomOut(true)

        //내위치 마커
        var marker = MapPOIItem()
        marker.apply {
            itemName = "내위치"
            mapPoint = mapPoint
            markerType = MapPOIItem.MarkerType.BluePin
            selectedMarkerType = MapPOIItem.MarkerType.RedPin
        }
        mapView.addPOIItem(marker)
        
        //검색장소 마커
        val documents:MutableList<Place>? = (activity as MainActivity).searchPlaceResponse?.documents
        documents?.forEach { 
            val marker = MapPOIItem().apply { 
                itemName = it.place_name
                mapPoint = mapPoint
                markerType = MapPOIItem.MarkerType.YellowPin
                selectedMarkerType = MapPOIItem.MarkerType.RedPin
                userObject = it
            }
            mapView.addPOIItem(marker)
        }
    }
}
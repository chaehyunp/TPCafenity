package com.ch96.tpcafenity.model

import java.io.Serializable

data class KakaoSearchPlaceResponse (var meta:PlaceMeta, var documents:MutableList<Place>)

data class PlaceMeta(var total_count:Int, var pageable_count:Int, var is_end:Boolean)

data class Place (
    var id:String, //장소 ID
    var place_name:String, //장소명, 업체명
    var phone:String, //전화번호
    var address_name:String, //전체 지번 주소
    var road_address_name:String, //전체 도로명 주소
    var x:String, //경도(longitude)
    var y:String, //위도(latitude)
    var place_url:String, //장소 상세 페이지 URL
    var distance:String, //중심좌표까지의 거리(단위: 미터(m))
)

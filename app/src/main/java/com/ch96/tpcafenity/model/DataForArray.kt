package com.ch96.tpcafenity.model

data class CommunityList(var postTag:String,
                         var title:String,
                         var text:String,
                         var image0:String,
                         var image1:String,
                         var image2:String,
                         var image3:String,
                         var image4:String,
                         var nick:String,
                         var postDate:String)

data class ReviewData(var nick: String,
                      var text:String,
                      var writeDate:String)

data class ShopId(var id: String)


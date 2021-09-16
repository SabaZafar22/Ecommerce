package com.sabazafar.ecommerce.entity

import com.google.gson.annotations.SerializedName

data class Product(
    var id : Int,
    @SerializedName("title")
    var name : String,

    @SerializedName("image")
    var imageUrl : String,

    var description : String,
    var price : String

)

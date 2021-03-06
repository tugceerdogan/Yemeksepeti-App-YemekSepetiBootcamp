package com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.basket

import com.google.gson.annotations.SerializedName

data class BasketResponse(
    @SerializedName("data")
    val orderList: ArrayList<Basket>,
    val success: Boolean
)

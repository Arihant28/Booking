package com.work.asinghi.marvelbooking.model

data class Locations(val place: String, val url : String, val date : String, val rate : String, val description : String, var like: Boolean = false)
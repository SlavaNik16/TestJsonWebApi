package com.example.ft.Models

data class Trips (
       val driver_id: String?,
       val client_id: String?,
       val price: Float,
       val description:String,
       val start_time:String,
       val seats_amount: Int,
       val start_point: String,
       val end_point: String,
       val status: String
       )
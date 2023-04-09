package com.example.ft.API

import com.example.ft.Models.Trips
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import java.util.TreeMap

interface TripsApi {
    @GET("Trips/Read")
    suspend fun GetAll(): ArrayList<Trips>

    @kotlin.ExperimentalMultiplatform
       @POST("Trips/Create")
       suspend fun NewTrips(@Body trips: Trips): Trips


}
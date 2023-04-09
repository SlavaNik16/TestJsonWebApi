package com.example.ft.API


import com.example.ft.Models.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*
import kotlin.collections.ArrayList

interface UsersApi {
    @GET("Users/ReadAll")
    suspend fun GetAll(): ArrayList<User>

    @ExperimentalMultiplatform
        @POST("Users/Create")
        fun NewUser(@Body user: User): Call<ResponseBody>

    @ExperimentalMultiplatform
        @PUT("Users/Update")
        suspend fun EditUser(@Body id: UUID, user: User): User

}

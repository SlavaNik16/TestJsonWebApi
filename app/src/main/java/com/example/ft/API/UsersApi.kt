package com.example.ft.API


import com.example.ft.Models.GetUserID
import com.example.ft.Models.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*
import kotlin.collections.ArrayList

interface UsersApi {
    @GET("Users/Read")
    suspend fun GetAll(): ArrayList<User>

    @GET("Users/Read/{guid}")
    suspend fun GetCurrentUser(@Path("guid") guid:String): User

    @ExperimentalMultiplatform
        @POST("Users/Create")
        fun NewUser(@Body user: User): Call<ResponseBody>

    @ExperimentalMultiplatform
    @PUT("Users/Update")
    fun EditUser(@Query("guid") guid: String, @Body user: User): Call<ResponseBody>

    @DELETE("Users/Delete")
    fun DeleteUser(@Query("guid") guid: String): Call<ResponseBody>
}

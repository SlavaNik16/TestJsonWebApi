package com.example.ft.Activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.ft.API.TripsApi
import com.example.ft.API.UsersApi
import com.example.ft.Models.User
import com.example.ft.Models.GetUserID
import com.example.ft.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var ID:UUID
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMultiplatform::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        var retrofit = Retrofit.Builder()
            .baseUrl("http://fromandtoapi.somee.com").client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var usersApi = retrofit.create(UsersApi::class.java)
        var tripsApi = retrofit.create(TripsApi::class.java)


        binding.button.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                usersApi.GetAll()
            }
        }

        binding.buttonEdit.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {

                var date = LocalDate.parse("2003-10-27")
                var text = usersApi.NewUser(
                    User(
                        "ура",
                        "Все ок",
                        "Мужской",
                        "nik@gmail.com",
                        "Slavanik",
                        "${date}",
                        "2342342323",
                        "8943839208"
                    )
                ).enqueue(object : retrofit2.Callback<ResponseBody>{
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                       var list = Gson().fromJson("""${response.body()!!.string()}""", GetUserID::class.java)
                       ID =  list.id_user
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })


//                var text = tripsApi.NewTrips(
//                    Trips(
//                     "Нижний новгород", "34758937"
//                     )
//                )
//                runOnUiThread{
//                    binding.text.text = text.toString()
//                }

            }
        }

    }
}

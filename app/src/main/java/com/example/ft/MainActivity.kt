package com.example.ft

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.ft.API.TripsApi
import com.example.ft.API.UsersApi
import com.example.ft.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
                usersApi.GetCurrentUser("4d087593-4040-4d7a-869f-796cf1aebc2e")
            }
        }

        binding.buttonEdit.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                usersApi.DeleteUser(
                    "512ea2dd-e636-4557-ade4-0dc41f615500",
                ).enqueue(object : retrofit2.Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    }

                })
            }
            runOnUiThread{

            }
/* var date = LocalDate.parse("2003-02-03")
var user = User(
   "ура",
   "Все ок",
   "Мужской",
   "nik@gmail.com",
   "123",
   "2342342323",
   "$date",
   "8943839208",
   false,
  4.0,
  null,
   null,
  null
)
usersApi.EditUser(
"512ea2dd-e636-4557-ade4-0dc41f615500",
   user
).enqueue(object: retrofit2.Callback<ResponseBody>{
   override fun onResponse(
       call: Call<ResponseBody>,
       response: Response<ResponseBody>
   ) {
   }

   override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
   }

})
*/
/*var date = LocalDate.parse("2003-10-27")
var text = usersApi.NewUser(
   User(
       "ура",
       "Все ок",
       "Мужской",
       "nik@gmail.com",
       "Slavanik",
       "$date",
       "2342342323",
       "8943839208"
   )
).enqueue(object : retrofit2.Callback<ResponseBody>{
   override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
      var list = Gson().fromJson("""${response.body()!!.string()}""", GetUserID::class.java)
      return list.id_user
   }

   override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
       TODO("Not yet implemented")
   }

})
*/

//                var text = tripsApi.NewTrips(
//                    Trips(
//                     "Нижний новгород", "34758937"
//                     )
//                )
//                runOnUiThread{
//                    binding.text.text = text.toString()
//                }

//}

}

}
}

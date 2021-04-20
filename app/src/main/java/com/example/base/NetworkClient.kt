package com.example.base


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

class NetworkClient {

    private val api : RetrofitApi

    companion object{
        const val CONNECTION_TIMEOUT: Long = 180L
        const val READ_TIMEOUT: Long = 180L
        const val WRITE_TIMEOUT: Long = 180L
        const val BASE_URL = "https://api.github.com/"
    }

    private fun getApiClient(): RetrofitApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofitBuilder = Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }


        val retrofit = retrofitBuilder.build()
        return retrofit.create(RetrofitApi::class.java)
    }

    init{
        this.api = getApiClient()
    }




    fun getRepo(login : String) : Flow<List<Repo>> = flow {
      emit(api.getListRepo(login))
    }

}

interface RetrofitApi {
    @GET("users/{user}/repos") suspend fun getListRepo(@Path("user") user : String): List<Repo>
}


data class Repo(
    val name: String
)

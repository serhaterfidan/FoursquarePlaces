package com.colornative.seattleplacesearch.network

import com.colornative.seattleplacesearch.model.PlaceDetails
import com.colornative.seattleplacesearch.model.PlaceSearchResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoursquareApi {

    @GET("places/search")
    suspend fun getPlaces(
        @Query("query") query: String,
        @Query("ll") latLong: String,
        @Query("v") version: String = "20211210"
    ): Response<PlaceSearchResponse>

    @GET("venues/{id}")
    suspend fun getPlaceDetails(@Path("id") id: String): Response<PlaceDetails>

    companion object {
        private const val BASE_URL = "https://api.foursquare.com/v3/"

        fun create(): FoursquareApi {
            // Create an OkHttpClient instance with an interceptor for adding the API access token to the request
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original: Request = chain.request()
                    val requestBuilder: Request.Builder = original.newBuilder()
                        .header("accept", "application/json")
                        .header("Authorization", "fsq3G5hn8OgY8414PdcxeBXeuIuXHo0suoT1kfARnp5+bP0=")
                    val request: Request = requestBuilder.build()
                    chain.proceed(request)
                }
                .build()

            // Create a Retrofit instance with the base URL and the OkHttpClient instance
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Create the com.colornative.seattleplacesearch.network.FoursquareApi interface implementation
            return retrofit.create(FoursquareApi::class.java)
        }
    }
}
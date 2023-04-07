package com.colornative.seattleplacesearch.network

import com.colornative.seattleplacesearch.PlaceDetails
import com.colornative.seattleplacesearch.model.Place
import com.colornative.seattleplacesearch.model.PlaceDetails

class PlaceRepository {

    private val api = FoursquareApi.create()

    suspend fun searchPlaces(query: String, ll: String): Result<List<Place>> {
        return try {
            val response = api.getPlaces(query, ll)
            if (response.isSuccessful) {
                val venues = response.body()?.results ?: emptyList()
                Result.success(venues)
            } else {
                val errorBody = response.errorBody()?.string()
                Result.failure(Error(errorBody ?: "Unknown Error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPlaceDetails(id: String): Result<PlaceDetails?> {
        return try {
            val response = api.getPlaceDetails(id)
            if (response.isSuccessful) {
                val venueDetails = response.body()
                Result.success(venueDetails)
            } else {
                val errorBody = response.errorBody()?.string()
                Result.failure(Error(errorBody ?: "Unknown Error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
package com.colornative.seattleplacesearch.model

data class Place(
    val fsq_id: String,
    val categories: List<Category>,
    val chains: List<Any>,
    val distance: Int,
    val geocodes: Geocodes,
    val link: String,
    val location: Location,
    val name: String,
    val related_places: RelatedPlaces,
    val timezone: String
)

data class Category(
    val id: Int,
    val name: String,
    val icon: Icon
)

data class Icon(
    val prefix: String,
    val suffix: String
)

data class Geocodes(
    val main: Main,
    val roof: Roof
)

data class Main(
    val latitude: Double,
    val longitude: Double
)

data class Roof(
    val latitude: Double,
    val longitude: Double
)

data class Location(
    val address: String,
    val address_extended: String,
    val census_block: String,
    val country: String,
    val dma: String,
    val formatted_address: String,
    val locality: String,
    val postcode: String,
    val region: String
)

data class RelatedPlaces(
    val results: List<Any>
)

data class Context(
    val geo_bounds: GeoBounds
)

data class GeoBounds(
    val circle: Circle
)

data class Circle(
    val center: Center,
    val radius: Int
)

data class Center(
    val latitude: Double,
    val longitude: Double
)

data class PlaceSearchResponse(
    val results: List<Place>,
    val context: Context
)
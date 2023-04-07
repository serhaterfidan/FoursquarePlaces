package com.colornative.seattleplacesearch.model

data class PlaceDetails(
    val fsq_id: String,
    val categories: List<CategoryDetails>,
    val chains: List<Chain>,
    val date_closed: String?,
    val description: String?,
    val distance: Int,
    val email: String?,
    val fax: String?,
    val features: Features?,
    val geocodes: GeocodesDetails,
    val hours: Hours?,
    val hours_popular: List<RegularHours>,
    val link: String?,
    val location: LocationDetails,
    val menu: String?,
    val name: String,
    val photos: List<Photo>,
    val popularity: Int?,
)

data class CategoryDetails(
    val id: Int,
    val name: String,
    val icon: Icon
)

data class IconDetails(
    val id: String,
    val created_at: String,
    val prefix: String,
    val suffix: String,
    val width: Int,
    val height: Int,
    val classifications: List<String>,
    val tip: Tip
)

data class Tip(
    val id: String,
    val created_at: String,
    val text: String,
    val url: String,
    val lang: String,
    val agree_count: Int,
    val disagree_count: Int
)

data class Chain(
    val id: String,
    val name: String
)

data class Features(
    val payment: Payment?,
    val food_and_drink: FoodAndDrink?,
    val services: Services?,
    val amenities: Amenities?
)

data class Payment(
    val credit_cards: CreditCards?,
    val digital_wallet: DigitalWallet?
)

data class CreditCards(
    val accepts_credit_cards: Any?,
    val discover: Any?,
    val visa: Any?,
    val diners_club: Any?,
    val master_card: Any?
)

data class DigitalWallet(
    val accepts_nfc: Any?
)

data class FoodAndDrink(
    val alcohol: Alcohol?,
    val meals: Meals?
)

data class Alcohol(
    val beer: Any?,
    val byo: Any?,
    val cocktails: Any?,
    val full_bar: Any?,
    val wine: Any?
)

data class Meals(
    val breakfast: Any?,
    val brunch: Any?,
    val lunch: Any?,
    val happy_hour: Any?,
    val dessert: Any?,
    val dinner: Any?
)

data class Services(
    val delivery: Any?,
    val takeout: Any?,
    val drive_through: Any?,
    val dine_in: DineIn?
)

data class DineIn(
    val reservations: Any?,
    val online_reservations: Any?,
    val groups_only_reservations: Any?
)

data class Amenities(
    val restroom: Any?,
    val smoking: Any?,
    val jukebox: Any?,
    val music: Any?,
    val live_music: Any?,
    val private_room: Any?,
    val outdoor_seating: Any?,
    val tvs: Any?,
    val atm: Any?,
    val coat_check: Any?,
    val wheelchair_accessible: Any?,
    val parking: Parking?
)

data class Parking(
    val parking: Any?,
    val street_parking: Any?,
    val valet_parking: Any?,
    val public_lot: Any?,
    val private_lot: Any?
)

data class GeocodesDetails(
    val drop_off: LatLng,
    val front_door: LatLng,
    val main: LatLng,
    val road: LatLng,
    val roof: LatLng
)

data class LatLng(
    val latitude: Double,
    val longitude: Double
)

data class Hours(
    val display: String,
    val is_local_holiday: Boolean,
    val open_now: Boolean,
    val regular: List<RegularHours>,
    val seasonal: List<SeasonalHours>
)

data class RegularHours(
    val close: String,
    val day: Int,
    val open: String
)

data class SeasonalHours(
    val closed: Boolean,
    val end_date: String,
    val hours: List<RegularHours>,
    val start_date: String
)

data class LocationDetails(
    val address: String,
    val address_extended: String?,
    val admin_region: String?,
    val census_block: String?,
    val country: String,
    val cross_street: String?,
    val dma: String?,
    val formatted_address: String,
    val locality: String,
    val neighborhood: List<String>,
    val po_box: String?,
    val post_town: String?,
    val postcode: String?,
    val region: String
)

data class Photo(
    val id: String,
    val created_at: String,
    val prefix: String,
    val suffix: String,
    val width: Int,
    val height: Int,
    val classifications: List<String>,
    val tip: TipDetails?
)

data class TipDetails(
    val id: String,
    val created_at: String,
    val text: String,
    val url: String,
    val lang: String,
    val agree_count: Int,
    val disagree_count: Int
)
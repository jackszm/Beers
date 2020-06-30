package com.jsz.beerlist.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiBeer(
    @Json(name = "abv") val abv: Double,
//    @Json(name = "attenuation_level") val attenuationLevel: Double,
//    @Json(name = "boil_volume") val apiBoilVolume: ApiBoilVolume,
//    @Json(name = "brewers_tips") val brewersTips: String,
//    @Json(name = "contributed_by") val contributedBy: String,
//    @Json(name = "description") val description: String,
//    @Json(name = "ebc") val ebc: Double,
//    @Json(name = "first_brewed") val firstBrewed: String,
//    @Json(name = "food_pairing") val foodPairing: List<String>,
//    @Json(name = "ibu") val ibu: Double,
//    @Json(name = "id") val id: Int,
    @Json(name = "image_url") val imageUrl: String,
//    @Json(name = "ingredients") val apiIngredients: ApiIngredients,
//    @Json(name = "method") val apiMethod: ApiMethod,
    @Json(name = "name") val name: String
//    @Json(name = "ph") val ph: Double,
//    @Json(name = "srm") val srm: Double,
//    @Json(name = "tagline") val tagline: String,
//    @Json(name = "target_fg") val targetFg: Double,
//    @Json(name = "target_og") val targetOg: Double,
//    @Json(name = "volume") val volume: ApiVolume
)

@JsonClass(generateAdapter = true)
data class ApiBoilVolume(
    @Json(name = "unit") val unit: String,
    @Json(name = "value") val value: Int
)

@JsonClass(generateAdapter = true)
data class ApiIngredients(
    @Json(name = "hops") val hops: List<Any>,
    @Json(name = "malt") val malt: List<ApiMalt>,
    @Json(name = "yeast") val yeast: String
)

@JsonClass(generateAdapter = true)
data class ApiMethod(
    @Json(name = "fermentation") val fermentation: ApiFermentation,
    @Json(name = "mash_temp") val mashTemp: List<ApiMashTemp>,
    @Json(name = "twist") val twist: String? = null
)

@JsonClass(generateAdapter = true)
data class ApiVolume(
    @Json(name = "unit") val unit: String,
    @Json(name = "value") val value: Int
)

@JsonClass(generateAdapter = true)
data class ApiMalt(
    @Json(name = "amount") val amount: ApiAmount,
    @Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
data class ApiAmount(
    @Json(name = "unit") val unit: String,
    @Json(name = "value") val value: Double
)

@JsonClass(generateAdapter = true)
data class ApiFermentation(
    @Json(name = "temp") val temp: ApiTemp
)

@JsonClass(generateAdapter = true)
data class ApiMashTemp(
    @Json(name = "duration") val duration: Int,
    @Json(name = "temp") val temp: ApiTempX
)

@JsonClass(generateAdapter = true)
data class ApiTemp(
    @Json(name = "unit") val unit: String,
    @Json(name = "value") val value: Double
)

@JsonClass(generateAdapter = true)
data class ApiTempX(
    @Json(name = "unit") val unit: String,
    @Json(name = "value") val value: Int
)

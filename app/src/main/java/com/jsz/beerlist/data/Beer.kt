package com.jsz.beerlist.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Beer(
    val name: String,
    val imageUrl: String,
    val abv: Double,
    val hops: List<String>,
    val malts: List<String>,
    val method: BeerMethod
) : Parcelable

@Parcelize
data class BeerMethod(
    val fermentationTemp: String,
    val mashTemp: List<String>
) : Parcelable


fun ApiBeer.toBeer(): Beer {
    return Beer(
        name = name,
        imageUrl = imageUrl,
        abv = abv,
        hops = this.ingredients.hops.map { it.name },
        malts = this.ingredients.malts.map { it.name },
        method = BeerMethod(
            fermentationTemp = "${method.fermentation.temp.value} ${method.fermentation.temp.unit}",
            mashTemp = method.mashTemp.map { "${it.temp.value} ${it.temp.unit}" }
        )

    )
}



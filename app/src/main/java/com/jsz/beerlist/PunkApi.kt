package com.jsz.beerlist

import com.jsz.beerlist.data.ApiBeer
import io.reactivex.Single
import retrofit2.http.GET

interface PunkApi {

    @GET("beers")
    fun fetchBeers(): Single<List<ApiBeer>>
}

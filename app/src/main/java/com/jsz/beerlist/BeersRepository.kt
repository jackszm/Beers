package com.jsz.beerlist

import com.jsz.beerlist.data.Beer
import com.jsz.beerlist.data.toBeer
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class BeersRepository(
    private val punkApi: PunkApi
) {

    fun getBeers(): Single<List<Beer>> {
        return punkApi.fetchBeers()
            .subscribeOn(Schedulers.io())
            .map { it.map { apiBeer -> apiBeer.toBeer() } }
    }
}

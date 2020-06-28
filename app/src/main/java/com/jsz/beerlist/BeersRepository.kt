package com.jsz.beerlist

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class BeersRepository(
    private val punkApi: PunkApi
) {

    fun getBeers(): Single<List<String>> {
        return punkApi.fetchBeers()
            .subscribeOn(Schedulers.io())
            .map { it.map { beer -> beer.name } }
    }
}

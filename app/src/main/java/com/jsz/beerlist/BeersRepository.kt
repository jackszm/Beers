package com.jsz.beerlist

import com.jsz.beerlist.data.Beer
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class BeersRepository(
    private val punkApi: PunkApi
) {

    fun getBeers(): Single<List<Beer>> {
        return punkApi.fetchBeers()
            .subscribeOn(Schedulers.io())
            .map { it.map { beer -> Beer(beer.name, beer.imageUrl, beer.abv) } }
    }
}

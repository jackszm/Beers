package com.jsz.beerlist

import com.jsz.beerlist.data.Beer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class MainViewModel() : BaseViewModel<List<Beer>>(emptyList()) {

    private val repository = BeersRepository(PunkApiClient.punkApi)

    init {
        disposables += repository.getBeers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { setState { it } }
            )
    }

}

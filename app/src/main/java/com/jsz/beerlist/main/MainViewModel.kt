package com.jsz.beerlist.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jsz.beerlist.AppNavigator
import com.jsz.beerlist.BeersRepository
import com.jsz.beerlist.PunkApiClient
import com.jsz.beerlist.common.Action
import com.jsz.beerlist.common.BaseViewModel
import com.jsz.beerlist.data.Beer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class MainViewModel(
    private val navigator: AppNavigator,
    repository: BeersRepository
) : BaseViewModel<MainViewModel.State>(State.Loading) {

    init {
        disposables += repository.getBeers()
            .map {
                it.map { beer ->
                    UiBeer(
                        beer = beer,
                        clickAction = Action { onBeerClicked(beer) })
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { setState { State.Data(it) } },
                onError = { setState { State.Error } }
            )
    }

    private fun onBeerClicked(beer: Beer) {
        navigator.toBeerDetails(beer)
    }


    sealed class State {
        object Loading : State()

        data class Data(
            val beers: List<UiBeer>
        ) : State()

        object Error : State()
    }

}

data class UiBeer(
    val beer: Beer,
    val clickAction: Action<Beer>
)

class MainViewModelFactory(
    private val navigator: AppNavigator
) : ViewModelProvider.Factory {

    private val repository = BeersRepository(PunkApiClient.punkApi)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(navigator, repository) as T
    }
}



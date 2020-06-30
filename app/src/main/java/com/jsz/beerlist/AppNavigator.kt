package com.jsz.beerlist

import android.app.Activity
import com.jsz.beerlist.data.Beer
import com.jsz.beerlist.details.BeerDetailsActivity

class AppNavigator(
    private val activity: Activity
) {

    fun toBeerDetails(beer: Beer) {
        activity.startActivity(
            BeerDetailsActivity.buildIntent(activity, beer)
        )
    }
}

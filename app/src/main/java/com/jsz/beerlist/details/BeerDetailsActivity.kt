package com.jsz.beerlist.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import com.jsz.beerlist.R
import com.jsz.beerlist.data.Beer
import kotlinx.android.synthetic.main.activity_beer_details.*

class BeerDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_details)

        val beer = intent.getParcelableExtra<Beer>(EXTRA_BEER)

        beerImage.load(beer.imageUrl)
        beerName.text = beer.name
        beerAbv.text = beer.abv
        beerDescription.text = beer.description
        beerHops.text = beer.hops.joinToString(", ")
        beerMalts.text = beer.malts.joinToString(", ")
        beerMethods.text = beer.method.mashTemp.joinToString(", ")
    }

    companion object {
        private const val EXTRA_BEER = "EXTRA_BEER"

        fun buildIntent(context: Context, beer: Beer): Intent {
            return Intent(context, BeerDetailsActivity::class.java)
                .putExtra(EXTRA_BEER, beer)
        }
    }
}



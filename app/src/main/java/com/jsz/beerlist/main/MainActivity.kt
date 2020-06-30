package com.jsz.beerlist.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.jsz.beerlist.AppNavigator
import com.jsz.beerlist.R
import com.jsz.beerlist.common.exhaustive
import com.jsz.beerlist.common.gone
import com.jsz.beerlist.common.visible
import com.jsz.beerlist.main.MainViewModel.State.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            AppNavigator(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = BeersAdapter()
        beersRecyclerView.adapter = adapter

        viewModel.state.observe(this) { state ->
            when (state) {
                Loading -> {
                    loadingErrorView.showLoading()
                    beersRecyclerView.gone()
                }
                is Data -> {
                    loadingErrorView.gone()
                    beersRecyclerView.visible()
                    adapter.submitList(state.beers)
                }
                Error -> {
                    loadingErrorView.showError()
                    beersRecyclerView.gone()
                }
            }.exhaustive
        }
    }
}

class BeersAdapter : ListAdapter<UiBeer, BeerViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BeerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BeerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_beer, parent, false)) {

    private val nameView = itemView.findViewById<TextView>(R.id.beer_name)
    private val abvTextView = itemView.findViewById<TextView>(R.id.beer_abv)
    private val imageView = itemView.findViewById<ImageView>(R.id.beer_image)

    fun bind(uiBeer: UiBeer) {
        nameView.text = uiBeer.beer.name
        imageView.load(uiBeer.beer.imageUrl)
        abvTextView.text = uiBeer.beer.abv
        itemView.setOnClickListener { uiBeer.clickAction(uiBeer.beer) }
    }

}

private val diffCallback = object : ItemCallback<UiBeer>() {
    override fun areItemsTheSame(oldItem: UiBeer, newItem: UiBeer): Boolean {
        return oldItem.beer.name == newItem.beer.name
    }

    override fun areContentsTheSame(oldItem: UiBeer, newItem: UiBeer): Boolean {
        return oldItem == newItem
    }
}

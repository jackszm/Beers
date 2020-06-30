package com.jsz.beerlist.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.jsz.beerlist.R
import kotlinx.android.synthetic.main.merge_loading_error.view.*

class LoadingErrorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.merge_loading_error, this)
    }

    fun showLoading() {
        visible()
        progressBar.visible()
        errorView.gone()
    }

    fun showError() {
        visible()
        progressBar.gone()
        errorView.visible()
    }

    fun hide() {
        gone()
    }
}

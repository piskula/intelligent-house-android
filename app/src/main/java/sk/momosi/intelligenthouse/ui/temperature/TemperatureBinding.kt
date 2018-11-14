package sk.momosi.intelligenthouse.ui.temperature

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import sk.momosi.intelligenthouse.TemperatureAdapter
import sk.momosi.intelligenthouse.model.TemperatureItem

object TemperatureBinding {

    @JvmStatic
    @BindingAdapter("temperatures")
    fun setTemperatures(recyclerView: RecyclerView, items: List<TemperatureItem>?) {
        val adapter = recyclerView.adapter
        if (adapter != null && adapter is TemperatureAdapter) {
            adapter.replaceData(items ?: emptyList())
        }
    }

}

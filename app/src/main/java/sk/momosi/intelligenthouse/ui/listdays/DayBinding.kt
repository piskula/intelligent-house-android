package sk.momosi.intelligenthouse.ui.listdays

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import java.time.LocalDate

object DayBinding {

    @JvmStatic
    @BindingAdapter("days")
    fun setTemperatures(recyclerView: RecyclerView, items: List<LocalDate>?) {
        val adapter = recyclerView.adapter
        if (adapter != null && adapter is DayAdapter) {
            adapter.replaceData(items ?: emptyList())
        }
    }

}

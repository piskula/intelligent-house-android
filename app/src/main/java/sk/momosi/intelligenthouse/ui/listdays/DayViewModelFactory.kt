package sk.momosi.intelligenthouse.ui.listdays

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class DayViewModelFactory(private val sensorId: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DayListViewModel(sensorId) as T;
    }

}

package sk.momosi.intelligenthouse.ui.temperature

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class TemperatureViewModelFactory(private val sensorId: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TemperatureListViewModel(sensorId) as T;
    }

}

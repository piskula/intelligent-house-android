package sk.momosi.intelligenthouse.ui

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean

abstract class BaseListViewModel: ViewModel() {

    val isLoading = ObservableBoolean(true)

    val isEmpty = ObservableBoolean(false)

    val isError = ObservableBoolean(false)

    abstract fun loadData()
}

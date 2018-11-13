package sk.momosi.intelligenthouse.ui

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import android.os.Parcelable

abstract class BaseListViewModel<T: Parcelable>: ViewModel() {

    val temperatures: ObservableList<T> = ObservableArrayList()

    val isLoading = ObservableBoolean(true)

    val isEmpty = ObservableBoolean(false)

    val isError = ObservableBoolean(false)

    abstract fun loadData()
}

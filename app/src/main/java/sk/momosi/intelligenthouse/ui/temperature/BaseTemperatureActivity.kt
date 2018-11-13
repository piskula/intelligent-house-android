package sk.momosi.intelligenthouse.ui.temperature

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import sk.momosi.intelligenthouse.model.TemperatureItem
import sk.momosi.intelligenthouse.ui.BaseListViewModel

abstract class BaseTemperatureActivity<T : BaseListViewModel<TemperatureItem>>(private val clazz: Class<T>)
    : AppCompatActivity() {

    lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(clazz)
        viewModel.loadData()

        setupList()
    }

    abstract fun setupList()
}

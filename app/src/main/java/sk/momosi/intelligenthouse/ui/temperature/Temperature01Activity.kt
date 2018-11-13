package sk.momosi.intelligenthouse.ui.temperature

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_temperature_01.*
import sk.momosi.intelligenthouse.R
import sk.momosi.intelligenthouse.TemperatureAdapter
import sk.momosi.intelligenthouse.databinding.ActivityListTemperature01Binding

class Temperature01Activity: BaseTemperatureActivity<Temperature01ListViewModel>(
    Temperature01ListViewModel::class.java) {

    lateinit var binding: ActivityListTemperature01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_temperature_01)
        binding.setLifecycleOwner(this)
        super.onCreate(savedInstanceState)
    }

    override fun setupList() {
        val manager: LinearLayoutManager = temperature_list_01.layoutManager as LinearLayoutManager
        manager.stackFromEnd = true
        manager.reverseLayout = true

        binding.viewModel = viewModel
        temperature_list_01.adapter = TemperatureAdapter(viewModel = viewModel)

    }

}

package sk.momosi.intelligenthouse.ui.temperature

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_temperature.*
import sk.momosi.intelligenthouse.R
import sk.momosi.intelligenthouse.databinding.ActivityListTemperatureBinding

const val SENSOR_ID = "sensorId"

class TemperatureActivity : AppCompatActivity() {

    lateinit var binding: ActivityListTemperatureBinding
    lateinit var viewModel: TemperatureListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_temperature)
        binding.setLifecycleOwner(this)

        super.onCreate(savedInstanceState)

        val sensorId = savedInstanceState?.getString(SENSOR_ID) ?: intent.extras.getString(SENSOR_ID)
        viewModel = ViewModelProviders.of(this, TemperatureViewModelFactory(sensorId))
            .get(TemperatureListViewModel::class.java)
        viewModel.loadData()

        setupList()
    }

    private fun setupList() {
        val manager: LinearLayoutManager = temperature_list.layoutManager as LinearLayoutManager
        manager.stackFromEnd = true
        manager.reverseLayout = true

        binding.viewModel = viewModel
        temperature_list.adapter = TemperatureAdapter(viewModel = viewModel)

    }

}

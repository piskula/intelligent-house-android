package sk.momosi.intelligenthouse.ui.listdays

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_day.*
import sk.momosi.intelligenthouse.R
import sk.momosi.intelligenthouse.databinding.ActivityListDayBinding

const val SENSOR_ID = "sensorId"

class DayListActivity : AppCompatActivity() {

    lateinit var binding: ActivityListDayBinding
    lateinit var viewModel: DayListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_day)
        binding.setLifecycleOwner(this)

        super.onCreate(savedInstanceState)

        val sensorId = savedInstanceState?.getString(SENSOR_ID) ?: intent.extras.getString(
            SENSOR_ID
        )
        viewModel = ViewModelProviders.of(this, DayViewModelFactory(sensorId))
            .get(DayListViewModel::class.java)
        viewModel.loadData()

        setupList()
    }

    private fun setupList() {
        val manager: LinearLayoutManager = day_list.layoutManager as LinearLayoutManager
        manager.stackFromEnd = true
        manager.reverseLayout = true

        binding.viewModel = viewModel

    }

}

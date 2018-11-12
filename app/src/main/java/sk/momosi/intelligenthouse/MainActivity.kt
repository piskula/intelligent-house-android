package sk.momosi.intelligenthouse

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import sk.momosi.intelligenthouse.databinding.ActivityMainBinding
import sk.momosi.intelligenthouse.ui.TemperatureListViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: TemperatureListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TemperatureListViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        setupList()

        viewModel.loadData()
    }

    private fun setupList() {
        temperature_list.adapter = TemperatureAdapter(viewModel = viewModel)
    }
}

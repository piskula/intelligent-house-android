package sk.momosi.intelligenthouse

import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import sk.momosi.intelligenthouse.databinding.ItemTemperatureBinding
import sk.momosi.intelligenthouse.model.TemperatureItem
import sk.momosi.intelligenthouse.ui.BaseListViewModel


class TemperatureAdapter(
    var data: List<TemperatureItem> = emptyList(),
    val viewModel: BaseListViewModel<TemperatureItem>
) : RecyclerView.Adapter<TemperatureAdapter.TemperatureViewHolder>() {

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TemperatureViewHolder, position: Int) {
        holder.bind(data[position])
        val output = DateUtils.getRelativeTimeSpanString(data[position].timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TemperatureViewHolder {
        val itemBinding = ItemTemperatureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TemperatureViewHolder(itemBinding)
    }

    fun replaceData(data: List<TemperatureItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    // ViewHolder
    inner class TemperatureViewHolder(val binding: ItemTemperatureBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TemperatureItem) {
            binding.temperature = item
            binding.executePendingBindings()
        }
    }
}

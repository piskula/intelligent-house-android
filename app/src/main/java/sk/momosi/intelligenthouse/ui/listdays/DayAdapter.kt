package sk.momosi.intelligenthouse.ui.listdays

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import sk.momosi.intelligenthouse.databinding.ItemDayBinding
import java.time.LocalDate


class DayAdapter(
    var data: List<LocalDate> = emptyList()
) : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): DayViewHolder {
        val itemBinding = ItemDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DayViewHolder(itemBinding)
    }

    fun replaceData(data: List<LocalDate>) {
        this.data = data
        notifyDataSetChanged()
    }

    // ViewHolder
    inner class DayViewHolder(val binding: ItemDayBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LocalDate) {
            binding.day = item
            binding.executePendingBindings()
        }
    }
}

package az.khayalsharifli.presentation.flow.adapters

import android.location.Location
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import az.khayalsharifli.domain.model.Epic
import az.khayalsharifli.presentation.databinding.ItemEpicBinding
import az.khayalsharifli.presentation.base.BaseDiffUtil
import az.khayalsharifli.presentation.tools.ClickListener
import az.khayalsharifli.presentation.tools.Util.getAddressFromLocation

class HomeAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var items = emptyList<Epic>()

    class HomeViewHolder(val binding: ItemEpicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(epic: Epic) {
            val location = Location("Location").apply {
                latitude = epic.centroid_coordinates.lat
                longitude = epic.centroid_coordinates.lon
            }
            binding.tvCaption.text = epic.caption
            binding.tvDate.text = epic.date
            //binding.tvLocation.text = getAddressFromLocation(location, binding.root.context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemEpicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = items[position]
        holder.bindItem(item)
        holder.binding.root.setOnClickListener {
            clickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = items.size

    fun setData(newItems: List<Epic>) {
        val diffUtil = BaseDiffUtil(items, newItems)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        items = newItems
        diffUtilResult.dispatchUpdatesTo(this)
    }

}
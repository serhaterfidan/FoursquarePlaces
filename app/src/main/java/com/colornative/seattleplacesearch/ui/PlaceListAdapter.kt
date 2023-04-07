package com.colornative.seattleplacesearch.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.colornative.seattleplacesearch.databinding.ListItemVenueBinding
import com.colornative.seattleplacesearch.model.Place

class PlaceListAdapter(var context: Context,private val onItemClick: (Place) -> Unit) : ListAdapter<Place, PlaceListAdapter.ViewHolder>(PlaceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemVenueBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    inner class ViewHolder(private val binding: ListItemVenueBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Place, onItemClick: (Place) -> Unit) {
            binding.placeNameTextView.text = item.name
            binding.placeAddressTextView.text = item.location.address
            binding.placeDistanceTextView.text = "${item.distance}m"
            binding.placeCategoriesTextView.text = item.categories?.let {
                it.joinToString(", ") { category -> category.name }
            }
            binding.placeRelatedPlacesTextView.text = item.related_places?.results?.let {
                it.joinToString(", ")
            }
            // Load the image using Glide
            Glide.with(context)
                .load(item.categories.getOrNull(0)?.icon?.prefix+item.categories.getOrNull(0)?.icon?.suffix)
                .into(binding.placeImageView)
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }
}

class PlaceDiffCallback : DiffUtil.ItemCallback<Place>() {

    override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem.fsq_id == newItem.fsq_id
    }

    override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem == newItem
    }
}
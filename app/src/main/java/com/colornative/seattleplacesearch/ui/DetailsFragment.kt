package com.colornative.seattleplacesearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.colornative.seattleplacesearch.R
import com.colornative.seattleplacesearch.network.DetailsViewModel
import com.colornative.seattleplacesearch.databinding.FragmentDetailsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getVenueDetails(args.venueID)

        viewModel.place.observe(viewLifecycleOwner) { placeDetails ->

            // Set the place details
            binding.placeNameTextView.text = placeDetails.name
            binding.placeRatingBar.rating = (placeDetails.popularity ?: 0).toFloat()
            binding.placeAddressTextView.text = placeDetails.location.address
            binding.placeDistanceTextView.text = requireActivity().getString(R.string.place_distance) + placeDetails.distance.toString()
            // Load the image using Glide
            Glide.with(this)
                .load(placeDetails.categories.getOrNull(0)?.icon?.prefix+placeDetails.categories.getOrNull(0)?.icon?.suffix)
                .into(binding.placeImageView)
            binding.placeDescriptionTextView.text = placeDetails.description
            binding.placeHoursTextView.text = placeDetails.hours?.display

            // Initialize the MapView
            binding.placeMapView.onCreate(savedInstanceState)

            // Get the GoogleMap object
            binding.placeMapView.getMapAsync { googleMap ->
                // Set the map type
                googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL

                // Get the location of the place
                val location = LatLng(placeDetails.geocodes.main.latitude, placeDetails.geocodes.main.longitude)

                // Add a marker to the map
                googleMap.addMarker(MarkerOptions().position(location))

                // Move the camera to the location
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (!error.isNullOrBlank()) {
                Toast.makeText(requireContext(), "Error: $error", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
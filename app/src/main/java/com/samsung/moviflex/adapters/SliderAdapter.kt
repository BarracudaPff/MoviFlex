package com.samsung.moviflex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.samsung.moviflex.api.MovieApi
import com.samsung.moviflex.models.Movie
import com.samsung.moviflex.R
import com.samsung.moviflex.fragments.MovieFragmentDirections
import com.samsung.moviflex.fragments.TvShowFragmentDirections
import com.samsung.moviflex.databinding.SliderItemBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(
    private val fragment: Fragment,
    private var sliders: List<Movie>,
    private val isMovie: Boolean
) : SliderViewAdapter<SliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = SliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val slider = sliders[position]
        holder.bind(slider)
    }

    override fun getCount(): Int = sliders.size

    inner class ViewHolder(private val binding: SliderItemBinding) : SliderViewAdapter.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Glide.with(fragment)
                .load(MovieApi.PHOTO_BASE_URL + movie.posterPath)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageView)

            binding.root.setOnClickListener {
                if (isMovie) {
                    val action = MovieFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
                        movie.id,
                        movie.title
                    )
                    fragment.findNavController().navigate(action)
                } else {
                    val action = TvShowFragmentDirections.actionTvShowFragmentToTvShowDetailsFragment(
                        movie.id,
                        movie.name
                    )
                    fragment.findNavController().navigate(action)
                }
            }
        }
    }

    fun setData(sliders: List<Movie>) {
        this.sliders = sliders
        notifyDataSetChanged()
    }
}
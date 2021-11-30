package com.samsung.moviflex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.samsung.moviflex.api.MovieApi
import com.samsung.moviflex.models.TvShowDetails
import com.samsung.moviflex.R
import com.samsung.moviflex.databinding.SeasonItemBinding

class SeasonAdapter(
    private val fragment: Fragment,
    private val seasonList: List<TvShowDetails.Season>
) : RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder>() {
    private var onAttach = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val view = SeasonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeasonViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val season = seasonList[position]
        holder.bind(season)
    }

    override fun getItemCount(): Int = seasonList.size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                onAttach = false
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        super.onAttachedToRecyclerView(recyclerView)
    }

    inner class SeasonViewHolder(private val binding: SeasonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(season: TvShowDetails.Season) {
            val posterUrl: String = MovieApi.PHOTO_BASE_URL + season.posterPath

            binding.apply {
                Glide.with(fragment)
                    .load(posterUrl)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)

                seasonName.text = season.name
            }
        }
    }
}
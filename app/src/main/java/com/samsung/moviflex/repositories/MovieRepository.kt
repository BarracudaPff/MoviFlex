package com.samsung.moviflex.repositories

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.samsung.moviflex.*
import com.samsung.moviflex.api.ApiBuilder
import com.samsung.moviflex.api.MovieApi
import com.samsung.moviflex.models.*
import com.samsung.moviflex.paginations.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val apiBuilder: ApiBuilder,
    @ApplicationContext private val context: Context
) {
    var movieDetails: MovieDetails? = null
    var nowPlayingMovies: List<Movie>? = null
    var movieVideos: List<Video>? = null

    suspend fun getMovieDetails(movieId: Int) = withContext(Dispatchers.IO) {
        context.withToast {
            val response = apiBuilder.getMovieDetails(movieId)
            if (response.isSuccessful) {
                movieDetails = response.body()!!
            } else {
                showToast(context)
            }
        }
    }

    suspend fun getNowPlayingMovies() = withContext(Dispatchers.IO) {
        context.withToast {
            val response = apiBuilder.getNowPlayingMovies()
            if (response.isSuccessful) {
                nowPlayingMovies = response.body()!!.results
            } else {
                showToast(context)
            }
        }
    }

    suspend fun getMovieVideos(movieId: Int) = withContext(Dispatchers.IO) {
        context.withToast {
            val response = apiBuilder.getMovieVideos(movieId)
            if (response.isSuccessful) {
                movieVideos = response.body()!!.results
            } else {
                showToast(context)
            }
        }
    }

    fun getPopularMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PopularMoviePagingSource(movieApi) }
        ).liveData

    fun getTopRatedMovies() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { TopRatedMoviePagingSource(movieApi) }
    ).liveData

    fun getUpcomingMovies() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { UpcomingMoviePagingSource(movieApi) }
    ).liveData

    fun getSimilarMovies(movieId: Int) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { SimilarMoviePagingSource(movieId, movieApi) }
    ).liveData

    fun getGenreMovies(genreId: Int) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { MovieGenrePagingSource(genreId, movieApi) }
    ).liveData

}
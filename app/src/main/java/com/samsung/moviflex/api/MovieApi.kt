package com.samsung.moviflex.api

import com.samsung.moviflex.models.*
import retrofit2.Response
import retrofit2.http.*

interface MovieApi {
    //Movies
    @GET("movie/{movie_id}?api_key=$API_KEY")
    suspend fun getMovieDetails(@Path("movie_id") id: Int): Response<MovieDetails>

    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlayingMovies(): Response<MovieResponse>

    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieResponse

    @GET("movie/top_rated?api_key=$API_KEY")
    suspend fun getTopRatedMovies(@Query("page") page: Int): MovieResponse

    @GET("movie/upcoming?api_key=$API_KEY")
    suspend fun getUpcomingMovies(@Query("page") page: Int): MovieResponse

    @GET("movie/{movie_id}/similar?api_key=$API_KEY")
    suspend fun getSimilarMovies(@Path("movie_id") id: Int, @Query("page") page: Int): MovieResponse

    @GET("movie/{movie_id}/videos?api_key=$API_KEY")
    suspend fun getMovieVideos(@Path("movie_id") id: Int): Response<VideoResponse>

    @GET("discover/movie?api_key=$API_KEY")
    suspend fun getGenreMovies(@Query("with_genres") genreId: Int, @Query("page") page: Int): MovieResponse

    //Tv Shows
    @GET("tv/airing_today?api_key=$API_KEY")
    suspend fun getTvAiringToday(): Response<MovieResponse>

    @GET("tv/popular?api_key=$API_KEY")
    suspend fun getTvPopular(@Query("page") page: Int): MovieResponse

    @GET("tv/top_rated?api_key=$API_KEY")
    suspend fun getTvTopRated(@Query("page") page: Int): MovieResponse

    @GET("tv/on_the_air?api_key=$API_KEY")
    suspend fun getTvOnTheAir(@Query("page") page: Int): MovieResponse

    @GET("tv/{tvShow_id}/similar?api_key=$API_KEY")
    suspend fun getSimilarTvShows(@Path("tvShow_id") id: Int, @Query("page") page: Int): MovieResponse

    @GET("tv/{tvShow_id}/videos?api_key=$API_KEY")
    suspend fun getTvShowVideos(@Path("tvShow_id") id: Int): Response<VideoResponse>

    @GET("tv/{tvShow_id}?api_key=$API_KEY")
    suspend fun getTvShowDetails(@Path("tvShow_id") id: Int): Response<TvShowDetails>

    @GET("search/multi?api_key=$API_KEY")
    suspend fun getSearch(@Query("query") query: String, @Query("page") page: Int): MovieResponse

    @GET("discover/tv?api_key=$API_KEY")
    suspend fun getGenreTVShows(@Query("with_genres") genreId: Int, @Query("page") page: Int): MovieResponse

    companion object {
        private const val API_KEY = "<INSERT_HERE>"
        const val PHOTO_BASE_URL = "https://image.tmdb.org/t/p/w342"

        fun url(endpoint: String) = PHOTO_BASE_URL + endpoint

        init {
            if (API_KEY == "<INSERT_HERE>") {
                throw IllegalArgumentException(
                    "You need to pass api key for tmdb. " +
                            "You can find how to generate it here https://developers.themoviedb.org/3/getting-started/introduction"
                )
            }
        }
    }
}
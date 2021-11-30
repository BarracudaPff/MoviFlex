package com.samsung.moviflex.models

data class Genre(
    val name: String,
    val id: Int
)

val movieGenres = listOf(
    Genre("Action", 28),
    Genre("Adventure", 12),
    Genre("Animation", 16),
    Genre("Comedy", 35),
    Genre("Fantasy", 14),
    Genre("Horror", 27),
    Genre("Romance", 10749),
    Genre("Science Fiction", 878),
    Genre("Thriller", 53),
    Genre("War", 10752),
//    Genre("Western", 37),
)

val tvShowGenres = listOf(
    Genre("Documentary", 99),
    Genre("History", 36),
    Genre("Music", 10402),
    Genre("Mystery", 9648),
    Genre("Family", 10751),
    Genre("Drama", 18),
    Genre("Crime", 80),
    Genre("TV Movie", 10770),
)
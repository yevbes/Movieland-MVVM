package com.yevbes.movieland.service.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.yevbes.movieland.service.Movie

@Dao
interface MovieDao {

    @Insert
    fun insert(movie: Movie)

    @Update
    fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("DELETE FROM movie")
    fun deleteAllMovies()

    @Query("SELECT * FROM movie ORDER BY title")
    fun getAllMovies() : LiveData<List<Movie>>
}
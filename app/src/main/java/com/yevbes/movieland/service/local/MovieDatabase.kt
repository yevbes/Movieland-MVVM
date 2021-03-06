package com.yevbes.movieland.service.local

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.yevbes.movieland.service.Movie
import timber.log.Timber

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private lateinit var instance: MovieDatabase
        @Synchronized
        fun getInstance(context: Context): MovieDatabase {
            if (!Companion::instance.isInitialized) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java, "movie_database"
                )
//                        TODO: Remove fallback
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
            return instance
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Timber.tag("RoomCallback").v("Database is Created")
            }
        }
    }

}
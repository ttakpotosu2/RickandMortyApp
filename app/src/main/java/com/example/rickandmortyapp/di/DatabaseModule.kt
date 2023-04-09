package com.example.rickandmortyapp.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmortyapp.data.local.CharacterResultsDatabase
import com.example.rickandmortyapp.data.local.EpisodesResultsDatabase
import com.example.rickandmortyapp.data.local.LocationsResultsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCharacterDatabase(
        @ApplicationContext context: Context
    ): CharacterResultsDatabase{
        return Room.databaseBuilder(
            context,
            CharacterResultsDatabase::class.java,
            "characters_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocationDatabase(
        @ApplicationContext context: Context
    ): LocationsResultsDatabase{
        return Room.databaseBuilder(
            context,
            LocationsResultsDatabase::class.java,
            "locations_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideEpisodeDatabase(
        @ApplicationContext context: Context
    ): EpisodesResultsDatabase{
        return Room.databaseBuilder(
            context,
            EpisodesResultsDatabase::class.java,
            "episodes_database"
        ).build()
    }
}
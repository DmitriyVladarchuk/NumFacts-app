package com.example.numbersapp.di

import android.app.Application
import com.example.numbersapp.data.API.NumbersApiService
import com.example.numbersapp.data.local.FactDAO
import com.example.numbersapp.data.local.LocalDB
import com.example.numbersapp.data.local.PreferenceDataSource
import com.example.numbersapp.data.repository.DatabaseRepositoryImpl
import com.example.numbersapp.data.repository.FactRepositoryImpl
import com.example.numbersapp.data.repository.SettingsRepositoryImpl
import com.example.numbersapp.domain.repository.DatabaseRepository
import com.example.numbersapp.domain.repository.FactRepository
import com.example.numbersapp.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun  providerFactDAO(app: Application): FactDAO {
        return LocalDB.getBataBase(app).dao()
    }

    @Provides
    @Singleton
    fun providerPreferenceDataSource(app: Application): PreferenceDataSource {
        return PreferenceDataSource(app)
    }

    @Provides
    @Singleton
    fun providerFactRepository(numbersApiService: NumbersApiService): FactRepository {
        return FactRepositoryImpl(numbersApiService)
    }

    @Provides
    @Singleton
    fun providerDataBaseRepository(dao: FactDAO): DatabaseRepository {
        return DatabaseRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun providerSettingsRepository(dataSource: PreferenceDataSource): SettingsRepository {
        return SettingsRepositoryImpl(dataSource)
    }
}
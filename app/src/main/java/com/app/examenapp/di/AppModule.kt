package com.app.examenapp.di

import android.content.Context
import com.app.examenapp.data.local.preferences.CountryPreferences
import com.app.examenapp.data.remote.api.CountriesApi
import com.app.examenapp.data.repository.CountryRepositoryImpl
import com.app.examenapp.domain.repository.CountryRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideCountriesApi(retrofit: Retrofit): CountriesApi = retrofit.create(CountriesApi::class.java)

    @Provides
    @Singleton
    fun providePokemonPreferences(
        @ApplicationContext context: Context,
        gson: Gson
    ): CountryPreferences {
        return CountryPreferences(context, gson)
    }

    @Provides
    @Singleton
    fun provideCountriesRepository(
        api: CountriesApi,
        preferences: CountryPreferences
    ): CountryRepository = CountryRepositoryImpl(api,preferences)
}

package com.app.examenapp.di

import com.app.examenapp.data.remote.api.CountriesApi
import com.app.examenapp.data.repository.CountryRepositoryImpl
import com.app.examenapp.domain.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
            .baseUrl("(https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCountriesApi(retrofit: Retrofit): CountriesApi = retrofit.create(CountriesApi::class.java)

    @Provides
    @Singleton
    fun provideCountriesRepository(api: CountriesApi): CountryRepository = CountryRepositoryImpl(api)
}

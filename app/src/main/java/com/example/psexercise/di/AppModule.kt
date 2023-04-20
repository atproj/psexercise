package com.example.psexercise.di

import android.content.Context
import com.example.psexercise.data.LocalDataSource
import com.example.psexercise.data.MockDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideMockDataSrc(@ApplicationContext context: Context, gson: Gson): LocalDataSource {
        return MockDataSource(context, gson)
    }
}
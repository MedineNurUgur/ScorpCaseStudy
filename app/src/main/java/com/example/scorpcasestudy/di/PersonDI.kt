package com.example.scorpcasestudy.di

import com.example.scorpcasestudy.data.api.DataSourceService
import com.example.scorpcasestudy.data.repository.PersonRepository
import com.example.scorpcasestudy.data.repository.impl.PersonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersonDI {

    @Provides
    @Singleton
    fun provideDataSource(): DataSourceService = DataSourceService()

    @Provides
    @Singleton
    fun providePersonRepository(dataSourceService: DataSourceService): PersonRepository = PersonRepositoryImpl(dataSourceService)

}
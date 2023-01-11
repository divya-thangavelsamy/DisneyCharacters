package com.codewithdivya.disneycharacters.data.datasource

import com.codewithdivya.disneycharacters.business.repositoryInterface.DisneyCharacterListRepoInterface
import com.codewithdivya.disneycharacters.data.mappers.DisneyCharacterListMapper
import com.codewithdivya.disneycharacters.data.repository.DisneyCharactersRepositoryImpl
import com.codewithdivya.disneycharacters.data.service.DisneyCharactersService
import com.codewithdivya.disneycharacters.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofitClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesDisneyListCharacters(retrofit: Retrofit): DisneyCharactersApi =
        retrofit.create(DisneyCharactersApi::class.java)

    @Provides
    @Singleton
    fun providesNetworkDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideLDisneyListRepository(
        mapper: DisneyCharacterListMapper,
        webService: DisneyCharactersService,
    ): DisneyCharacterListRepoInterface {
        return DisneyCharactersRepositoryImpl(mapper, webService)
    }

}

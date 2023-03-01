package dzianis.trakhimik.gifdiscover.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dzianis.trakhimik.gifdiscover.data.remote.GifApi
import dzianis.trakhimik.gifdiscover.data.repository.GifsRepositoryImpl
import dzianis.trakhimik.gifdiscover.domain.repository.GifsRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideWeatherApi(): GifApi {
        return Retrofit.Builder()
            .baseUrl("https://api.giphy.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideGifsRepository(api: GifApi): GifsRepository {
        return GifsRepositoryImpl(api)
    }
}
package ashutosh.stackExchangeTask.di

import ashutosh.stackExchangeTask.Constants
import ashutosh.stackExchangeTask.api.RetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    fun providesRetrofit(): RetrofitAPI = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()
        .create(RetrofitAPI::class.java)
}
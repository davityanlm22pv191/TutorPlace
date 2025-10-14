package com.example.tutorplace.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.tutorplace.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	@Singleton
	fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
		return ChuckerInterceptor.Builder(context)
			.collector(ChuckerCollector(context, showNotification = true))
			.maxContentLength(250_000L)
			.build()
	}

	@Provides
	@Singleton
	fun provideOkHttpClient(chucker: ChuckerInterceptor): OkHttpClient {
		return OkHttpClient.Builder()
			.addInterceptor(chucker)
			.build()
	}

	@Provides
	@Singleton
	fun provideRetrofit(client: OkHttpClient): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BuildConfig.SERVER_URL)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}
}
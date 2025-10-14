package com.example.tutorplace.network

import com.example.tutorplace.data.onboarding.OnboardingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object ApiDataModule {

	@Provides
	fun provideOnboardingService(retrofit: Retrofit): OnboardingService {
		return retrofit.create(OnboardingService::class.java)
	}
}
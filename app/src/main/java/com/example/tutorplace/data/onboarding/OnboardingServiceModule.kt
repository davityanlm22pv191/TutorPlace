package com.example.tutorplace.data.onboarding

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OnboardingServiceModule {

	@Binds
	@Singleton
	abstract fun bind(impl: OnboardingServiceImpl): OnboardingService
}
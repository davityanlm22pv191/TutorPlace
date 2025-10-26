package com.example.tutorplace.data.profile

import com.example.tutorplace.data.profile.storage.ProfileStorage
import com.example.tutorplace.data.profile.storage.ProfileStorageImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class ProfileModule {
	@Binds
	@Singleton
	abstract fun bind(impl: ProfileStorageImpl): ProfileStorage
}
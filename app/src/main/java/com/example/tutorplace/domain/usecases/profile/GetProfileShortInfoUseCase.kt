package com.example.tutorplace.domain.usecases.profile

import com.example.tutorplace.data.profile.ProfileService
import com.example.tutorplace.data.profile.storage.ProfileStorage
import javax.inject.Inject

class GetProfileShortInfoUseCase @Inject constructor(
	private val profileService: ProfileService,
	private val profileStorage: ProfileStorage,
) {
	// Mock Image Url https://iili.io/K4WLI4a.png
	suspend fun execute(): Result<Unit> {
		val response = profileService.getProfileShortInfo()
		return if (response.isSuccessful) {
			response.body()?.first()?.let { profileShortInfo ->
				profileStorage.setProfileShortInfo(profileShortInfo)
			}
			Result.success(Unit)
		} else {
			Result.failure(Throwable(response.message()))
		}
	}
}
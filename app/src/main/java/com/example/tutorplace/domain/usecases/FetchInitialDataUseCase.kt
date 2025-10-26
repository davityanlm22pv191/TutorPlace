package com.example.tutorplace.domain.usecases

import com.example.tutorplace.domain.usecases.profile.GetProfileShortInfoUseCase
import javax.inject.Inject

class FetchInitialDataUseCase @Inject constructor(
	private val getProfileShortInfoUseCase: GetProfileShortInfoUseCase,
) {
	suspend fun execute() {
		getProfileShortInfoUseCase.execute()
	}
}
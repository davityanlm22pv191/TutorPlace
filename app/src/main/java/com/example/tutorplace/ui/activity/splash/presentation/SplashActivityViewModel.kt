package com.example.tutorplace.ui.activity.splash.presentation

import com.example.tutorplace.data.credentials.CredentialsStorage
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityEvent.ResolveNextScreen
import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashActivityViewModel @Inject constructor(
	private val credentialsStorage: CredentialsStorage
) : BaseViewModel<SplashActivityEvent, SplashActivityState, SplashActivityEffect>() {

	override fun initialState() = SplashActivityState()

	override fun onEvent(event: SplashActivityEvent) = when (event) {
		is ResolveNextScreen -> resolveNextScreen()
	}

	private fun resolveNextScreen() {
		if (credentialsStorage.isAuthorized()) {
			sendEffect(SplashActivityEffect.NavigateToHome)
		} else {
			sendEffect(SplashActivityEffect.NavigateToAuthFlow)
		}
	}
}
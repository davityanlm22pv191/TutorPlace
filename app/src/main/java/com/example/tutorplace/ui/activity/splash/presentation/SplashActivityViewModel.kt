package com.example.tutorplace.ui.activity.splash.presentation

import com.example.tutorplace.data.credentials.CredentialsStorage
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityCommand.ResolveNextScreen
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityEvent.NavigateToAuth
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityEvent.NavigateToHome
import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashActivityViewModel @Inject constructor(
	private val credentialsStorage: CredentialsStorage
) : BaseViewModel<SplashActivityEvent, SplashActivityCommand>() {

	override fun handleCommand(command: SplashActivityCommand) = when (command) {
		ResolveNextScreen -> resolveNextScreen()
	}

	private fun resolveNextScreen() {
		if (credentialsStorage.isAuthorized()) {
			setEvent(NavigateToHome)
		} else {
			setEvent(NavigateToAuth)
		}
	}
}
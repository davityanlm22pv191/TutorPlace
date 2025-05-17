package com.example.tutorplace.ui.activity.splash.presentation

import com.example.tutorplace.ui.base.BaseCommand

sealed interface SplashActivityCommand: BaseCommand {
	data object ResolveNextScreen: SplashActivityCommand
}
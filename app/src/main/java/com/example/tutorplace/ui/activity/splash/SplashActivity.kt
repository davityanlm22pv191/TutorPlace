package com.example.tutorplace.ui.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tutorplace.ui.activity.main.MainActivity
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityCommand.ResolveNextScreen
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityEvent
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityEvent.NavigateToAuth
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityEvent.NavigateToHome
import com.example.tutorplace.ui.activity.splash.presentation.SplashActivityViewModel
import com.example.tutorplace.ui.navigation.Destinations
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

	private val viewModel by viewModels<SplashActivityViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		val splashScreen = installSplashScreen()
		observeViewModel()

		splashScreen.setOnExitAnimationListener { splashScreenView ->
			splashScreenView.view
				.animate()
				.alpha(0f)
				.scaleX(0.9f)
				.scaleY(0.9f)
				.setDuration(500L)
				.withEndAction {
					splashScreenView.remove()
					resolveNextActivity()
					finish()
				}.start()
		}
		super.onCreate(savedInstanceState)
	}

	private fun resolveNextActivity() = viewModel.handleCommand(ResolveNextScreen)

	private fun observeViewModel() {
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.event.collect { splashActivityEvent ->
					handlingViewModelEvent(splashActivityEvent)
				}
			}
		}
	}

	private fun handlingViewModelEvent(event: SplashActivityEvent) {
		val startDestination = when(event) {
			NavigateToAuth -> Destinations.AuthorizationFlow.FLOW_ROUTE
			NavigateToHome -> Destinations.Home.route
		}
		val intent = Intent(this, MainActivity::class.java).apply {
			putExtra("start_destination", startDestination)
		}
		startActivity(intent)
		finish()
	}
}
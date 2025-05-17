package com.example.tutorplace.ui.fragment.auth.authorization

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import com.example.tutorplace.ui.base.BaseFragment
import com.example.tutorplace.ui.fragment.auth.authorization.presentation.AuthorizationCommand
import com.example.tutorplace.ui.fragment.auth.authorization.presentation.AuthorizationEvent
import com.example.tutorplace.ui.fragment.auth.authorization.presentation.AuthorizationState
import com.example.tutorplace.ui.fragment.auth.authorization.presentation.AuthorizationViewModel
import com.example.tutorplace.ui.fragment.auth.authorization.ui.AuthorizationScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationFragment :
	BaseFragment<AuthorizationState, AuthorizationEvent, AuthorizationCommand>() {

	override val viewModel by viewModels<AuthorizationViewModel>()

	@Composable
	override fun Screen(state: AuthorizationState, onCommand: (AuthorizationCommand) -> Unit) =
		AuthorizationScreen(state, onCommand)

	override fun handlingViewModelEvent(event: AuthorizationEvent) = Unit
}
package com.example.tutorplace.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tutorplace.ui.theme.TutorPlaceTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<State : BaseState, Event : BaseEvent, Command : BaseCommand> :
	Fragment() {

	abstract val viewModel: BaseViewModel<Event, Command>

	@Stable
	protected lateinit var state: MutableStateFlow<State>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		observeViewModel()
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	) = ComposeView(requireContext()).apply {
		setContent { Screen() }
	}

	@Composable
	abstract fun Screen(state: State, onCommand: (Command) -> Unit)

	@Composable
	protected fun Screen() {
		val state by state.asStateFlow().collectAsState()
		val onCommand = remember(viewModel) {
			{ command: Command -> viewModel.handleCommand(command) }
		}
		TutorPlaceTheme {
			Screen(state, onCommand)
		}
	}

	abstract fun handlingViewModelEvent(event: Event)

	private fun observeViewModel() {
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.event.collect { event -> handlingViewModelEvent(event) }
			}
		}
	}
}
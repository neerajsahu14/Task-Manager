package com.neerajsahu14.notesapp.presentation.ui.event

import com.neerajsahu14.notesapp.data.modal.Tasks

sealed interface HomeUiEvents {
    data object ToggleDialog : HomeUiEvents
    data class AddTasks(val task: Tasks): HomeUiEvents
    data class CompleteTasks(val taskIndex: Int) : HomeUiEvents
    data class OpenTask(val task: Tasks) : HomeUiEvents
    data object CloseTaskDialog : HomeUiEvents

}
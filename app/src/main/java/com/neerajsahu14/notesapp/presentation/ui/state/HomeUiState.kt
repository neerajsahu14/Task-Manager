package com.neerajsahu14.notesapp.presentation.ui.state

import com.neerajsahu14.notesapp.data.modal.Tasks
import java.lang.Error

data class HomeUiState(
    val showDialog: Boolean = false,
    val openTask: Boolean = false,
    val selectedTask : Tasks? = null ,
    val taskList: MutableList<Tasks> = mutableListOf(),
    val completedTask: MutableList<Tasks> = mutableListOf()
)

package com.neerajsahu14.notesapp.domain.usecase

import com.neerajsahu14.notesapp.data.modal.Tasks
import com.neerajsahu14.notesapp.domain.repo.HomeRepository
import com.neerajsahu14.notesapp.utils.resultCallBack

class AddTaskUseCase(private val homeRepository: HomeRepository) {
    suspend fun execute(
        task: Tasks,
        resultCallBack: resultCallBack
    ){
        homeRepository.addTaskCall(task, resultCallBack)
    }
}
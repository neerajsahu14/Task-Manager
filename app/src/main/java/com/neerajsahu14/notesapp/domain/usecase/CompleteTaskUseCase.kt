package com.neerajsahu14.notesapp.domain.usecase

import com.neerajsahu14.notesapp.data.modal.Tasks
import com.neerajsahu14.notesapp.domain.repo.HomeRepository
import com.neerajsahu14.notesapp.utils.resultCallBack

class CompleteTaskUseCase(private val homeRepository: HomeRepository) {
    suspend fun execute(
        tasks: Tasks,
        resultCallBack: resultCallBack
    ){
        homeRepository.completeTaskCall(tasks,resultCallBack)
    }
}
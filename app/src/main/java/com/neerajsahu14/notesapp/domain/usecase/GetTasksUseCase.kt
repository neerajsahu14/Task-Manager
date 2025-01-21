package com.neerajsahu14.notesapp.domain.usecase

import com.neerajsahu14.notesapp.domain.repo.HomeRepository
import com.neerajsahu14.notesapp.utils.resultListCallBack

class GetTasksUseCas(private val homeRepository: HomeRepository) {
    suspend fun execute(
        resultListCallBack: resultListCallBack
    ){
        homeRepository.getTasks(resultListCallBack)
    }
}
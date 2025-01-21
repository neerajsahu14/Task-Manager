package com.neerajsahu14.notesapp.domain.repo

import com.neerajsahu14.notesapp.data.modal.Tasks
import com.neerajsahu14.notesapp.utils.resultCallBack
import com.neerajsahu14.notesapp.utils.resultListCallBack

interface HomeRepository {
    suspend fun addTaskCall(task: Tasks, resultCallBack: resultCallBack)

    suspend fun completeTaskCall(
        task: Tasks,
        resultCallBack: resultCallBack
    )

    suspend fun getCompletedTasks(
        resultListCallBack: resultListCallBack
    )
    suspend fun getTasks(
        resultListCallBack: resultListCallBack
    )
    suspend fun getAllTasks(
        resultListCallBack: resultListCallBack
    )

}
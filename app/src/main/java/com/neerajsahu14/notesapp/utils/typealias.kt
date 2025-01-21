package com.neerajsahu14.notesapp.utils

import com.neerajsahu14.notesapp.data.modal.Tasks


typealias resultCallBack = (result: TaskResult<Tasks>) -> Unit
typealias resultListCallBack = (result: TaskResult<List<Tasks>>) -> Unit
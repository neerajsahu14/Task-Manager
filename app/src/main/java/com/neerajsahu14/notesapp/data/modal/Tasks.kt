package com.neerajsahu14.notesapp.data.modal

data class Tasks (
    var id: Int,
    var taskName: String,
    var taskDetail: String,
    var taskEndDate: String,
    var taskFiles: MutableList<String>
)
package com.example.week2native.viewmodel

import androidx.lifecycle.ViewModel
import com.example.week2native.model.Task
import com.example.week2native.model.mockTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(mockTasks)
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask.asStateFlow()

    val addTaskDialogVisible = MutableStateFlow<Boolean>(false)

    init {
        _tasks.value = mockTasks

    }
    fun openTask(id: Int) {
        val task = _tasks.value.find { it.id == id }
        _selectedTask.value = task
    }
    fun addTask(task: Task) {
        _tasks.value += task
        addTaskDialogVisible.value = false
    }

    fun removeTask(id: Int) {
        _tasks.value = _tasks.value.filterNot { it.id == id }
    }

    fun toggleDone(id: Int) {
        _tasks.value = _tasks.value.map {
            if (it.id == id) it.copy(done = !it.done)
            else it
        }
    }

    fun updateTask(task: Task) {
        _tasks.value = _tasks.value.map {
            if (it.id == task.id) task else it
        }
        _selectedTask.value = null
    }

    fun selectTask(task: Task?) {
        _selectedTask.value = task
    }

    fun closeDialog() {
        _selectedTask.value = null
    }


}

package woowacourse.kanban.board.domain

import java.util.UUID

data class Tasks(val initialTasks: MutableList<Task>) {
    private val _tasks = initialTasks
    val tasks: List<Task> get() = _tasks.toList()

    val totalCount: Int
        get() = _tasks.size

    val completedRate: Int
        get() = if (_tasks.isEmpty()) 0 else (countByState(TaskState.DONE).toDouble() / _tasks.size * 100).toInt()

    fun addTask(task: Task) {
        _tasks.add(task)
    }

    fun changeTaskState(taskId: UUID, taskState: TaskState) {
        val index = _tasks.indexOfFirst { it.id == taskId }
        if (index != -1) {
            _tasks[index] = _tasks[index].changeTaskState(taskState)
        }
    }

    fun updateTask(taskId: UUID, task: Task) {
        val index = _tasks.indexOfFirst { it.id == taskId }
        if (index != -1) {
            _tasks[index] = task
        }
    }

    fun deleteTask(taskId: UUID) {
        _tasks.removeIf { it.id == taskId }
    }

    fun countByState(taskState: TaskState): Int {
        return _tasks.count { it.taskState == taskState }
    }

    fun getTasksByState(taskState: TaskState): List<Task> {
        return _tasks.filter { it.taskState == taskState }
    }
}

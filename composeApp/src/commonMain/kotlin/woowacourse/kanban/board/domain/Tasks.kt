package woowacourse.kanban.board.domain

import java.util.UUID

data class Tasks(val tasks: List<Task>) {
    val totalCount: Int
        get() = tasks.size

    val completedRate: Int
        get() = if (tasks.isEmpty()) 0 else (countByState(TaskState.DONE).toDouble() / tasks.size * 100).toInt()

    fun addTask(task: Task): Tasks {
        return copy(tasks = tasks + task)
    }

    fun changeTaskState(taskId: UUID, taskState: TaskState): Tasks {
        return copy(
            tasks = tasks.map {
                if (it.id == taskId) {
                    it.changeTaskState(taskState)
                } else {
                    it
                }
            },
        )
    }

    fun updateTask(taskId: UUID, task: Task): Tasks {
        return copy(
            tasks = tasks.map {
                if (it.id == taskId) {
                    task
                } else {
                    it
                }
            },
        )
    }

    fun countByState(taskState: TaskState): Int {
        return tasks.count { it.taskState == taskState }
    }

    fun getTasksByState(taskState: TaskState): List<Task> {
        return tasks.filter { it.taskState == taskState }
    }
}

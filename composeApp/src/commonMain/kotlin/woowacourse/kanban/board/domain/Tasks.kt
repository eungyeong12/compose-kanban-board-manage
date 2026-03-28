package woowacourse.kanban.board.domain

data class Tasks(val tasks: List<Task>) {
    val totalCount: Int
        get() = tasks.size

    val completedRate: Int
        get() = if (tasks.isEmpty()) 0 else (countByState(TaskState.DONE).toDouble() / tasks.size * 100).toInt()

    fun addTask(task: Task): Tasks {
        return copy(tasks = tasks + task)
    }

    fun changeTaskState(taskId: Int, taskState: TaskState): Tasks {
        return copy(
            tasks = tasks.mapIndexed { index, task ->
                if (index == taskId) {
                    task.changeTaskState(taskState)
                } else {
                    task
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

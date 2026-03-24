package woowacourse.kanban.board.domain

data class Tasks(val tasks: List<Task>) {
    val totalCount: Int = tasks.size
    fun countByState(taskState: TaskState): Int = tasks.count { it.taskState == taskState }
    fun completedRate(): Int = if (tasks.isEmpty()) 0 else (countByState(TaskState.DONE).toDouble() / tasks.size * 100).toInt()
    fun getTasksByState(taskState: TaskState): List<Task> = tasks.filter { it.taskState == taskState }
}

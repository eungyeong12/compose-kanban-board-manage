package woowacourse.kanban.board.domain

data class Task(
    val id: Int = ++idCounter,
    val title: String,
    val content: String = "",
    val tags: List<String> = emptyList(),
    val taskState: TaskState = TaskState.TO_DO,
    val author: String = "다이노",
) {

    fun changeTaskState(taskState: TaskState): Task = copy(taskState = taskState)

    companion object {
        private var idCounter = 0
    }
}

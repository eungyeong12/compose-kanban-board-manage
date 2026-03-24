package woowacourse.kanban.board.domain

data class Task(
    val title: String,
    val content: String = "",
    val tags: List<String> = emptyList(),
    val taskState: TaskState = TaskState.TO_DO,
    val author: String = "다이노",
)

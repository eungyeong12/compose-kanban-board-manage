package woowacourse.kanban.board.ui.board.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.UUID
import woowacourse.kanban.board.domain.Task
import woowacourse.kanban.board.domain.TaskState
import woowacourse.kanban.board.domain.Tasks

class ProjectState(val id: UUID = UUID.randomUUID(), val name: String, initialTasks: List<Task> = emptyList()) {
    var tasks by mutableStateOf(Tasks(initialTasks.toMutableList()))
        private set

    fun addTask(task: Task) {
        val newTasks = Tasks(tasks.tasks.toMutableList())
        newTasks.addTask(task)
        tasks = newTasks
    }

    fun changeTaskState(taskId: UUID, taskState: TaskState) {
        val newTasks = Tasks(tasks.tasks.toMutableList())
        newTasks.changeTaskState(taskId, taskState)
        tasks = newTasks
    }

    fun updateTask(taskId: UUID, task: Task) {
        val newTasks = Tasks(tasks.tasks.toMutableList())
        newTasks.updateTask(taskId, task)
        tasks = newTasks
    }

    fun deleteTask(taskId: UUID) {
        val newTasks = Tasks(tasks.tasks.toMutableList())
        newTasks.deleteTask(taskId)
        tasks = newTasks
    }
}

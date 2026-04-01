package woowacourse.kanban.board.domain

import java.util.UUID

data class Project(val id: UUID = UUID.randomUUID(), val name: String, val tasks: Tasks = Tasks(emptyList())) {
    fun addTask(task: Task): Project {
        return copy(tasks = tasks.addTask(task))
    }

    fun changeTaskState(taskId: UUID, taskState: TaskState): Project {
        return copy(tasks = tasks.changeTaskState(taskId, taskState))
    }

    fun updateTask(taskId: UUID, task: Task): Project {
        return copy(tasks = tasks.updateTask(taskId, task))
    }

    fun deleteTask(taskId: UUID): Project {
        return copy(tasks = tasks.deleteTask(taskId))
    }
}

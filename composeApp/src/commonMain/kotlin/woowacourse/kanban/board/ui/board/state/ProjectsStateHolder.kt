package woowacourse.kanban.board.ui.board.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.UUID
import woowacourse.kanban.board.domain.Project
import woowacourse.kanban.board.domain.Task
import woowacourse.kanban.board.domain.TaskState

class ProjectsStateHolder(initialProjects: List<Project> = emptyList()) {
    private var _projects by mutableStateOf(initialProjects)
    private var _selectedProjectId by mutableStateOf(if (initialProjects.isNotEmpty()) initialProjects.first().id else null)

    val projects: List<Project> get() = _projects
    val selectedProject: Project? get() = _projects.find { it.id == _selectedProjectId }

    fun selectProject(projectId: UUID) {
        _selectedProjectId = projectId
    }

    fun addTask(projectId: UUID, task: Task) {
        _projects = _projects.map {
            if (it.id == projectId) {
                it.addTask(task)
            } else {
                it
            }
        }
    }

    fun changeTaskState(projectId: UUID, taskId: UUID, taskState: TaskState) {
        _projects = _projects.map {
            if (it.id == projectId) {
                it.changeTaskState(taskId, taskState)
            } else {
                it
            }
        }
    }
}

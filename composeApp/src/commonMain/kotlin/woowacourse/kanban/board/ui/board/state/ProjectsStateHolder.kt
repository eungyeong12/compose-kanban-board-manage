package woowacourse.kanban.board.ui.board.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.UUID
import woowacourse.kanban.board.domain.Project
import woowacourse.kanban.board.domain.Task
import woowacourse.kanban.board.domain.TaskState

class ProjectsStateHolder(val projects: List<Project> = emptyList()) {
    private var _uiState by mutableStateOf(
        ProjectsUiState(
            projects = projects,
            selectedProjectId = if (projects.isNotEmpty()) projects.first().id else null,
        ),
    )

    val uiState: ProjectsUiState get() = _uiState

    fun selectProject(projectId: UUID) {
        _uiState = _uiState.copy(selectedProjectId = projectId)
    }

    fun addTask(projectId: UUID, task: Task) {
        _uiState = _uiState.copy(
            projects = _uiState.projects.map {
                if (it.id == projectId) {
                    it.addTask(task)
                } else {
                    it
                }
            },
        )
    }

    fun changeTaskState(projectId: UUID, taskId: UUID, taskState: TaskState) {
        _uiState = _uiState.copy(
            projects = _uiState.projects.map {
                if (it.id == projectId) {
                    it.changeTaskState(taskId, taskState)
                } else {
                    it
                }
            },
        )
    }
}

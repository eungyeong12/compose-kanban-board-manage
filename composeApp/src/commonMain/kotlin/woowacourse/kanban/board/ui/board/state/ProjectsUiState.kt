package woowacourse.kanban.board.ui.board.state

import java.util.UUID
import woowacourse.kanban.board.domain.Project

data class ProjectsUiState(val projects: List<Project> = emptyList(), val selectedProjectId: UUID? = null) {
    val selectedProject: Project?
        get() = projects.find { it.id == selectedProjectId }
}

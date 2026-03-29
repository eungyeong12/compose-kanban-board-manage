package woowacourse.kanban.board.ui.board

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.UUID
import woowacourse.kanban.board.domain.Task
import woowacourse.kanban.board.domain.TaskState
import woowacourse.kanban.board.ui.board.components.Sidebar
import woowacourse.kanban.board.ui.board.state.ProjectsStateHolder

@Composable
fun ProjectScreen(
    stateHolder: ProjectsStateHolder,
    onProjectChange: (UUID) -> Unit,
    onTaskCreated: (UUID, Task) -> Unit,
    onTaskStateChange: (UUID, UUID, TaskState) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState = stateHolder.uiState
    val projects = uiState.projects
    val selectedProject = uiState.selectedProject
    val authors = listOf("다이노", "페임스")

    Row(
        modifier = modifier,
    ) {
        Sidebar(
            projects = projects,
            selectedProject = uiState.selectedProject,
            onProjectChange = { onProjectChange(it.id) },
            modifier = Modifier.width(255.dp),
        )

        if (selectedProject != null) {
            Board(
                projectName = selectedProject.name,
                tasks = selectedProject.tasks,
                onTaskCreated = { onTaskCreated(selectedProject.id, it) },
                onTaskStateChange = { taskId, targetState ->
                    onTaskStateChange(selectedProject.id, taskId, targetState)
                },
                authors = authors,
                modifier = Modifier.size(width = 1295.dp, height = 909.dp),
            )
        }
    }
}

@Preview
@Composable
private fun ProjectScreenPreview() {
    ProjectScreen(
        stateHolder = remember { ProjectsStateHolder() },
        onProjectChange = { },
        onTaskCreated = { _, _ -> },
        onTaskStateChange = { _, _, _ -> },
    )
}

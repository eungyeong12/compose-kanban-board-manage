package woowacourse.kanban.board

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.kanban.board.domain.Project
import woowacourse.kanban.board.ui.board.ProjectScreen
import woowacourse.kanban.board.ui.board.state.ProjectsStateHolder

@Preview(showBackground = true)
@Composable
fun App() {
    val projects = listOf(
        Project(name = "Compose1"),
        Project(name = "Compose2"),
        Project(name = "Compose3너무너무길다란이름"),
    )

    val stateHolder = remember { ProjectsStateHolder(projects) }

    val authors = listOf("다이노", "페임스")
    val authorsWithNone = listOf("없음") + authors

    ProjectScreen(
        stateHolder = stateHolder,
        onProjectChange = { projectId -> stateHolder.selectProject(projectId) },
        onTaskCreated = { projectId, task -> stateHolder.addTask(projectId, task) },
        onTaskUpdated = { projectId, taskId, task ->
            stateHolder.updateTask(projectId, taskId, task)
        },
        onTaskDeleted = { projectId, taskId ->
            stateHolder.deleteTask(projectId, taskId)
        },
        onTaskStateChange = { projectId, taskId, taskState ->
            stateHolder.changeTaskState(projectId, taskId, taskState)
        },
        modifier = Modifier.fillMaxSize(),
    )
}

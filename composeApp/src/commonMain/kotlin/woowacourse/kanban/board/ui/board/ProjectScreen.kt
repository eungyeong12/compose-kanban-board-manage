package woowacourse.kanban.board.ui.board

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.domain.Project
import woowacourse.kanban.board.domain.Tasks
import woowacourse.kanban.board.ui.board.components.Sidebar

@Composable
fun ProjectScreen(projects: List<Project>, onProjectChange: (Project, Tasks) -> Unit, modifier: Modifier = Modifier) {
    val authors = listOf("다이노", "페임스")
    var selectedProject by remember { mutableStateOf(projects.first()) }
    var selectedTasks by remember { mutableStateOf(projects.first().tasks) }

    Row(
        modifier = modifier,
    ) {
        Sidebar(
            projects = projects,
            selectedProject = selectedProject,
            onProjectChange = {
                selectedProject = it
                selectedTasks = it.tasks
                onProjectChange(it, it.tasks)
            },
            modifier = Modifier.width(255.dp),
        )

        Board(
            tasks = selectedTasks,
            onTaskCreated = {
                selectedTasks = selectedTasks.copy(tasks = selectedTasks.copy(tasks = selectedTasks.addTask(it)).tasks)
                onProjectChange(selectedProject, selectedTasks)
            },
            onTaskStateChange = { idx, targetStatus ->
                selectedTasks = selectedTasks.copy(tasks = selectedTasks.copy(tasks = selectedTasks.fixStatus(idx, targetStatus)).tasks)
                onProjectChange(selectedProject, selectedTasks)
            },
            authors = authors,
            modifier = Modifier.size(width = 1295.dp, height = 909.dp),
        )
    }
}

@Preview
@Composable
private fun ProjectScreenPreview() {
    ProjectScreen(
        projects = listOf(
            Project("Compose1", Tasks(emptyList())),
            Project("Compose2", Tasks(emptyList())),
        ),
        modifier = TODO(),
        onProjectChange = { _, _ -> },
    )
}

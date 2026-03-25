package woowacourse.kanban.board

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.kanban.board.domain.Project
import woowacourse.kanban.board.domain.Tasks
import woowacourse.kanban.board.ui.board.ProjectScreen

@Preview(showBackground = true)
@Composable
fun App() {
    val projects =
        listOf(
            Project(
                name = "Compose1",
                tasks = Tasks(emptyList()),
            ),
            Project(
                name = "Compose2",
                tasks = Tasks(emptyList()),
            ),
            Project(
                name = "Compose3너무너무길다란이름",
                tasks = Tasks(emptyList()),
            ),
        )

    ProjectScreen(
        projects = projects,
        onProjectChange = { project, tasks -> projects[projects.indexOf(project)].tasks = tasks },
        modifier = Modifier.fillMaxSize(),
    )
}

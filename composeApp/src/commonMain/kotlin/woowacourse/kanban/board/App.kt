package woowacourse.kanban.board

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.domain.Tasks
import woowacourse.kanban.board.ui.board.Board

@Preview(showBackground = true)
@Composable
fun App() {
    val authors = listOf("다이노", "페임스")
    var tasks by remember { mutableStateOf(Tasks(emptyList())) }


    ProjectScreen(
        projects = projects,
        onProjectChange = { project, tasks -> projects[projects.indexOf(project)].tasks = tasks },
        modifier = Modifier.fillMaxSize(),
    )
}

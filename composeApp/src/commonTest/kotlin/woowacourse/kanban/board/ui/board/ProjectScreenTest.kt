package woowacourse.kanban.board.ui.board

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.domain.Project
import woowacourse.kanban.board.domain.Task
import woowacourse.kanban.board.domain.Tasks

@OptIn(ExperimentalTestApi::class)
class ProjectScreenTest {
    val projects =
        listOf(
            Project(
                name = "Compose1",
                tasks = Tasks(emptyList()),
            ),
            Project(
                name = "Compose2",
                tasks = Tasks(
                    listOf(
                        Task(
                            id = 0,
                            title = "test_title",
                            author = "elle",
                        ),
                    ),
                ),
            ),
        )

    @Test
    fun `프로젝트 버튼을 클릭하면 해당 프로젝트의 태스크가 노출된다`() = runComposeUiTest {
        setContent {
            ProjectScreen(
                projects = projects,
                onProjectChange = { project, tasks -> projects[projects.indexOf(project)].tasks = tasks },
            )
        }

        onNodeWithText("Compose2", useUnmergedTree = true).performClick()
        onNodeWithText("test_title", useUnmergedTree = true).assertExists()
    }
}

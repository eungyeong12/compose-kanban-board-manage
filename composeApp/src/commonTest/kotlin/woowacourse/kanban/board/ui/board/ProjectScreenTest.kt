package woowacourse.kanban.board.ui.board

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.domain.Project
import woowacourse.kanban.board.domain.Task
import woowacourse.kanban.board.domain.TaskState
import woowacourse.kanban.board.domain.Tasks
import woowacourse.kanban.board.ui.board.state.ProjectsStateHolder

@OptIn(ExperimentalTestApi::class)
class ProjectScreenTest {
    val projects = listOf(
        Project(name = "Compose1"),
        Project(
            name = "Compose2",
            tasks = Tasks(
                listOf(
                    Task(
                        title = "test_title",
                        taskState = TaskState.TO_DO,
                        author = "다이노",
                    ),
                ),
            ),
        ),
        Project(name = "Compose3너무너무길다란이름"),
    )

    @Test
    fun `프로젝트 제목 리스트가 노출된다`() = runComposeUiTest {
        setContent {
            val stateHolder = remember { ProjectsStateHolder(initialProjects = projects) }

            ProjectScreen(
                stateHolder = stateHolder,
                onProjectChange = { projectId -> stateHolder.selectProject(projectId) },
                onTaskCreated = { projectId, task -> stateHolder.addTask(projectId, task) },
                onTaskStateChange = { projectId, taskIdx, taskState ->
                    stateHolder.changeTaskState(projectId, taskIdx, taskState)
                },
                modifier = Modifier.fillMaxSize(),
            )
        }

        onNode(hasText("Compose1") and hasClickAction()).assertExists()
        onNode(hasText("Compose2") and hasClickAction()).assertExists()
        onNode(hasText("Compose3너무너무길다란이름") and hasClickAction()).assertExists()
    }

    @Test
    fun `프로젝트 버튼을 클릭하면 해당 프로젝트의 태스크가 노출된다`() = runComposeUiTest {
        setContent {
            val stateHolder = remember { ProjectsStateHolder(initialProjects = projects) }

            ProjectScreen(
                stateHolder = stateHolder,
                onProjectChange = { projectId -> stateHolder.selectProject(projectId) },
                onTaskCreated = { projectId, task -> stateHolder.addTask(projectId, task) },
                onTaskStateChange = { projectId, taskIdx, taskState ->
                    stateHolder.changeTaskState(projectId, taskIdx, taskState)
                },
                modifier = Modifier.fillMaxSize(),
            )
        }

        onNodeWithText("Compose2", useUnmergedTree = true).performClick()
        onNodeWithText("test_title", useUnmergedTree = true).assertExists()
    }
}

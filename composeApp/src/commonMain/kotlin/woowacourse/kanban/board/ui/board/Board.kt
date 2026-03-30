package woowacourse.kanban.board.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.kanban.board.domain.Task
import woowacourse.kanban.board.domain.TaskState
import woowacourse.kanban.board.domain.Tasks
import woowacourse.kanban.board.ui.board.components.BoardHeader
import woowacourse.kanban.board.ui.board.components.CreateTaskModalDialog
import woowacourse.kanban.board.ui.board.components.KanbanBoardContent
import woowacourse.kanban.board.ui.theme.OutlineVariant
import woowacourse.kanban.board.ui.theme.Primary
import java.util.UUID

@Composable
fun Board(
    projectName: String,
    tasks: Tasks,
    onTaskCreated: (Task) -> Unit,
    authors: List<String>,
    onTaskStateChange: (UUID, TaskState) -> Unit,
    modifier: Modifier = Modifier,
) {
    var openDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let { message ->
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                message = message,
                withDismissAction = true,
            )
            snackbarMessage = null
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        Box(
            modifier = modifier.padding(innerPadding),
        ) {
            Column {
                BoardHeader(
                    projectName = projectName,
                    completedRate = tasks.completedRate,
                    doneCount = tasks.countByState(TaskState.DONE),
                    totalCount = tasks.totalCount,
                    onClick = { openDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                )

                HorizontalDivider(color = OutlineVariant)

                KanbanBoardContent(
                    tasks = tasks,
                    onTaskStateChange = { id, targetStatus ->
                        onTaskStateChange(id, targetStatus)
                        snackbarMessage = "태스크가 이동되었습니다."
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Primary),
                )
            }

            if (openDialog) {
                CreateTaskModalDialog(
                    authors = authors,
                    onDismissRequest = {
                        openDialog = false
                    },
                    onConfirmation = {
                        onTaskCreated(it)
                        snackbarMessage = "새로운 태스크가 추가되었습니다."
                        openDialog = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BoardPreview() {
    Board(
        projectName = "Compose Desktop 칸반보드",
        tasks = Tasks(emptyList()),
        onTaskCreated = {},
        authors = listOf("다이노", "페임스"),
        onTaskStateChange = { _, _ -> },
    )
}

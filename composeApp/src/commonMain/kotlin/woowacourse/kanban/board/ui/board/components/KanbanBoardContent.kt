package woowacourse.kanban.board.ui.board.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.domain.TaskState
import woowacourse.kanban.board.domain.Tasks
import woowacourse.kanban.board.ui.taskcard.TaskCards
import woowacourse.kanban.board.ui.theme.DoneBorder
import woowacourse.kanban.board.ui.theme.DoneContent
import woowacourse.kanban.board.ui.theme.DoneTitle
import woowacourse.kanban.board.ui.theme.InProgressBorder
import woowacourse.kanban.board.ui.theme.InProgressContent
import woowacourse.kanban.board.ui.theme.InProgressTitle
import woowacourse.kanban.board.ui.theme.ToDoBorder
import woowacourse.kanban.board.ui.theme.ToDoContent
import woowacourse.kanban.board.ui.theme.ToDoTitle

@Composable
fun KanbanBoardContent(tasks: Tasks, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        TaskState.entries.forEach { taskState ->
            StateTasks(
                tasks,
                taskState,
                taskState.titleColor(),
                taskState.contentColor(),
                taskState.borderColor(),
            )
        }
    }
}

@Composable
private fun StateTasks(
    tasks: Tasks,
    taskState: TaskState,
    titleColor: Color,
    contentColor: Color,
    borderColor: Color,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = contentColor,

        ),
        border = BorderStroke(0.5.dp, borderColor),
        modifier = modifier.size(width = 320.dp, height = 748.dp),
    ) {
        StateTasksTitle(titleColor, taskState, tasks)
        TaskCards(
            tasks.getTasksByState(taskState),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        )
    }
}

@Composable
private fun StateTasksTitle(titleColor: Color, taskState: TaskState, tasks: Tasks, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(titleColor)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = taskState.toText(),
            fontWeight = FontWeight.W600,
            fontSize = 16.sp,
        )
        Box(
            modifier = Modifier
                .size(width = 29.dp, height = 24.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White),
        ) {
            Text(
                text = tasks.countByState(taskState).toString(),
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

fun TaskState.toText(): String = when (this) {
    TaskState.TO_DO -> "To Do"
    TaskState.IN_PROGRESS -> "In Progress"
    TaskState.DONE -> "Done"
}

private fun TaskState.titleColor(): Color = when (this) {
    TaskState.TO_DO -> ToDoTitle
    TaskState.IN_PROGRESS -> InProgressTitle
    TaskState.DONE -> DoneTitle
}

private fun TaskState.contentColor(): Color = when (this) {
    TaskState.TO_DO -> ToDoContent
    TaskState.IN_PROGRESS -> InProgressContent
    TaskState.DONE -> DoneContent
}

private fun TaskState.borderColor(): Color = when (this) {
    TaskState.TO_DO -> ToDoBorder
    TaskState.IN_PROGRESS -> InProgressBorder
    TaskState.DONE -> DoneBorder
}

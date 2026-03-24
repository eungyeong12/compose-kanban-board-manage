package woowacourse.kanban.board.ui.taskcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.domain.Task

@Composable
fun TaskCards(tasks: List<Task>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(items = tasks, key = { it }) {
            TaskCard(task = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskCardsPreview() {
    TaskCards(emptyList())
}

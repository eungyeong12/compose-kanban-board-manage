package woowacourse.kanban.board.domain

enum class TaskState(val inAuthorRequired: Boolean, val isDeletable: Boolean) {
    TO_DO(false, true),
    IN_PROGRESS(true, true),
    REVIEW(true, false),
    DONE(true, false)
}

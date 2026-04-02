package woowacourse.kanban.board.domain

enum class Author {
    NONE,
    DINO,
    JAMES,
    ;

    companion object {
        fun getAuthors(isRequired: Boolean): List<Author> = if (isRequired) listOf(DINO, JAMES) else listOf(NONE, DINO, JAMES)
    }
}

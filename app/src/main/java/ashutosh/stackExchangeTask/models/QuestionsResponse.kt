package ashutosh.stackExchangeTask.models

data class QuestionsResponse(
    val has_more: Boolean,
    val questions: List<Question>,
    val quota_max: Int,
    val quota_remaining: Int
)
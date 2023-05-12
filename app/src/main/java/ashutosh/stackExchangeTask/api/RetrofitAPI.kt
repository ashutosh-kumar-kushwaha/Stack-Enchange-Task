package ashutosh.stackExchangeTask.api

import ashutosh.stackExchangeTask.models.QuestionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("2.3/questions?order=desc&site=stackoverflow")
    suspend fun getQuestions(@Query("sort") sortBy: String): Response<QuestionsResponse>

    @GET("2.3/questions/unanswered?order=desc&sort=activity&site=stackoverflow")
    suspend fun getUnansweredQuestions(): Response<QuestionsResponse>
}
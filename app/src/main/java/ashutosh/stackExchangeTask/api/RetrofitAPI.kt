package ashutosh.stackExchangeTask.api

import ashutosh.stackExchangeTask.models.QuestionsResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("2.3/questions?order=desc&sort=activity&site=stackoverflow")
    suspend fun getRecentActivityQuestions(): Response<QuestionsResponse>
}
package ashutosh.stackExchangeTask.api

import ashutosh.stackExchangeTask.models.QuestionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("2.3/questions?order=desc&site=stackoverflow")
suspend fun getQuestions(@Query("sort") sortBy: String, @Query("page") page: Int, @Query("pagesize") pageSize : Int, @Query("tagged") tags: String): Response<QuestionsResponse>

    @GET("2.3/questions/unanswered?order=desc&sort=activity&site=stackoverflow")
    suspend fun getUnansweredQuestions(@Query("page") page: Int, @Query("pagesize") pageSize : Int): Response<QuestionsResponse>

    @GET("/2.3/search?order=desc&sort=activity&site=stackoverflow")
    suspend fun search(@Query("order") order: String, @Query("sort") sortBy: String, @Query("page") page: Int, @Query("pagesize") pageSize: Int, @Query("tagged") tagged: String, @Query("intitle") query: String): Response<QuestionsResponse>

}
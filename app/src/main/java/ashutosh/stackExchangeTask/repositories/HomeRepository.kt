package ashutosh.stackExchangeTask.repositories

import ashutosh.stackExchangeTask.SingleLiveEvent
import ashutosh.stackExchangeTask.api.NetworkResult
import ashutosh.stackExchangeTask.api.RetrofitAPI
import ashutosh.stackExchangeTask.models.QuestionsResponse
import java.lang.Exception
import javax.inject.Inject

class HomeRepository @Inject constructor(private val retrofitAPI: RetrofitAPI){
    val recentActivityQuestionsResponse = SingleLiveEvent<NetworkResult<QuestionsResponse>>()

    suspend fun getRecentActivityQuestions(){
        recentActivityQuestionsResponse.value = NetworkResult.Loading()
        try {
            val response = retrofitAPI.getRecentActivityQuestions()
            when(response.code()){
                200 -> {
                    if(response.body()!=null){
                        recentActivityQuestionsResponse.value = NetworkResult.Success(200, response.body()!!)
                    }
                    else{
                        recentActivityQuestionsResponse.value = NetworkResult.Error(200, "Something went wrong\nResponse is null")
                    }
                }
                else -> recentActivityQuestionsResponse.value = NetworkResult.Error(response.code(), "Something went wrong\nError code: ${response.code()}")
            }
        }
        catch (e: Exception){
            e.printStackTrace()
            recentActivityQuestionsResponse.value = NetworkResult.Error(-1, e.message)
        }
    }
}
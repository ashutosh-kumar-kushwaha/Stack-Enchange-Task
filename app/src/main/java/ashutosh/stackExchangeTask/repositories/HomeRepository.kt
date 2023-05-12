package ashutosh.stackExchangeTask.repositories

import ashutosh.stackExchangeTask.SingleLiveEvent
import ashutosh.stackExchangeTask.api.NetworkResult
import ashutosh.stackExchangeTask.api.RetrofitAPI
import ashutosh.stackExchangeTask.models.QuestionsResponse
import java.lang.Exception
import javax.inject.Inject

class HomeRepository @Inject constructor(private val retrofitAPI: RetrofitAPI){
    val recentActivityQuestionsResponse = SingleLiveEvent<NetworkResult<QuestionsResponse>>()
    val unansweredQuestionsResponse = SingleLiveEvent<NetworkResult<QuestionsResponse>>()
    val topVotedQuestionsResponse = SingleLiveEvent<NetworkResult<QuestionsResponse>>()
    val hotQuestionsResponse = SingleLiveEvent<NetworkResult<QuestionsResponse>>()

    suspend fun getRecentActivityQuestions(){
        recentActivityQuestionsResponse.value = NetworkResult.Loading()
        try {
            val response = retrofitAPI.getQuestions("activity")
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

    suspend fun getUnansweredQuestions(){
        unansweredQuestionsResponse.value = NetworkResult.Loading()
        try {
            val response = retrofitAPI.getUnansweredQuestions()
            when(response.code()){
                200 -> {
                    if(response.body()!=null){
                        unansweredQuestionsResponse.value = NetworkResult.Success(200, response.body()!!)
                    }
                    else{
                        unansweredQuestionsResponse.value = NetworkResult.Error(200, "Something went wrong\nResponse is null")
                    }
                }
                else -> unansweredQuestionsResponse.value = NetworkResult.Error(response.code(), "Something went wrong\nError code: ${response.code()}")
            }
        }
        catch (e: Exception){
            e.printStackTrace()
            unansweredQuestionsResponse.value = NetworkResult.Error(-1, e.message)
        }
    }

    suspend fun getTopVotedQuestions(){
        topVotedQuestionsResponse.value = NetworkResult.Loading()
        try {
            val response = retrofitAPI.getQuestions("votes")
            when(response.code()){
                200 -> {
                    if(response.body()!=null){
                        topVotedQuestionsResponse.value = NetworkResult.Success(200, response.body()!!)
                    }
                    else{
                        topVotedQuestionsResponse.value = NetworkResult.Error(200, "Something went wrong\nResponse is null")
                    }
                }
                else -> topVotedQuestionsResponse.value = NetworkResult.Error(response.code(), "Something went wrong\nError code: ${response.code()}")
            }
        }
        catch (e: Exception){
            e.printStackTrace()
            topVotedQuestionsResponse.value = NetworkResult.Error(-1, e.message)
        }
    }

    suspend fun getHotQuestions(){
        hotQuestionsResponse.value = NetworkResult.Loading()
        try {
            val response = retrofitAPI.getQuestions("hot")
            when(response.code()){
                200 -> {
                    if(response.body()!=null){
                        hotQuestionsResponse.value = NetworkResult.Success(200, response.body()!!)
                    }
                    else{
                        hotQuestionsResponse.value = NetworkResult.Error(200, "Something went wrong\nResponse is null")
                    }
                }
                else -> hotQuestionsResponse.value = NetworkResult.Error(response.code(), "Something went wrong\nError code: ${response.code()}")
            }
        }
        catch (e: Exception){
            e.printStackTrace()
            hotQuestionsResponse.value = NetworkResult.Error(-1, e.message)
        }
    }

}
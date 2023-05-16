package ashutosh.stackExchangeTask.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import ashutosh.stackExchangeTask.api.NetworkResult
import ashutosh.stackExchangeTask.api.RetrofitAPI
import ashutosh.stackExchangeTask.models.QuestionsResponse
import javax.inject.Inject

class HomeRepository @Inject constructor(private val retrofitAPI: RetrofitAPI){
//    fun getRecentActivityQuestions(tagged: String) = Pager(config = PagingConfig(10, 50), pagingSourceFactory = {QuestionsPagingSource(retrofitAPI, "activity", tagged)}).liveData
    val recentActivityQuestionsResponse = MutableLiveData<NetworkResult<QuestionsResponse>>()
    suspend fun getRecentActivityQuestions(tags: String){
        recentActivityQuestionsResponse.value = NetworkResult.Loading()
        try {
            val response = retrofitAPI.getQuestions("activity", 1, 100, tags)
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
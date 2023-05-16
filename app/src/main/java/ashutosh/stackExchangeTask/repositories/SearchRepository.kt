package ashutosh.stackExchangeTask.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import ashutosh.stackExchangeTask.api.NetworkResult
import ashutosh.stackExchangeTask.api.RetrofitAPI
import ashutosh.stackExchangeTask.models.QuestionsResponse
//import ashutosh.stackExchangeTask.paging.SearchPagingSource
import javax.inject.Inject

class SearchRepository @Inject constructor(private val retrofitAPI: RetrofitAPI) {
    //    fun getSearch(order: String, sortBy: String, tags: String, query: String) = Pager(config = PagingConfig(10, 50), pagingSourceFactory = {SearchPagingSource(retrofitAPI, order, sortBy, tags, query)})
    val searchResponse = MutableLiveData<NetworkResult<QuestionsResponse>>()
    suspend fun getSearch(query: String, tags: String) {
        searchResponse.value = NetworkResult.Loading()
        try {
            val response = retrofitAPI.search("desc", "activity", 1, 100, tags, query)
            when (response.code()) {
                200 -> {
                    if (response.body() != null) {
                        searchResponse.value =
                            NetworkResult.Success(200, response.body()!!)
                    } else {
                        searchResponse.value =
                            NetworkResult.Error(200, "Something went wrong\nResponse is null")
                    }
                }

                else -> searchResponse.value = NetworkResult.Error(
                    response.code(),
                    "Something went wrong\nError code: ${response.code()}"
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            searchResponse.value = NetworkResult.Error(-1, e.message)
        }
    }
}
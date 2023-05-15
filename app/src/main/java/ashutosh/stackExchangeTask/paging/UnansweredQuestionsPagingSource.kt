package ashutosh.stackExchangeTask.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ashutosh.stackExchangeTask.api.RetrofitAPI
import ashutosh.stackExchangeTask.models.Question
import java.lang.Exception

class UnansweredQuestionsPagingSource(private val retrofitAPI: RetrofitAPI) : PagingSource<Int, Question>() {
    override fun getRefreshKey(state: PagingState<Int, Question>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Question> {
        return try{
            val currentPage = params.key ?: 1
            val pageSize = params.loadSize
            val response = retrofitAPI.getUnansweredQuestions(currentPage, pageSize)
            val prevKey = if(currentPage == 1){
                null
            }
            else{
                currentPage - 1
            }
            val nextKey = if(response.has_more) {
                currentPage+1
            }
            else {
                null
            }
            LoadResult.Page(response.items, prevKey, nextKey)
        }
        catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}
package ashutosh.stackExchangeTask.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import ashutosh.stackExchangeTask.api.RetrofitAPI
import ashutosh.stackExchangeTask.paging.UnansweredQuestionsPagingSource
import javax.inject.Inject

class UnansweredRepository @Inject constructor(private val retrofitAPI: RetrofitAPI) {
    fun getUnansweredQuestions() = Pager(config = PagingConfig(20, 100), pagingSourceFactory = { UnansweredQuestionsPagingSource(retrofitAPI) }).liveData
}
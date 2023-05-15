package ashutosh.stackExchangeTask.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import ashutosh.stackExchangeTask.api.RetrofitAPI
import ashutosh.stackExchangeTask.paging.QuestionsPagingSource
import javax.inject.Inject

class RecentActivityRepository @Inject constructor(private val retrofitAPI: RetrofitAPI) {
    fun getRecentActivityQuestions() = Pager(config = PagingConfig(10, 50), pagingSourceFactory = { QuestionsPagingSource(retrofitAPI, "activity") }).liveData
}
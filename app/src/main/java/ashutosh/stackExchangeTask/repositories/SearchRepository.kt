package ashutosh.stackExchangeTask.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import ashutosh.stackExchangeTask.api.RetrofitAPI
import ashutosh.stackExchangeTask.paging.SearchPagingSource
import javax.inject.Inject

class SearchRepository @Inject constructor(private val retrofitAPI: RetrofitAPI) {
    fun getSearch(order: String, sortBy: String, tags: String, query: String) = Pager(config = PagingConfig(10, 50), pagingSourceFactory = {SearchPagingSource(retrofitAPI, order, sortBy, tags, query)})
}
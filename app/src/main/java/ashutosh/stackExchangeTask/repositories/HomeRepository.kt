package ashutosh.stackExchangeTask.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import ashutosh.stackExchangeTask.api.RetrofitAPI
import ashutosh.stackExchangeTask.paging.QuestionsPagingSource
import ashutosh.stackExchangeTask.paging.UnansweredQuestionsPagingSource
import javax.inject.Inject

class HomeRepository @Inject constructor(private val retrofitAPI: RetrofitAPI){
    fun getRecentActivityQuestions() = Pager(config = PagingConfig(10, 50), pagingSourceFactory = {QuestionsPagingSource(retrofitAPI, "activity")}).liveData
    fun getHotQuestions() = Pager(config = PagingConfig(10, 50), pagingSourceFactory = {QuestionsPagingSource(retrofitAPI, "hot")}).liveData
    fun getTopVotedQuestions() = Pager(config = PagingConfig(10, 50), pagingSourceFactory = {QuestionsPagingSource(retrofitAPI, "votes")}).liveData
    fun getUnansweredQuestions() = Pager(config = PagingConfig(10, 50), pagingSourceFactory = {UnansweredQuestionsPagingSource(retrofitAPI)}).liveData


}
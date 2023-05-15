package ashutosh.stackExchangeTask.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.PagingData
import androidx.paging.liveData
import ashutosh.stackExchangeTask.models.Question
import ashutosh.stackExchangeTask.repositories.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {
     var tags = ""

     val searchTextLiveData = MutableLiveData("")

     val searchResponse = searchTextLiveData.switchMap {
          searchRepository.getSearch("desc", "activity", tags, it).liveData
     }
}
package ashutosh.stackExchangeTask.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import ashutosh.stackExchangeTask.models.Question
import ashutosh.stackExchangeTask.repositories.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {
     var tags = ""

     val searchTextLiveData = MutableLiveData("")
     val searchResponse get() = searchRepository.searchResponse

     fun getSearch(){
          viewModelScope.launch {
               searchRepository.getSearch(searchTextLiveData.value!!, tags)
          }
     }

//     val searchResponse = searchTextLiveData.switchMap {
//          searchRepository.getSearch("desc", "activity", tags, it).liveData.cachedIn(viewModelScope)
//     }
}
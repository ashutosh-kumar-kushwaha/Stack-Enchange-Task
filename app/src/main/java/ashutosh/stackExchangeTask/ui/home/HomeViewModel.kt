package ashutosh.stackExchangeTask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import ashutosh.stackExchangeTask.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    var tags = ""

    val recentActivityQuestionsResponse get() = homeRepository.recentActivityQuestionsResponse
//    val recentActivityQuestionsResponse get() = homeRepository.getRecentActivityQuestions(tags).cachedIn(viewModelScope)

    fun getRecentQuestions(){
        viewModelScope.launch {
            homeRepository.getRecentActivityQuestions(tags)
        }
    }

}
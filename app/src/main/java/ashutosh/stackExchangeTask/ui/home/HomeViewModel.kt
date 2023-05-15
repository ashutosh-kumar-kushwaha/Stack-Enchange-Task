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
    val recentActivityQuestionsResponse get() = homeRepository.getRecentActivityQuestions().cachedIn(viewModelScope)
    val unansweredQuestionsResponse get() = homeRepository.getUnansweredQuestions().cachedIn(viewModelScope)
    val topVotedQuestionsResponse get() = homeRepository.getTopVotedQuestions().cachedIn(viewModelScope)
    val hotQuestionsResponse get() = homeRepository.getHotQuestions().cachedIn(viewModelScope)

    fun getRecentQuestions(){
        viewModelScope.launch {
            homeRepository.getRecentActivityQuestions()
        }
    }

    fun getUnansweredQuestions(){
        viewModelScope.launch {
            homeRepository.getUnansweredQuestions()
        }
    }

    fun getTopVotedQuestions(){
        viewModelScope.launch {
            homeRepository.getTopVotedQuestions()
        }
    }

    fun getHotQuestions(){
        viewModelScope.launch {
            homeRepository.getHotQuestions()
        }
    }
}
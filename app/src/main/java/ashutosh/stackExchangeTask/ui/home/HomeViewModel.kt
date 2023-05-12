package ashutosh.stackExchangeTask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ashutosh.stackExchangeTask.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    val recentActivityQuestionsResponse get() = homeRepository.recentActivityQuestionsResponse
    val unansweredQuestionsResponse get() = homeRepository.unansweredQuestionsResponse
    val topVotedQuestionsResponse get() = homeRepository.topVotedQuestionsResponse
    val hotQuestionsResponse get() = homeRepository.hotQuestionsResponse

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
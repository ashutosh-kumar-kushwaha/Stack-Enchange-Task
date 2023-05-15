package ashutosh.stackExchangeTask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import ashutosh.stackExchangeTask.repositories.UnansweredRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UnansweredViewModel @Inject constructor(private val unansweredRepository: UnansweredRepository) : ViewModel() {
    val unansweredQuestionsResponse get() = unansweredRepository.getUnansweredQuestions().cachedIn(viewModelScope)
}
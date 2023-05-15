package ashutosh.stackExchangeTask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import ashutosh.stackExchangeTask.repositories.RecentActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecentActivityViewModel @Inject constructor(private val recentActivityRepository: RecentActivityRepository): ViewModel() {
    val recentActivityQuestionsResponse get() = recentActivityRepository.getRecentActivityQuestions().cachedIn(viewModelScope)
}
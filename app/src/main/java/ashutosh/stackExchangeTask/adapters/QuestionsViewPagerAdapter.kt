package ashutosh.stackExchangeTask.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ashutosh.stackExchangeTask.ui.home.HotQuestionsFragment
import ashutosh.stackExchangeTask.ui.home.LastActivityQuestionsFragment
import ashutosh.stackExchangeTask.ui.home.TopVotedQuestionsFragment
import ashutosh.stackExchangeTask.ui.home.UnansweredQuestionsFragment

class QuestionsViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LastActivityQuestionsFragment()
            1 -> HotQuestionsFragment()
            2 -> UnansweredQuestionsFragment()
            else -> TopVotedQuestionsFragment()
        }
    }

}
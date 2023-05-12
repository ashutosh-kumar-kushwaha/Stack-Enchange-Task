package ashutosh.stackExchangeTask.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ashutosh.stackExchangeTask.adapters.QuestionsViewPagerAdapter
import ashutosh.stackExchangeTask.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding : FragmentHomeBinding get() = _binding!!

    private val tabs = arrayOf("Recent Activity", "Hot", "Unanswered", "Top Voted")

    private val homeViewModel by viewModels<HomeViewModel>()

    private lateinit var questionsViewPagerAdapter: QuestionsViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        questionsViewPagerAdapter = QuestionsViewPagerAdapter(this)

        binding.viewPager.adapter = questionsViewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            tab.text = tabs[position]
        }.attach()

        homeViewModel.getRecentQuestions()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
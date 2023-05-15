package ashutosh.stackExchangeTask.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ashutosh.stackExchangeTask.R
import ashutosh.stackExchangeTask.adapters.QuestionRecyclerAdapter
import ashutosh.stackExchangeTask.adapters.QuestionsViewPagerAdapter
import ashutosh.stackExchangeTask.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding : FragmentHomeBinding get() = _binding!!

//    private val tabs = arrayOf("Recent Activity", "Hot", "Unanswered", "Top Voted")
    private val tabs = arrayOf("Recent Activity")

    private val homeViewModel by viewModels<HomeViewModel>()

    val questionRecyclerAdapter = QuestionRecyclerAdapter()

    private lateinit var questionsViewPagerAdapter: QuestionsViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        questionsViewPagerAdapter = QuestionsViewPagerAdapter(this)

//        binding.viewPager.adapter = questionsViewPagerAdapter

//        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
//            tab.text = tabs[position]
//        }.attach()

//        binding.viewPager.offscreenPageLimit = 2

        binding.questionsRecyclerVw.adapter = questionRecyclerAdapter
        binding.questionsRecyclerVw.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        binding.searchTxtVw.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.recentActivityQuestionsResponse.observe(viewLifecycleOwner){
            questionRecyclerAdapter.submitData(lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
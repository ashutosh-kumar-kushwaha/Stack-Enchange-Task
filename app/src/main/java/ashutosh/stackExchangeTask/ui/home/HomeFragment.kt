package ashutosh.stackExchangeTask.ui.home

import android.os.Bundle
import android.os.RecoverySystem
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ashutosh.stackExchangeTask.R
import ashutosh.stackExchangeTask.adapters.QuestionRecyclerAdapter
import ashutosh.stackExchangeTask.api.NetworkResult
import ashutosh.stackExchangeTask.bottomSheet.FilterBottomSheet
import ashutosh.stackExchangeTask.databinding.FragmentHomeBinding
import ashutosh.stackExchangeTask.interfaces.QuestionClickListener
import ashutosh.stackExchangeTask.interfaces.TagListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    //    private val tabs = arrayOf("Recent Activity", "Hot", "Unanswered", "Top Voted")
    private val tabs = arrayOf("Recent Activity")

    private val homeViewModel by viewModels<HomeViewModel>()

    private lateinit var questionRecyclerAdapter: QuestionRecyclerAdapter


    private lateinit var filterBottomSheet: FilterBottomSheet

    //    private lateinit var questionsViewPagerAdapter: QuestionsViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

//        questionsViewPagerAdapter = QuestionsViewPagerAdapter(this)

//        binding.viewPager.adapter = questionsViewPagerAdapter

//        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
//            tab.text = tabs[position]
//        }.attach()

//        binding.viewPager.offscreenPageLimit = 2

        val questionClickListener = object : QuestionClickListener {
            override fun onClick(link: String) {
                val bundle = Bundle()
                bundle.putString("link", link)
                findNavController().navigate(R.id.action_homeFragment_to_webViewFragment, bundle)
            }
        }

        questionRecyclerAdapter = QuestionRecyclerAdapter(questionClickListener)
        binding.questionsRecyclerVw.adapter = questionRecyclerAdapter
        binding.questionsRecyclerVw.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        binding.searchTxtVw.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        val tagListener = object : TagListener {
            override fun tags(tags: String) {
                homeViewModel.tags = tags
                homeViewModel.getRecentQuestions()
            }
        }
        filterBottomSheet = FilterBottomSheet(tagListener)

        homeViewModel.getRecentQuestions()

        binding.filterBtn.setOnClickListener {
            filterBottomSheet.show(parentFragmentManager, "Filter Bottom Sheet")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.recentActivityQuestionsResponse.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    questionRecyclerAdapter.submitList(it.data?.items)
                    binding.progressBar.visibility = View.GONE
                    binding.questionsRecyclerVw.visibility = View.VISIBLE
                }

                is NetworkResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.questionsRecyclerVw.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.questionsRecyclerVw.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
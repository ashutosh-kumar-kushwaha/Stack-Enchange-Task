package ashutosh.stackExchangeTask.ui.home

import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ashutosh.stackExchangeTask.adapters.QuestionRecyclerAdapter
import ashutosh.stackExchangeTask.api.NetworkResult
import ashutosh.stackExchangeTask.databinding.FragmentRecentActivityQuestionsBinding
import ashutosh.stackExchangeTask.models.QuestionsResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentActivityQuestionsFragment : Fragment() {
    private var _binding : FragmentRecentActivityQuestionsBinding? = null
    private val binding : FragmentRecentActivityQuestionsBinding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>({requireParentFragment()})

    private val questionsRecyclerAdapter = QuestionRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecentActivityQuestionsBinding.inflate(inflater, container, false)

        binding.lastActivityQuestionsRecyclerView.adapter = questionsRecyclerAdapter
        binding.lastActivityQuestionsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.recentActivityQuestionsResponse.observe(viewLifecycleOwner){
            when(it){
                is NetworkResult.Success -> {
                    questionsRecyclerAdapter.submitList(it.data?.items)
                    Log.d("Ashu", it.data?.items.toString())
                }
                is NetworkResult.Error -> {}
                is NetworkResult.Loading -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package ashutosh.stackExchangeTask.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ashutosh.stackExchangeTask.adapters.QuestionRecyclerAdapter
import ashutosh.stackExchangeTask.api.NetworkResult
import ashutosh.stackExchangeTask.databinding.FragmentTopVotedQuestionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopVotedQuestionsFragment : Fragment() {

    private var _binding : FragmentTopVotedQuestionsBinding? = null
    private val binding : FragmentTopVotedQuestionsBinding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>()

    private val questionsRecyclerAdapter = QuestionRecyclerAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopVotedQuestionsBinding.inflate(inflater, container, false)

        binding.topVotedQuestionsRecyclerView.adapter = questionsRecyclerAdapter
        binding.topVotedQuestionsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        homeViewModel.getTopVotedQuestions()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.topVotedQuestionsResponse.observe(viewLifecycleOwner){
            questionsRecyclerAdapter.submitData(lifecycle, it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
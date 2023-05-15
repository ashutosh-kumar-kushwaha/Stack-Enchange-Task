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
import ashutosh.stackExchangeTask.databinding.FragmentUnansweredQuestionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnansweredQuestionsFragment : Fragment() {

    private var _binding : FragmentUnansweredQuestionsBinding? = null
    private val binding : FragmentUnansweredQuestionsBinding get() = _binding!!

    private val homeViewModel by viewModels<UnansweredViewModel>()

    private val questionsRecyclerAdapter = QuestionRecyclerAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnansweredQuestionsBinding.inflate(inflater, container, false)

//        homeViewModel.getUnansweredQuestions()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.unansweredQuestionsRecyclerView.adapter = questionsRecyclerAdapter
        binding.unansweredQuestionsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        homeViewModel.unansweredQuestionsResponse.observe(viewLifecycleOwner){
            questionsRecyclerAdapter.submitData(lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
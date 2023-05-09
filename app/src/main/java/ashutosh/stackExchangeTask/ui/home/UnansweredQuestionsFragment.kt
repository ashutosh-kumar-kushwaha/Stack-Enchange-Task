package ashutosh.stackExchangeTask.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ashutosh.stackExchangeTask.databinding.FragmentUnansweredQuestionsBinding

class UnansweredQuestionsFragment : Fragment() {

    private var _binding : FragmentUnansweredQuestionsBinding? = null
    private val binding : FragmentUnansweredQuestionsBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnansweredQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
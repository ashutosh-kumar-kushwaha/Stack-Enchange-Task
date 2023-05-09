package ashutosh.stackExchangeTask.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ashutosh.stackExchangeTask.databinding.FragmentLastActivityQuestionsBinding

class LastActivityQuestionsFragment : Fragment() {
    private var _binding : FragmentLastActivityQuestionsBinding? = null
    private val binding : FragmentLastActivityQuestionsBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLastActivityQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package ashutosh.stackExchangeTask.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ashutosh.stackExchangeTask.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding : FragmentHomeBinding get() = _binding!!

    private val tabs = arrayOf("Last Activity", "Hot", "Unanswered", "Top Voted")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            tab.text = tabs[position]
        }.attach()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
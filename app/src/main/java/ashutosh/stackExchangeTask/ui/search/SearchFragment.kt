package ashutosh.stackExchangeTask.ui.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ashutosh.stackExchangeTask.R
import ashutosh.stackExchangeTask.adapters.QuestionRecyclerAdapter
import ashutosh.stackExchangeTask.api.NetworkResult
import ashutosh.stackExchangeTask.bottomSheet.FilterBottomSheet
import ashutosh.stackExchangeTask.databinding.FragmentSearchBinding
import ashutosh.stackExchangeTask.interfaces.QuestionClickListener
import ashutosh.stackExchangeTask.interfaces.TagListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.internal.ViewUtils.showKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding : FragmentSearchBinding? = null
    private val binding : FragmentSearchBinding get() = _binding!!
    private val searchViewModel by viewModels<SearchViewModel>()
    private lateinit var filterBottomSheet : FilterBottomSheet
    private lateinit var questionRecyclerAdapter : QuestionRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.viewModel = searchViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val questionClickListener = object : QuestionClickListener {
            override fun onClick(link: String) {
                val bundle = Bundle()
                bundle.putString("link", link)
                findNavController().navigate(R.id.action_searchFragment_to_webViewFragment, bundle)
            }
        }

        questionRecyclerAdapter = QuestionRecyclerAdapter(questionClickListener)
        binding.questionsRecyclerVw.adapter = questionRecyclerAdapter
        binding.questionsRecyclerVw.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        val tagListener = object: TagListener{
            override fun tags(tags: String) {
                searchViewModel.tags = tags
                searchViewModel.getSearch()
            }

        }
        filterBottomSheet = FilterBottomSheet(tagListener)

//        val searchText = binding.searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
//        binding.searchView.isFocusable = true
//        binding.searchView.requestFocus()
//        binding.searchView.requestFocusFromTouch()
//        searchText.requestFocus()
//        val font = ResourcesCompat.getFont(requireContext(), R.font.poppins_medium)
//        searchText.typeface = font
//        searchText.setTextColor(ContextCompat.getColor(requireContext(), R.color.color2))
//        searchText.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.color2))
//        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        binding.searchView.isFocusable = true
        binding.searchView.requestFocus()
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

        binding.filterBtn.setOnClickListener {
            filterBottomSheet.show(parentFragmentManager, "Filter Bottom Sheet")
        }

        binding.searchView.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                searchViewModel.getSearch()
            }

        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.searchResponse.observe(viewLifecycleOwner){
            when(it){
                is NetworkResult.Success -> {
                    questionRecyclerAdapter.submitList(it.data?.items)
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package ashutosh.stackExchangeTask.ui.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import ashutosh.stackExchangeTask.databinding.FragmentSearchBinding
import com.google.android.material.internal.ViewUtils.showKeyboard


class SearchFragment : Fragment() {

    private var _binding : FragmentSearchBinding? = null
    private val binding : FragmentSearchBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

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

//        binding.searchView.postDelayed({
//            binding.searchView.requestFocus()
//            showKeyboard(requireContext(), binding.searchView)
//        }, 300)

//        binding.searchView.setOnFocusChangeListener { v, hasFocus ->
//            if (hasFocus) {
//                Log.d("TAG", "EditText focused")
//            } else {
//                Log.d("TAG", "EditText lost focus")
//            }
//        }



        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
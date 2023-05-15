package ashutosh.stackExchangeTask.bottomSheet

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import ashutosh.stackExchangeTask.R
import ashutosh.stackExchangeTask.databinding.BottomSheetFilterBinding
import ashutosh.stackExchangeTask.interfaces.TagListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip

class FilterBottomSheet(private val tagListener: TagListener, private val initialTags: String) : BottomSheetDialogFragment() {

    private var _binding : BottomSheetFilterBinding? = null
    private val binding : BottomSheetFilterBinding get() = _binding!!

    private var tags = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetFilterBinding.inflate(inflater, container, false)

        tags.split(";").forEach {
            if(it.isNotEmpty()) createChip(it)
        }

        binding.tagEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                var text = s.toString()
                if(text.isNotEmpty() && (text.last().toString()=="," || text.last().toString()=="\n" || text.last().toString()==" ")){
                    text = text.substring(0, text.length-1)
                    binding.tagEditText.setText("")
                    createChip(text)
                    tags += "$text;"
                }
            }

        })

        binding.doneBtn.setOnClickListener {
            tagListener.tags(tags)
            Log.d("Bottom Sheet", tags)
            dismiss()
        }



        return binding.root
    }

    private fun createChip(text: String) {
        val chip = Chip(requireContext())
        chip.text = text
        chip.isCloseIconVisible = true
        chip.isCheckable = false
        chip.isClickable = true
        chip.setPadding(16, 16, 16, 16)
        chip.setTextColor(Color.BLACK)
        chip.setChipBackgroundColorResource(R.color.white)
        chip.closeIconTint = ColorStateList.valueOf(Color.BLACK)
        chip.setOnCloseIconClickListener {
            binding.chipGroup.removeView(chip)
            tags = tags.replace(chip.text.toString(), "")
        }
        binding.chipGroup.addView(chip)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
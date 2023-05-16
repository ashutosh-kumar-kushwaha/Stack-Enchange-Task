package ashutosh.stackExchangeTask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ashutosh.stackExchangeTask.databinding.LayoutAdBinding
import ashutosh.stackExchangeTask.databinding.LayoutQuestionBinding
import ashutosh.stackExchangeTask.interfaces.QuestionClickListener
import ashutosh.stackExchangeTask.models.Question
import ashutosh.stackExchangeTask.time.TimeFormat
import coil.load
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class QuestionRecyclerAdapter(private val questionClickListener: QuestionClickListener) : ListAdapter<Question, RecyclerView.ViewHolder>(DiffUtil()) {

    private val VIEW_TYPE_QUESTION = 0
    private val VIEW_TYPE_AD = 1

    override fun getItemViewType(position: Int): Int {
        return if (position%6 == 5) VIEW_TYPE_AD else VIEW_TYPE_QUESTION
    }

    inner class QuestionsViewHolder(private val binding: LayoutQuestionBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }
        fun bind(question: Question){
            binding.nameTxtVw.text = question.owner.display_name
            binding.profilePicImgVw.load(question.owner.profile_image)
            binding.timeTxtVw.text = TimeFormat().getTimeDifference(question.creation_date.toTime())
            val htmlSpannedString = HtmlCompat.fromHtml(question.title, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.questionTxtVw.text = htmlSpannedString
            binding.votesTxtVw.text = question.score.toString()
            binding.answersTxtVw.text = question.answer_count.toString()
            binding.viewsTxtVw.text = question.view_count.toString()
            binding.tagsRecyclerVw.adapter = TagsRecyclerAdapter(question.tags)
            val layoutManager = FlexboxLayoutManager(binding.root.context)
            layoutManager.flexDirection = FlexDirection.ROW
            layoutManager.justifyContent = JustifyContent.FLEX_START
            binding.tagsRecyclerVw.layoutManager = layoutManager
        }

        private fun Int.toTime(): String{
            return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(this.toLong()*1000))
        }

        override fun onClick(v: View?) {
            if(absoluteAdapterPosition != RecyclerView.NO_POSITION) questionClickListener.onClick(getItem(absoluteAdapterPosition).link)
        }
    }

    inner class AdViewHolder(private val binding: LayoutAdBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(){
//            RequestConfiguration.Builder().setTestDeviceIds(listOf("D43850ED6B97DCC41FA66C89554F4CC1"))
////            MobileAds.setRequestConfiguration(requestConfiguration)
//            val ad = AdRequest.Builder().build()
//            RequestConfiguration.Builder().setTestDeviceIds(listOf("D43850ED6B97DCC41FA66C89554F4CC1"))
////            MobileAds.setRequestConfiguration(requestConfiguration)
//            val ad = AdRequest.Builder().build()
//            binding.adView.loadAd(ad)
        }
    }
    class DiffUtil: ItemCallback<Question>(){
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.question_id == newItem.question_id
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_QUESTION)
            QuestionsViewHolder(LayoutQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        else
            AdViewHolder(LayoutAdBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position)==VIEW_TYPE_QUESTION)
            (holder as QuestionsViewHolder).bind(getItem(position))
        else
            (holder as AdViewHolder).bind()
    }
}
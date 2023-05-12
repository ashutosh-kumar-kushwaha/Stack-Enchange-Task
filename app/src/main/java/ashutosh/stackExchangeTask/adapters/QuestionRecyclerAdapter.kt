package ashutosh.stackExchangeTask.adapters

import android.icu.util.LocaleData
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ashutosh.stackExchangeTask.databinding.LayoutQuestionBinding
import ashutosh.stackExchangeTask.models.Question
import ashutosh.stackExchangeTask.time.TimeFormat
import coil.load
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class QuestionRecyclerAdapter : ListAdapter<Question, QuestionRecyclerAdapter.QuestionsViewHolder>(DiffUtil()) {

    inner class QuestionsViewHolder(private val binding: LayoutQuestionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Question){
            binding.nameTxtVw.text = question.owner.display_name
            binding.profilePicImgVw.load(question.owner.profile_image)
            Log.d("Ashu", question.creation_date.toTime())
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
    }

    class DiffUtil: ItemCallback<Question>(){
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.question_id == newItem.question_id
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        return QuestionsViewHolder(LayoutQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
package ashutosh.stackExchangeTask.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ashutosh.stackExchangeTask.databinding.LayoutQuestionBinding
import ashutosh.stackExchangeTask.models.Question
import coil.load
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class QuestionRecyclerAdapter : ListAdapter<Question, QuestionRecyclerAdapter.QuestionsViewHolder>(DiffUtil()) {

    inner class QuestionsViewHolder(private val binding: LayoutQuestionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Question){
            binding.nameTxtVw.text = question.owner.display_name
            binding.profilePicImgVw.load(question.owner.profile_image)
            Log.d("Ashu", question.creation_date.toTime())
//            binding.timeTxtVw.text = getTimeDifference(question.creation_date.toTime())
            binding.questionTxtVw.text = question.title
            binding.votesTxtVw.text = question.score.toString()
            binding.answersTxtVw.text = question.answer_count.toString()
            binding.viewsTxtVw.text = question.view_count.toString()
            binding.tagsRecyclerVw.adapter = TagsRecyclerAdapter(question.tags)
        }

        private fun Int.toTime(): String{
            return DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault()).format(this)
        }

        private fun getTimeDifference(dateString: String): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = dateFormat.parse(dateString) ?: return "Incorrect format of date"
            val now = Date()
            val diffInMillis = now.time - date.time

            val seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMillis)
            if (seconds < 60) {
                return "just now"
            }

            val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis)
            if (minutes < 60) {
                return "$minutes minutes ago"
            }

            val hours = TimeUnit.MILLISECONDS.toHours(diffInMillis)
            if (hours < 24) {
                return "$hours hours ago"
            }

            val days = TimeUnit.MILLISECONDS.toDays(diffInMillis)
            if (days < 7) {
                return "$days days ago"
            }

            val weeks = days / 7
            if (weeks < 4) {
                return "$weeks weeks ago"
            }

            val months = days / 30
            if (months < 12) {
                return "$months months ago"
            }

            val years = days / 365
            return "$years years ago"
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
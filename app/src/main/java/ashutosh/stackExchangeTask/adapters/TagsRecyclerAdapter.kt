package ashutosh.stackExchangeTask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ashutosh.stackExchangeTask.databinding.LayoutTagBinding

class TagsRecyclerAdapter(private val tags: List<String>) : RecyclerView.Adapter<TagsRecyclerAdapter.TagsViewHolder>() {
    inner class TagsViewHolder(private val binding: LayoutTagBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: String){
            binding.tagTxtVw.text = tag
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        return TagsViewHolder(LayoutTagBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return tags.size
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        holder.bind(tags[position])
    }
}
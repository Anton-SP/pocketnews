package com.example.pocketnews.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketnews.data.NewsEntity
import com.example.pocketnews.databinding.ItemNewsBinding

class NewsAdapter():PagingDataAdapter<NewsEntity,NewsAdapter.NewsViewHolder>(NEWS_DIFF_CALLBACK) {

    private val news = ArrayList<NewsEntity>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val holder = NewsViewHolder(binding = binding)
        return holder
    }

    override fun getItemCount(): Int {
       return news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news = news[position])
    }

    fun submitList(data: List<NewsEntity>) {
        news.addAll(data)
        notifyDataSetChanged()
    }


    inner class NewsViewHolder(private val binding: ItemNewsBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(news:NewsEntity) {
            with(binding) {
                tvTitle.text = news.title
                tvPublished.text = news.author
            }
        }
    }

    companion object {
        private val NEWS_DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsEntity>() {
            override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean =
                oldItem == newItem
        }
    }
}
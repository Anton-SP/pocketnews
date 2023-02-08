package com.example.pocketnews.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketnews.data.NewsEntity
import com.example.pocketnews.databinding.ItemNewsBinding

class NewsAdapter():RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

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
                tvPublished.text = news.published
            }
        }
    }
}
package com.example.pocketnews.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pocketnews.R
import com.example.pocketnews.app
import com.example.pocketnews.databinding.FragmentStartScreenBinding
import com.example.pocketnews.ui.list.NewsAdapter
import com.example.pocketnews.ui.list.NewsListState
import com.example.pocketnews.ui.list.NewsViewModel
import kotlinx.coroutines.launch

class StartScreenFragment : Fragment(R.layout.fragment_start_screen) {

    private  val binding: FragmentStartScreenBinding by viewBinding()

    private val newsViewModel:NewsViewModel by viewModels{
        NewsViewModel.NewsViewModelFactory(requireContext().app.repo)
    }

    private val adapter:NewsAdapter by lazy {
        NewsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
       // getList()
        collectListFlow()

        binding.fabUpload.setOnClickListener {
            getList()
        }
    }

    private fun collectListFlow() {
        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                newsViewModel.getStateFlow().collect() {state ->
                    checkListState(state)
                }
            }
        }
    }

    private fun checkListState(state: NewsListState) {
        when (state) {
            is NewsListState.Loading -> {
                Toast.makeText(requireContext(), "loading news", Toast.LENGTH_SHORT).show()
            }
            is NewsListState.ListSuccess -> {
                adapter.submitList(state.data)
            }
            is NewsListState.Error -> {
                Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getList() {
        newsViewModel.getNewsList()
    }

    private fun initRecycler() {
        binding.rvNews.apply {
            adapter = this@StartScreenFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }


    }

}
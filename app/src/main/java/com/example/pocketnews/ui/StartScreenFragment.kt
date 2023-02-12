package com.example.pocketnews.ui

import android.os.Bundle
import android.util.Log
import android.view.View
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
import com.example.pocketnews.ui.list.NewsViewModel
import kotlinx.coroutines.flow.collectLatest
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
//        newsViewModel.getPagingFlow()
        getdata()

        binding.fabUpload.setOnClickListener {
            Log.d("HAPPY","fab press")
            getdata()

        }
    }

    private fun getdata() {
           viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                newsViewModel.items.collectLatest{
                    adapter.submitData(it)
                }
            }
        }
    }

    private fun initRecycler() {
        binding.rvNews.apply {
            adapter = this@StartScreenFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

}
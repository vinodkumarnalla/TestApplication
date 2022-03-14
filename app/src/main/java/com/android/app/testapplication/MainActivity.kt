package com.android.app.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.app.testapplication.databinding.ActivityLayoutBinding
import com.android.app.testapplication.network.Response

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initObservables()
        mainViewModel.fetchData()
    }

    private fun initObservables() {
        mainViewModel.getErrorLiveData().observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        mainViewModel.getProgressLiveData().observe(this, {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })
        mainViewModel.getResultLiveData().observe(this, {
            setupAdapter(it)
        })
    }

    private fun setupAdapter(list: List<Response>) {
        val adapter = ItemsListAdapter(list)
        val layoutManager = LinearLayoutManager(this)
        binding.rvItems.layoutManager = layoutManager
        binding.rvItems.adapter = adapter
        adapter.getOnClickLiveData().observe(this, {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.DETAILED_OBJECT, it)
            startActivity(intent)
        })
    }
}
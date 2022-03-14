package com.android.app.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.app.testapplication.databinding.ActivityDetailsBinding
import com.android.app.testapplication.databinding.ActivityLayoutBinding
import com.android.app.testapplication.network.Response
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    companion object {
        const val DETAILED_OBJECT = "detailed_object"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val data = intent.extras?.get(DETAILED_OBJECT) as Response?
        if (data != null) {
            binding.txtTitle.text = data.name
            binding.txtxDesc.text = data.description
            Glide.with(binding.root.context).load(data.image).into(binding.ivDetailImage)
        }

    }
}
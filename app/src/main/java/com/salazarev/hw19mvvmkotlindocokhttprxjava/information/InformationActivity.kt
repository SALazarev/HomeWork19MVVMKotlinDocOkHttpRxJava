package com.salazarev.hw19mvvmkotlindocokhttprxjava.information

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.salazarev.hw19mvvmkotlindocokhttprxjava.databinding.ActivityInformationBinding
import com.salazarev.hw19mvvmkotlindocokhttprxjava.list.ListActivity

class InformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInformationBinding

    private lateinit var viewModel: InformationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(InformationViewModel::class.java)
        if (intent.hasExtra(ListActivity.ID)) binding.tvText.text =
            intent.getStringExtra(ListActivity.ID)
    }
}
package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.salazarev.hw19mvvmkotlindocokhttprxjava.databinding.ActivityInformationBinding
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.ListActivity
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.rv.ClickListener
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.rv.ItemAdapter

class InformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInformationBinding

    private lateinit var viewModel: InformationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id =
            if (intent.hasExtra(ListActivity.ID)) intent.getStringExtra(ListActivity.ID) else ""




        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return InformationViewModel(id) as T
            }
        }).get(InformationViewModel::class.java)

        viewModel.liveData.observe(this, {
            binding.tvText.text = it.toString()
        })

    }
}
package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.salazarev.hw19mvvmkotlindocokhttprxjava.databinding.ActivityInformationBinding
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.BaseActivity
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.ListActivity

class InformationActivity : BaseActivity() {
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
                return InformationViewModel(getDependency(), id) as T
            }
        }).get(InformationViewModel::class.java)

        viewModel.liveData.observe(this, {
            val text = "$it PLN"
            binding.tvGoldCost.text = text
        })

    }
}
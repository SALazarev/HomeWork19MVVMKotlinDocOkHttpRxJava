package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.salazarev.hw19mvvmkotlindocokhttprxjava.databinding.ActivityListBinding
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.BaseActivity
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information.InformationActivity
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.rv.ClickListener
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.rv.ItemAdapter

class ListActivity : BaseActivity() {
    companion object {
        const val ID = "ID"
    }

    private lateinit var binding: ActivityListBinding
    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ListViewModel(getDependency()) as T
            }
        }).get(ListViewModel::class.java)



        binding.rvItems.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvItems.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        viewModel.liveData.observe(this, {
            binding.rvItems.adapter = ItemAdapter(it, object : ClickListener {
                override fun onClick(id: String) {
                    startInformationActivity(id)
                }

            })
        })
    }

    private fun startInformationActivity(id: String) {
        val intent = Intent(this, InformationActivity::class.java).apply {
            putExtra(ID, id)
        }
        startActivity(intent)
    }


}
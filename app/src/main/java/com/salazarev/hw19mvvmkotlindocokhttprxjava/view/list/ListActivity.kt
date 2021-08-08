package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.salazarev.hw19mvvmkotlindocokhttprxjava.databinding.ActivityListBinding
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information.InformationActivity
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.rv.ClickListener
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.rv.ItemAdapter

private lateinit var binding: ActivityListBinding
private lateinit var listViewModel: ListViewModel

class ListActivity : AppCompatActivity() {

    companion object {
        const val ID = "ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        binding.rvItems.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        liveData.observe(this, {
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
package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingResource
import com.salazarev.hw19mvvmkotlindocokhttprxjava.ProjectApp
import com.salazarev.hw19mvvmkotlindocokhttprxjava.R
import com.salazarev.hw19mvvmkotlindocokhttprxjava.databinding.ActivityListBinding
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.view.QuotationListItem
import com.salazarev.hw19mvvmkotlindocokhttprxjava.mytest.MessageDelayer
import com.salazarev.hw19mvvmkotlindocokhttprxjava.mytest.SimpleIdlingResource
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.ListViewModelFactory
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information.InformationActivity
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.rv.ClickListener
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.rv.ItemAdapter
import javax.inject.Inject


/**
 * Активити списка котировок. Показывает дату каждой котировки.
 */
class ListActivity : AppCompatActivity(), MessageDelayer.DelayerCallback {
    companion object {
        /**
         * Ключ даты котировки.
         */
        const val DATE = "DATE"
    }

    private var idlingResource: SimpleIdlingResource? = null

    private lateinit var binding: ActivityListBinding

    private lateinit var viewModel: ListViewModel

    @Inject
    lateinit var listViewModelFactory: ListViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        provideDependencies()

        viewModel = ViewModelProvider(this, listViewModelFactory).get(ListViewModel::class.java)

        binding.rvItems.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvItems.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        MessageDelayer.processMessage("complete", this, idlingResource)
    }

    private fun provideDependencies() {
        ProjectApp.getAppComponent(this).inject(this)
    }

    private fun setObservers() {
        viewModel.quotations.observe(this, this::showData)
        viewModel.progress.observe(this, this::showProgress)
        viewModel.errors.observe(this, this::showError)

    }

    private fun showData(data: List<QuotationListItem>) {
        binding.rvItems.adapter = ItemAdapter(data, object : ClickListener {
            override fun onClick(id: String) {
                startInformationActivity(id)
            }
        })
    }

    private fun showProgress(visible: Boolean) {
        binding.pbProgress.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun showError(error: Throwable) {
        AlertDialog.Builder(this).apply {
            setTitle(error.javaClass.canonicalName)
            setMessage(error.message)
            setPositiveButton(getString(R.string.try_again)) { dialog, _ ->
                viewModel.tryAgain()
                dialog.cancel()
            }
            create()
            show()
        }
    }

    private fun startInformationActivity(id: String) {
        val intent = Intent(this, InformationActivity::class.java).apply {
            putExtra(DATE, id)
        }
        startActivity(intent)
    }

    @VisibleForTesting
    @NonNull
    fun getIdlingResource(): IdlingResource? {
        if (idlingResource == null) {
            idlingResource = SimpleIdlingResource()
        }
        return idlingResource
    }

    override fun onDone(text: String?) {
        setObservers()
    }

}
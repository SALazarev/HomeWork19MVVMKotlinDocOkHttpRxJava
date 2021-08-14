package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.salazarev.hw19mvvmkotlindocokhttprxjava.ProjectApp
import com.salazarev.hw19mvvmkotlindocokhttprxjava.R
import com.salazarev.hw19mvvmkotlindocokhttprxjava.databinding.ActivityInformationBinding
import com.salazarev.hw19mvvmkotlindocokhttprxjava.domain.QuotationInteractor
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.domain.Quotation
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.BaseActivity
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.ListActivity
import javax.inject.Inject

/**
 * Активити информации о котировке. Показывает дату котировки и цену золота.
 */
class InformationActivity : BaseActivity() {
    private lateinit var binding: ActivityInformationBinding

    private lateinit var viewModel: InformationViewModel

    @Inject
    lateinit var interactor: QuotationInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        provideDependencies()
        val id =
            if (intent.hasExtra(ListActivity.DATE)) intent.getStringExtra(ListActivity.DATE) else ""

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return InformationViewModel(getDependency(), id) as T
            }
        }).get(InformationViewModel::class.java)

        setObservers()
    }

    private fun provideDependencies() {
        ProjectApp.component.inject(this)
    }

    private fun setObservers() {
        viewModel.quotation.observe(this, ::showData)
        viewModel.progress.observe(this, ::showProgress)
        viewModel.errors.observe(this, ::showError)
    }

    private fun showData(data: Quotation) {
        val mainText = "${resources.getString(R.string.gold_cost_in_poland_on)} ${data.date}:"
        val priceText = "${data.price} PLN"
        binding.tvTextHead.text = mainText
        binding.tvGoldCost.text = priceText
    }

    private fun showError(error: Throwable) {
        AlertDialog.Builder(this).apply {
            setTitle(error.javaClass.canonicalName)
            setMessage(error.message)
            setPositiveButton("Попробовать снова") { dialog, _ ->
                viewModel.tryAgain()
                dialog.cancel()
            }
            create()
            show()
        }
    }

    private fun showProgress(visible: Boolean) {
        binding.pbProgress.visibility = if (visible) View.VISIBLE else View.GONE
    }
}
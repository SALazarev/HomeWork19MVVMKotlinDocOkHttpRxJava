package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information

import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.IdlingResource
import com.salazarev.hw19mvvmkotlindocokhttprxjava.ProjectApp
import com.salazarev.hw19mvvmkotlindocokhttprxjava.R
import com.salazarev.hw19mvvmkotlindocokhttprxjava.databinding.ActivityInformationBinding
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.domain.Quotation
import com.salazarev.hw19mvvmkotlindocokhttprxjava.mytest.MessageDelayer
import com.salazarev.hw19mvvmkotlindocokhttprxjava.mytest.SimpleIdlingResource
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.InformationViewModelFactory
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.ListActivity
import javax.inject.Inject

/**
 * Активити информации о котировке. Показывает дату котировки и цену золота.
 */
class InformationActivity : AppCompatActivity(), MessageDelayer.DelayerCallback {
    private lateinit var binding: ActivityInformationBinding

    private lateinit var viewModel: InformationViewModel

    private var idlingResource: SimpleIdlingResource? = null

    @Inject
    lateinit var informationViewModelFactory: InformationViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        provideDependencies()

        viewModel = ViewModelProvider(
            this,
            informationViewModelFactory
        ).get(InformationViewModel::class.java)

        MessageDelayer.processMessage("complete", this, idlingResource)
    }

    private fun provideDependencies() {
        val id =
            if (intent.hasExtra(ListActivity.DATE)) intent.getStringExtra(ListActivity.DATE) else ""
        ProjectApp.getAppComponent(this).getViewModelComponent().id(id).build().inject(this)
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
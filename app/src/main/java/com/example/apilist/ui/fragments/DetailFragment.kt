package com.example.apilist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.apilist.R
import com.example.apilist.ui.viewmodels.DetailViewModel
import com.example.apilist.ui.viewmodels.DetailViewModelFactory
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {
    companion object {
        const val KEY_TITLE = "title"
        const val KEY_URL = "url"
    }

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(
            arguments?.get(KEY_TITLE) as String,
            arguments?.get(KEY_URL) as String
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title.text = viewModel.title

        webview.settings.loadWithOverviewMode = true
        webview.settings.useWideViewPort = true
        webview.loadUrl(viewModel.url)
    }
}
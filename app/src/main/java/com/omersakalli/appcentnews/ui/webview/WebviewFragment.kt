package com.omersakalli.appcentnews.ui.webview

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omersakalli.appcentnews.R

class WebviewFragment : Fragment() {

    companion object {
        fun newInstance() = WebviewFragment()
    }

    private lateinit var viewModel: WebviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.webview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WebviewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
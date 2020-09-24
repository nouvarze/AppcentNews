package com.omersakalli.appcentnews.ui.webview

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.omersakalli.appcentnews.R
import com.omersakalli.appcentnews.databinding.FragmentHomeBinding
import com.omersakalli.appcentnews.databinding.WebviewFragmentBinding

class WebviewFragment : Fragment() {

    companion object {
        fun newInstance() = WebviewFragment()
    }

    private lateinit var viewModel: WebviewViewModel
    private lateinit var binding: WebviewFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.webview_fragment,container,false)



        binding.webview.loadUrl(this.requireArguments().getString("article_url")!!)

        (activity as AppCompatActivity).supportActionBar!!.title = "News Source"

        return binding.root
    }



}
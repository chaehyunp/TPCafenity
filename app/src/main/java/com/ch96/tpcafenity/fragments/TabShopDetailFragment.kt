package com.ch96.tpcafenity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.activities.ShopInfoActivity
import com.ch96.tpcafenity.databinding.FragmentTabShopDetailBinding


class TabShopDetailFragment : Fragment() {

    val binding:FragmentTabShopDetailBinding by lazy { FragmentTabShopDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.webview.webViewClient = WebViewClient()
        binding.webview.webChromeClient = WebChromeClient()
        binding.webview.settings.javaScriptEnabled = true

        //ShopInfoActivity에서 adapter를 통해 intent가 가져온 값 가져오기
        val sia = requireActivity() as ShopInfoActivity
        sia.place_url.apply {
            binding.webview.loadUrl(this ?: "")
        }
        return binding.root
    }

}
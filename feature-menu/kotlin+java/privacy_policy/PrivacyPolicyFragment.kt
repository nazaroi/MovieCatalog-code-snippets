package com.nazaroi.feature_menu.privacy_policy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import com.nazaroi.base.fragment.BindingFragment
import com.nazaroi.common.screen.Screen
import com.nazaroi.common.shared.SharedViewModel
import com.nazaroi.feature_menu.databinding.FragmentPrivacyPolicyBinding

class PrivacyPolicyFragment : BindingFragment<FragmentPrivacyPolicyBinding>() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentPrivacyPolicyBinding {
        return FragmentPrivacyPolicyBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webview.apply {
            webViewClient = WebViewClient()
            loadUrl("https://doc-hosting.flycricket.io/movie-catalog-privacy-policy/e1d3ec38-1e7c-4dfc-be38-63fde5ffeb73/privacy")
        }

        sharedViewModel.setState(Screen.PrivacyPolicy)
    }
}
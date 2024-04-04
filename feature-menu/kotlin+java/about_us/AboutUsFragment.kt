package com.nazaroi.feature_menu.about_us

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.nazaroi.base.fragment.BindingFragment
import com.nazaroi.common.screen.Screen
import com.nazaroi.common.shared.SharedViewModel
import com.nazaroi.feature_menu.databinding.FragmentAboutUsBinding

class AboutUsFragment : BindingFragment<FragmentAboutUsBinding>() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentAboutUsBinding {
        return FragmentAboutUsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setState(Screen.AboutUs)
    }
}
package com.nazaroi.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding
        get() = _binding
            ?: throw IllegalStateException("Trying to access the binding outside of view lifecycle.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = createViewBinding(inflater, container)
        return binding.root
    }

    protected abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
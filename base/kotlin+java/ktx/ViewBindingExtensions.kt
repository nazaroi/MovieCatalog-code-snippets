package com.nazaroi.base.ktx

import android.content.Context
import androidx.viewbinding.ViewBinding

val ViewBinding.context: Context
    get() = root.context
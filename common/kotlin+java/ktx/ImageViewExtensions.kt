package com.nazaroi.common.ktx

import android.widget.ImageView
import androidx.navigation.NavController
import com.nazaroi.common.R

fun ImageView.setBackNavigation(navController: NavController) {
    setImageResource(R.drawable.ic_arrow_back)
    setOnClickListener { navController.popBackStack() }
}
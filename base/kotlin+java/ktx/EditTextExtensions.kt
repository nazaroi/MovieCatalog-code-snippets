package com.nazaroi.base.ktx

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

fun EditText.streamTextChanges(): Flow<String> {
    return callbackFlow {
        val watcher = doAfterTextChanged {
            trySend(it?.toString() ?: "").isSuccess
        }

        awaitClose { removeTextChangedListener(watcher) }
    }.onStart { emit(text.toString()) }
}
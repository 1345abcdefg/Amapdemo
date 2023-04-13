@file:Suppress("UNUSED_EXPRESSION")

package com.example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

suspend fun <T> request(
    block: suspend () -> T,
    coroutineScope: CoroutineScope,
) : Deferred<T> {

    return coroutineScope.async {
        val result = block()
        result
    }
}
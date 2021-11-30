package com.samsung.moviflex.repositories

import android.content.Context
import android.widget.Toast
import com.samsung.moviflex.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun showToast(context: Context) {
    withContext(Dispatchers.Main) {
        Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_SHORT).show()
    }
}

suspend fun Context.withToast(block: suspend () -> Unit) = withContext(Dispatchers.IO) {
    try {
        block()
    } catch (e: IOException) {
        showToast(this@withToast)
    } catch (e: HttpException) {
        showToast(this@withToast)
    }
}
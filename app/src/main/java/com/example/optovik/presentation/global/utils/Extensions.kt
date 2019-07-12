package com.example.optovik.presentation.global.utils

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager


fun showKeyboard(context: Context) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun hideKeyboard(context: Context, view: View) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun <T : Fragment> T.withArgs(
    argsBuilder: Bundle.() -> Unit
): T =
    this.apply {
        arguments = Bundle().apply(argsBuilder)
    }
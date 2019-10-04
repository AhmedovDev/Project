package ru.diitcenter.optovik.presentation.global.utils

import android.app.Activity
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

fun Fragment.hideKeyboard() = activity?.hideKeyboard()


fun Activity.hideKeyboard() = hideKeyboard(if (currentFocus == null) View(this) else currentFocus)

fun Context.hideKeyboard(view: View) {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun <T : Fragment> T.withArgs(
    argsBuilder: Bundle.() -> Unit
): T =
    this.apply {
        arguments = Bundle().apply(argsBuilder)
    }
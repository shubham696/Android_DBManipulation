package com.example.sampleroomdbapp.Util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class HelperUtility(val context: Context?) {

    fun hideKeyBoard(editText: EditText?) {
        if (context != null && editText != null) {
            val im =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }
}
package ru.daryasoft.meetropolys

import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView

class Utils {
    companion object {

        fun hideKeyboard(activity: Activity) {
            val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity.window.decorView.rootView.windowToken, 0)
        }

        fun showSnack(view: View, snackView: View, text: String) {
            val errorTextView = snackView.findViewById<TextView>(R.id.sign_error_text)
            errorTextView.text = text
            showSnack(view, snackView)
        }

        private fun showSnack(view: View, snackView: View) {
            val snackBar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
            val layout = snackBar.view as Snackbar.SnackbarLayout
            val textView = layout.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView

            textView.visibility = View.INVISIBLE
            layout.setPadding(0, 0, 0, 0)
            layout.addView(snackView, 0)

            snackBar.show()
        }
    }
}
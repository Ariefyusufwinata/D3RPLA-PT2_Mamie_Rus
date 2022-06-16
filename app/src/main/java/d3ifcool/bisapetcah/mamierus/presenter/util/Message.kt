package d3ifcool.bisapetcah.mamierus.presenter.util

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar

class Message {
    private fun alert (mContext : View,str : String) {
        val snack : Snackbar = Snackbar.make(
            mContext,
            str,
            Snackbar.LENGTH_LONG
        )
        val view : View = snack.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snack.show()
    }
}
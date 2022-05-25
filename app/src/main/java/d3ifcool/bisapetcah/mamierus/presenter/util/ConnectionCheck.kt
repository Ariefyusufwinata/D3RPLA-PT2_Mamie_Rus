package d3ifcool.bisapetcah.mamierus.presenter.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class ConnectionCheck {

    fun isNetworkAvailable(context: Context) : String {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val actNetwork = connectivityManager.getNetworkCapabilities(network)
            return if (actNetwork != null) {
                when {
                    actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        "WIFI"
                    }
                    actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        "CELLULAR"
                    }
                    else -> {
                        "NO"
                    }
                }
            } else {
                "NO"
            }
        }
        return "NO"
    }

}
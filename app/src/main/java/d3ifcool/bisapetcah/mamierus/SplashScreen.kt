package d3ifcool.bisapetcah.mamierus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import d3ifcool.bisapetcah.mamierus.core.helper.DELAYTIME
import d3ifcool.bisapetcah.mamierus.core.helper.SPLASHSCREEN
import d3ifcool.bisapetcah.mamierus.databinding.ActivitySplashScreenBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.auth.PortalScreenActivity
import d3ifcool.bisapetcah.mamierus.presenter.util.ConnectionCheck

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding
    private val connectionCheck = ConnectionCheck()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        object : Thread() {
            override fun run() {
                try {
                    checkNetworkToNavigation()
                } catch (e: Exception) {
                    Log.i(SPLASHSCREEN, "Failed! ${e.localizedMessage}")
                }
            }
        }.start()
    }

    private fun checkNetworkToNavigation() {
        if(connectionCheck.isNetworkAvailable(this@SplashScreen) == "WIFI") {
            alert("Terhubung Dengan WIFI!")
            Thread.sleep(DELAYTIME)
            navigation(true)
        }
        if(connectionCheck.isNetworkAvailable(this@SplashScreen) == "CELLULAR") {
            alert("Terhubung Dengan Data!")
            Thread.sleep(DELAYTIME)
            navigation(true)
        }
        if(connectionCheck.isNetworkAvailable(this@SplashScreen) == "NO") {
            alert("Tidak Ada Internet!")
            Thread.sleep(DELAYTIME)
            navigation(false)
        }
    }

    private fun navigation(network : Boolean) {
        when(network) {
            true -> {
                startActivity(Intent(baseContext, PortalScreenActivity::class.java))
                finish()
            }
            false -> {
                alert("Mohon Pastikan Terhubung Internet!")
            }
        }
    }

    private fun alert (str : String) {
        val snack : Snackbar = Snackbar.make(
            binding.root,
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
package d3ifcool.bisapetcah.mamierus

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import d3ifcool.bisapetcah.mamierus.core.DELAYTIME
import d3ifcool.bisapetcah.mamierus.core.SPLASHSCREEN
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenSettingsBinding
import d3ifcool.bisapetcah.mamierus.databinding.ActivitySplashScreenBinding
import d3ifcool.bisapetcah.mamierus.ui.PortalScreenActivity
import d3ifcool.bisapetcah.mamierus.ui.konsumen.SettingsActivityK

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        object : Thread() {
            override fun run() {
                try {
                    sleep(DELAYTIME)
                    startActivity(Intent(baseContext, MainActivity::class.java))
                    finish()
                } catch (e: Exception) {
                    Log.i("$SPLASHSCREEN.Process", "Failed! ${e.localizedMessage}")
                }
            }
        }.start()
    }

}
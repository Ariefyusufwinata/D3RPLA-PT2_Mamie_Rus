package d3ifcool.bisapetcah.mamierus

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import d3ifcool.bisapetcah.mamierus.core.DELAYTIME
import d3ifcool.bisapetcah.mamierus.core.SPLASHSCREEN
import d3ifcool.bisapetcah.mamierus.databinding.ActivitySplashScreenBinding
import d3ifcool.bisapetcah.mamierus.ui.PortalScreenActivity

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
                    startActivity(Intent(baseContext, PortalScreenActivity::class.java))
                    finish()
                } catch (e: Exception) {
                    Log.i("$SPLASHSCREEN.Process", "Failed! ${e.localizedMessage}")
                }
            }
        }.start()
    }
}
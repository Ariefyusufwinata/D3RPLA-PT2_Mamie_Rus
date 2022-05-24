package d3ifcool.bisapetcah.mamierus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import d3ifcool.bisapetcah.mamierus.core.helper.DELAYTIME
import d3ifcool.bisapetcah.mamierus.core.helper.SPLASHSCREEN
import d3ifcool.bisapetcah.mamierus.databinding.ActivitySplashScreenBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.auth.PortalScreenActivity

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
                    Log.i(SPLASHSCREEN, "Failed! ${e.localizedMessage}")
                }
            }
        }.start()
    }

}
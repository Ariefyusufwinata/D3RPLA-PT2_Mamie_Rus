package d3ifcool.bisapetcah.mamierus.presenter.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import d3ifcool.bisapetcah.mamierus.MainActivity
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPortalScreenBinding
import d3ifcool.bisapetcah.mamierus.presenter.util.ConnectionCheck

class PortalScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPortalScreenBinding
    private val connectionCheck = ConnectionCheck()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPortalScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if(connectionCheck.isNetworkAvailable(this) == "WIFI") {
//            alert("Terhubung Dengan WIFI!")
//            navigation(true)
//        }
//        if(connectionCheck.isNetworkAvailable(this) == "CELLULAR") {
//            alert("Terhubung Dengan Data!")
//            navigation(true)
//        }
//        if(connectionCheck.isNetworkAvailable(this) == "NO") {
//            alert("Tidak Ada Internet!")
//            navigation(false)
//        }

        binding.apply {
            btnLogin.setOnClickListener {
                Intent(this@PortalScreenActivity, LoginActivity::class.java).also {
                    startActivity(it)
                }
            }

            btnDaftar.setOnClickListener {
                Intent(this@PortalScreenActivity, RegisterActivity::class.java).also {
                    startActivity(it)
                }
            }

            btnLewati.setOnClickListener {
                Intent(this@PortalScreenActivity, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

    private fun navigation(network : Boolean){
        binding.apply {
            btnLogin.setOnClickListener {
                when(network) {
                    true -> {
                        Intent(this@PortalScreenActivity, LoginActivity::class.java).also {
                            startActivity(it)
                        }
                    }
                    false -> {
                        alert("Perikasa Sambungan Anda!")
                    }
                }
            }

            btnDaftar.setOnClickListener {
                when(network) {
                    true -> {
                        Intent(this@PortalScreenActivity, RegisterActivity::class.java).also {
                            startActivity(it)
                        }
                    }
                    false -> {
                        alert("Perikasa Sambungan Anda!")
                    }
                }
            }

            btnLewati.setOnClickListener {
                when(network) {
                    true -> {
                        Intent(this@PortalScreenActivity, MainActivity::class.java).also {
                            startActivity(it)
                        }
                    }
                    false -> {
                        alert("Perikasa Sambungan Anda!")
                    }
                }
            }
        }
    }

    private fun alert (str : String) {
        val snack : Snackbar = Snackbar.make(
            binding.root,
            str,
            Snackbar.LENGTH_SHORT
        )
        val view : View = snack.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snack.show()
    }

}
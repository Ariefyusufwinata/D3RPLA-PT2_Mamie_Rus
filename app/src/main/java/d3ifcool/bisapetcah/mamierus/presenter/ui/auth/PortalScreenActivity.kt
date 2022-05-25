package d3ifcool.bisapetcah.mamierus.presenter.ui.auth

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import d3ifcool.bisapetcah.mamierus.MainActivity
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPortalScreenBinding

class PortalScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPortalScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPortalScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkConnection()

        binding.btnLogin.setOnClickListener {
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.btnDaftar.setOnClickListener {
            Intent(this, RegisterActivity::class.java). also {
                startActivity(it)
            }
        }

        binding.btnLewati.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun checkConnection() {
        val connection = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connection.activeNetworkInfo
        if (activeNetwork != null) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                alert("Terhubung Internet Dengan WIFI!")
            }
            if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                alert("Terhubung Internet Dengan Data!")
            }
        } else {
            alert("Tidak Ada Koneksi")
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
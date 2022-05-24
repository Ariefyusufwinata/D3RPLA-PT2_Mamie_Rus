package d3ifcool.bisapetcah.mamierus.presenter.ui.auth

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPortalScreenBinding
import d3ifcool.bisapetcah.mamierus.MainActivity

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
        val connection = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connection.activeNetworkInfo
        if (activeNetwork != null) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                Snackbar.make(binding.root, "Terhubung Internet Dengan WIFI!", Snackbar.LENGTH_LONG).show()
            }
            if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                Snackbar.make(binding.root, "Terhubung Internet Dengan Data!", Snackbar.LENGTH_LONG).show()
            }
        } else {
            Snackbar.make(binding.root, "Tidak Ada Koneksi Internet!", Snackbar.LENGTH_LONG).show()
        }
    }


}
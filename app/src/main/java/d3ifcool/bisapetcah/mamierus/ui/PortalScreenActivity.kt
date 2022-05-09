package d3ifcool.bisapetcah.mamierus.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPortalScreenBinding
import d3ifcool.bisapetcah.mamierus.view.MainActivity

class PortalScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPortalScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPortalScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}
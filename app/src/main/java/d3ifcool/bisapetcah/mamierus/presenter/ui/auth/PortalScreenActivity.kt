package d3ifcool.bisapetcah.mamierus.presenter.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import d3ifcool.bisapetcah.mamierus.presenter.ui.publik.MainActivity
import d3ifcool.bisapetcah.mamierus.core.datastore.PreferencesData
import d3ifcool.bisapetcah.mamierus.core.helper.TemporaryObject
import d3ifcool.bisapetcah.mamierus.databinding.ActivityAuthPortalScreenBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.konsumen.MainActivityK
import d3ifcool.bisapetcah.mamierus.presenter.ui.pemilik.MainActivityP

class PortalScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAuthPortalScreenBinding
    private lateinit var sharedPref : PreferencesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthPortalScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = PreferencesData(this@PortalScreenActivity)

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

    override fun onStart() {
        super.onStart()
        if(sharedPref.getBoolean(TemporaryObject.PREFS_STATUS)) {
            if(sharedPref.getString(TemporaryObject.PREFS_ROLE) == "konsumen"){
                startActivity(Intent(this@PortalScreenActivity, MainActivityK::class.java))
                finish()
            }
            if(sharedPref.getString(TemporaryObject.PREFS_ROLE) == "pemilik"){
                startActivity(Intent(this@PortalScreenActivity, MainActivityP::class.java))
                finish()
            }
        }
    }

}
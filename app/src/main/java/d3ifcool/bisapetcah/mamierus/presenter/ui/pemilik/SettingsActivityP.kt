package d3ifcool.bisapetcah.mamierus.presenter.ui.pemilik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.core.datastore.PreferencesData
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPemilikSettingsBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.auth.PortalScreenActivity

class SettingsActivityP : AppCompatActivity() {

    private lateinit var binding : ActivityPemilikSettingsBinding
    private lateinit var sharedPref : PreferencesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemilikSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnLihatKomentar.setOnClickListener {
                Intent(this@SettingsActivityP, GetCommentActivityP::class.java).also {
                    startActivity(it)
                }
            }

            btnAboutApp.setOnClickListener {
                Intent(this@SettingsActivityP, AboutAppActivityP::class.java).also {
                    startActivity(it)
                }
            }

            btnLogout.setOnClickListener {
                sharedPref.clear()

                Intent(this@SettingsActivityP, PortalScreenActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }
}
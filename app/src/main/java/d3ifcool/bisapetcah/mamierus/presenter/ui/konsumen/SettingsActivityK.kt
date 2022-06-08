package d3ifcool.bisapetcah.mamierus.presenter.ui.konsumen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.core.datastore.PreferencesData
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenSettingsBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.auth.PortalScreenActivity
import d3ifcool.bisapetcah.mamierus.presenter.ui.pemilik.AboutAppActivityP
import d3ifcool.bisapetcah.mamierus.presenter.ui.pemilik.GetCommentActivityP

class SettingsActivityK : AppCompatActivity() {

    private lateinit var binding : ActivityKonsumenSettingsBinding
    private lateinit var sharedPref : PreferencesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsumenSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = PreferencesData(this@SettingsActivityK)

        binding.apply {
            btnAddComment.setOnClickListener {
                Intent(this@SettingsActivityK, CommentActivityK::class.java).also {
                    startActivity(it)
                }
            }

            btnAboutApp.setOnClickListener {
                Intent(this@SettingsActivityK, AboutAppActivityP::class.java).also {
                    startActivity(it)
                }
            }

            btnLogout.setOnClickListener {
                sharedPref.clear()

                Intent(this@SettingsActivityK, PortalScreenActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }
}
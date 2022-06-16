package d3ifcool.bisapetcah.mamierus.presenter.ui.costumer

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import d3ifcool.bisapetcah.mamierus.R
import d3ifcool.bisapetcah.mamierus.core.datastore.PreferencesData
import d3ifcool.bisapetcah.mamierus.core.helper.Constant
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PRESENTER
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenSettingsBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.auth.PortalScreenActivity
import d3ifcool.bisapetcah.mamierus.presenter.viewmodel.auth.SettingsViewModel

class SettingsActivityK : AppCompatActivity() {

    private lateinit var binding: ActivityKonsumenSettingsBinding
    private lateinit var model: SettingsViewModel
    private lateinit var session: PreferencesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsumenSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = resources.getString(R.string.settings)
        val actionBar = supportActionBar
        actionBar?.title = title

        session = PreferencesData(this@SettingsActivityK)
        val token = session.getString(Constant.PREFS_TOKEN).toString()

        model = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[SettingsViewModel::class.java]
        model.getProfile(token)
        model.getValue().observe(this) {
            when (it != null) {
                true -> {
                    val name = it.data?.name.toString()
                    val phone = it.data?.profile?.phone.toString()

                    binding.apply {
                        tvName.text = name
                        tvPhone.text = phone
                    }
                }
                false -> {
                    Toast.makeText(this, FAILURE_PRESENTER, Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.apply {
            btnProfile.setOnClickListener {
                startActivity(Intent(this@SettingsActivityK, ProfileActivityK::class.java))
            }

            btnAboutApp.setOnClickListener {
                Intent(this@SettingsActivityK, AboutAppActivityK::class.java).also {
                    startActivity(it)
                }
            }

            btnLogout.setOnClickListener {
                model.logoutUser(token)
                session.clear()
                Intent(this@SettingsActivityK, PortalScreenActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(Menu.NONE, 1, Menu.NONE, "Alamat Warung")
        menu?.add(Menu.NONE, 2, Menu.NONE, "Pengaturan")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            1 -> startActivity(Intent(this, AddressActivityK::class.java))
            2 -> startActivity(Intent(this, SettingsActivityK::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
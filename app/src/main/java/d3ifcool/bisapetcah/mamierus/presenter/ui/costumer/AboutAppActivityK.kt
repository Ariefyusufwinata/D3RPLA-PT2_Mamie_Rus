package d3ifcool.bisapetcah.mamierus.presenter.ui.costumer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import d3ifcool.bisapetcah.mamierus.R
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenAboutAppBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.auth.LoginActivity
import d3ifcool.bisapetcah.mamierus.presenter.ui.general.AboutAppActivity
import d3ifcool.bisapetcah.mamierus.presenter.ui.general.AddressActivity

class AboutAppActivityK : AppCompatActivity() {

    private lateinit var binding : ActivityKonsumenAboutAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsumenAboutAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = resources.getString(R.string.about_app)
        val actionBar = supportActionBar
        actionBar?.title = title
        actionBar?.setDisplayHomeAsUpEnabled(true)
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
        when(item.itemId) {
            1 -> startActivity(Intent(this, AddressActivityK::class.java))
            2 ->  startActivity(Intent(this, SettingsActivityK::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
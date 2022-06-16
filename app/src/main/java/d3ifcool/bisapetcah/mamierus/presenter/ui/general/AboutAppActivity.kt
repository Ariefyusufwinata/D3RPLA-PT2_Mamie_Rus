package d3ifcool.bisapetcah.mamierus.presenter.ui.general

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import d3ifcool.bisapetcah.mamierus.R
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPublicAboutAppBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.auth.LoginActivity

class AboutAppActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPublicAboutAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPublicAboutAppBinding.inflate(layoutInflater)
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
        menu?.add(Menu.NONE, 1, Menu.NONE, "Masuk")
        menu?.add(Menu.NONE, 2, Menu.NONE, "Alamat Warung")
        menu?.add(Menu.NONE, 3, Menu.NONE, "Tentang Aplikasi")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            1 -> startActivity(Intent(this, LoginActivity::class.java))
            2 -> startActivity(Intent(this, AddressActivity::class.java))
            3 -> startActivity(Intent(this, AboutAppActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
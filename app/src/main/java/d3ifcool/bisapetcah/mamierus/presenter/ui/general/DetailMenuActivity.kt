package d3ifcool.bisapetcah.mamierus.presenter.ui.general

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import d3ifcool.bisapetcah.mamierus.R
import d3ifcool.bisapetcah.mamierus.core.helper.BASE_WA
import d3ifcool.bisapetcah.mamierus.core.helper.Constant
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PRESENTER
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPublicDetailMenuBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.auth.LoginActivity
import d3ifcool.bisapetcah.mamierus.presenter.viewmodel.general.DetailViewModel

class DetailMenuActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPublicDetailMenuBinding
    private lateinit var model : DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tittle = resources.getString(R.string.detail_menu)
        val actionBar = supportActionBar
        actionBar?.title = tittle
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityPublicDetailMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(Constant.EXTRA_ID, 0)

        model = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]
        model.getDetailProduct(id)
        model.getValue().observe(this) {
            val image = it.data?.images?.get(1)?.path
            val name = it.data?.name.toString()
            val desc = it.data?.description.toString()

            //WhatsApp Value Message
            val phone = it.data?.user?.profile?.phone.toString()
            val message = "*Form%20Pemesanan%20Makanan%20Warung%20Mamie%20Rus*%0A%0A-%20Data%20Diri%20-%0ANama%20%3A%20%0ANo%20HP%20%3A%0AAlamat%20%3A%0A%0A-%20Makanan%20Yang%20Dipesan-%20%0ANama%20Makanan%20%3A%0AJumlah%20%3A"
            val uri = "$BASE_WA$phone?text=$message"

            when(it != null) {
                true -> {
                    binding.apply {
                        Glide.with(this@DetailMenuActivity)
                            .load(image)
                            .centerCrop()
                            .into(imgPhoto)

                        tvMenu.text = name
                        tvDeskripsi.text = desc

                        btnWhatsApp.setOnClickListener {
                            Intent(Intent.ACTION_VIEW).also { the ->
                                the.data = Uri.parse(uri)
                                startActivity(the)
                            }
                        }
                    }
                }
                false -> {
                    Toast.makeText(this@DetailMenuActivity, FAILURE_PRESENTER, Toast.LENGTH_LONG).show()
                }
            }
        }
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
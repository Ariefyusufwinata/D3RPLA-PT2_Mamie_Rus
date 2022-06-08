package d3ifcool.bisapetcah.mamierus.presenter.ui.publik

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import d3ifcool.bisapetcah.mamierus.R
import d3ifcool.bisapetcah.mamierus.core.helper.BASE_WA
import d3ifcool.bisapetcah.mamierus.core.helper.TemporaryObject
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPublicDetailMenuBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.auth.LoginActivity
import d3ifcool.bisapetcah.mamierus.presenter.viewmodel.publik.AddressViewModel
import d3ifcool.bisapetcah.mamierus.presenter.viewmodel.publik.DetailViewModel

class DetailMenuActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPublicDetailMenuBinding
    private lateinit var detailViewModel : DetailViewModel
    private lateinit var addressModel : AddressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tittle = resources.getString(R.string.detail_menu)
        val actionBar = supportActionBar
        actionBar?.title = tittle
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityPublicDetailMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(TemporaryObject.EXTRA_ID, 0)

        loadingTime(true)

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]
        detailViewModel.getDetailProduct(id)
        detailViewModel.getValue().observe(this) {
            val image = it.data?.images?.get(1)?.path
            val name = it.data?.name.toString()
            val desc = it.data?.description.toString()

            Log.d("image-menu", image.toString())

            when(it != null) {
                true -> {
                    binding.apply {
                        Glide.with(this@DetailMenuActivity)
                            .load(image)
                            .centerCrop()
                            .into(imgPhoto)

                        tvMenu.text = name
                        tvDeskripsi.text = desc
                    }
                    loadingTime(false)
                }
                false -> {
                    Toast.makeText(this@DetailMenuActivity, "Tidak Dapat Menampilkan!", Toast.LENGTH_LONG).show()
                    loadingTime(false)
                }
            }
        }

        addressModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[AddressViewModel::class.java]
        addressModel.getAddress()
        addressModel.getValue().observe(this) { it ->
            val phone = it.dataProfile?.phone.toString()
            val message = "*Form%20Pemesanan%20Makanan%20Warung%20Mamie%20Rus*%0A%0A-%20Data%20Diri%20-%0ANama%20%3A%20%0ANo%20HP%20%3A%0AAlamat%20%3A%0A%0A-%20Makanan%20Yang%20Dipesan-%20%0ANama%20Makanan%20%3A%0AJumlah%20%3A"
            val uri = "$BASE_WA$phone?text=$message"
            Log.d("number-phone", phone)

            when(it != null) {
                true -> {
                    binding.btnWhatsApp.setOnClickListener {
                        Intent(Intent.ACTION_VIEW).also {

                            it.data = Uri.parse(uri)
                            startActivity(it)
                        }
                    }
                    loadingTime(false)
                }
                false -> {
                    Toast.makeText(this@DetailMenuActivity, "Tidak Dapat Menampilkan!", Toast.LENGTH_LONG).show()
                    loadingTime(false)
                }
            }
        }
    }

    private fun loadingTime(isTrue : Boolean) {
        binding.apply {
            when(isTrue) {
                true -> {
                    progressBar.visibility = View.VISIBLE
                }
                false -> {
                    progressBar.visibility = View.GONE
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
            1 -> Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
            2 -> Intent(this, AddressActivity::class.java).also {
                startActivity(it)
            }
            3 -> Intent(this, AboutAppActivity::class.java).also {
                startActivity(it)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
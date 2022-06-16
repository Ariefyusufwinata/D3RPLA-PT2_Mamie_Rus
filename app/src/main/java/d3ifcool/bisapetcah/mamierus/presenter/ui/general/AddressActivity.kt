package d3ifcool.bisapetcah.mamierus.presenter.ui.general

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import d3ifcool.bisapetcah.mamierus.R
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PRESENTER
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPublicAddressBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.auth.LoginActivity
import d3ifcool.bisapetcah.mamierus.presenter.viewmodel.general.AddressViewModel

class AddressActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPublicAddressBinding
    private lateinit var model : AddressViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPublicAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = resources.getString(R.string.address)
        val actionBar = supportActionBar
        actionBar?.title = title
        actionBar?.setDisplayHomeAsUpEnabled(true)

        model = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[AddressViewModel::class.java]
            model.getAddress()
            model.getValue().observe(this@AddressActivity) {
                when (it != null) {
                    true -> {
                        binding.apply {
                            val name = it.dataProfile?.name.toString()
                            val desc = it.dataProfile?.description
                            val image = it.dataProfile?.imagesProfile?.get(0)?.path
                            val mapUrl = it.dataProfile?.mapUrl

                            val openClose = it.dataProfile?.isOpen?.toInt()
                            val isStatus: String
                            if (openClose == 1) {
                                isStatus = "Sedang Tutup • Jam Buka 9.30 WIB"
                            } else {
                                isStatus = "Sedang Buka • Jam Buka 9.30 WIB"
                            }

                            //Presenter
                            Glide.with(this@AddressActivity)
                                .load(image)
                                .centerCrop()
                                .into(imgWarung)
                            tvJamBukaTutup.text = isStatus
                            tvMamieRus.text = name
                            tvDeskripsi.text = desc

                            btnGmaps.setOnClickListener {
                                Intent(Intent.ACTION_VIEW).also { the ->
                                    the.data = Uri.parse(mapUrl)
                                    startActivity(the)
                                }
                            }
                        }
                    }
                    false -> {
                        Toast.makeText(this, FAILURE_PRESENTER, Toast.LENGTH_LONG).show()
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

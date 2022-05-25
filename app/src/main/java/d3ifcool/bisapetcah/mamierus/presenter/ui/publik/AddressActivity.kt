package d3ifcool.bisapetcah.mamierus.presenter.ui.publik

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import d3ifcool.bisapetcah.mamierus.R
import d3ifcool.bisapetcah.mamierus.core.helper.INTERNAL_SERVER
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.model.publik.PublicGetProfileResponses
import d3ifcool.bisapetcah.mamierus.databinding.ActivityAddressBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.auth.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = resources.getString(R.string.address)
        val actionBar = supportActionBar
        actionBar?.title = title
        actionBar?.setDisplayHomeAsUpEnabled(true)

        apiItem()
    }

    private fun apiItem() {
        Client.instance.getPublicProfile(
            1
        ).enqueue(object : Callback<PublicGetProfileResponses> {
            override fun onResponse(
                call: Call<PublicGetProfileResponses>,
                response: Response<PublicGetProfileResponses>)
            {
                if (response.isSuccessful) {
                    binding.apply {
                        val name = response.body()?.dataProfile?.name.toString()
                        val desc = response.body()?.dataProfile?.description
                        val image = response.body()?.dataProfile?.imagesProfile?.get(0)?.path
                        val mapUrl = response.body()?.dataProfile?.mapUrl

                        val openClose = response.body()?.dataProfile?.isOpen?.toInt()
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
                            Intent(Intent.ACTION_VIEW).also {
                                it.data = Uri.parse(mapUrl)
                                startActivity(it)
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PublicGetProfileResponses>, t: Throwable) {
                Log.d(INTERNAL_SERVER, t.toString())
            }
        })

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

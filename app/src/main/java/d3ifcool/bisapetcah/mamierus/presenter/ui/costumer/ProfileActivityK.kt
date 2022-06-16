package d3ifcool.bisapetcah.mamierus.presenter.ui.costumer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import d3ifcool.bisapetcah.mamierus.R
import d3ifcool.bisapetcah.mamierus.core.datastore.PreferencesData
import d3ifcool.bisapetcah.mamierus.core.helper.Constant
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PRESENTER
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PUT
import d3ifcool.bisapetcah.mamierus.core.helper.NOTIF
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenProfileBinding
import d3ifcool.bisapetcah.mamierus.presenter.viewmodel.auth.UpdateProfileViewModel

class ProfileActivityK : AppCompatActivity() {

    private lateinit var binding: ActivityKonsumenProfileBinding
    private lateinit var model: UpdateProfileViewModel
    private lateinit var session: PreferencesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsumenProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = resources.getString(R.string.profile)
        val actionBar = supportActionBar
        actionBar?.title = title
        actionBar?.setDisplayHomeAsUpEnabled(true)

        session = PreferencesData(this@ProfileActivityK)
        val token = session.getString(Constant.PREFS_TOKEN).toString()

        model = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[UpdateProfileViewModel::class.java]
        model.getProfile(token)
        model.getValue().observe(this) {
            when (it != null) {
                true -> {
                    val name = it.data?.name.toString()
                    val phone = it.data?.profile?.phone.toString()

                    binding.apply {
                        tvName.text = name
                        tvPhone.text = phone

                        edtNamaPengguna.setText(name)
                        edtNamaPengguna.isEnabled = false

                        edtNoHP.setText(phone)

                        btnSimpan.setOnClickListener {
                            val phoneNew = edtNoHP.text.toString()
                            val passwordNew = edtKataSandi.text.toString()
                            when {
                                phoneNew.isEmpty() -> {
                                    edtNoHP.requestFocus()
                                }
                                passwordNew.isEmpty() -> {
                                    edtKataSandi.requestFocus()
                                }
                                else -> {
                                    object : Thread() {
                                        override fun run() {
                                            try {
                                                model.update(token, passwordNew, phoneNew)
                                                alert("Berhasil mengubah!")
                                                sleep(NOTIF)
                                                startActivity(
                                                    Intent(
                                                        this@ProfileActivityK,
                                                        MainActivityK::class.java
                                                    )
                                                )
                                            } catch (e: Exception) {
                                                Log.i(FAILURE_PUT, "Failed! ${e.localizedMessage}")
                                            }
                                        }
                                    }.start()
                                }
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

    private fun alert(str: String) {
        val snack: Snackbar = Snackbar.make(
            binding.root,
            str,
            Snackbar.LENGTH_LONG
        )
        val view: View = snack.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snack.show()
    }
}
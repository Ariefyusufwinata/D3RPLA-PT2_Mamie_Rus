package d3ifcool.bisapetcah.mamierus.presenter.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import d3ifcool.bisapetcah.mamierus.core.datastore.PreferencesData
import d3ifcool.bisapetcah.mamierus.core.helper.TemporaryObject
import d3ifcool.bisapetcah.mamierus.databinding.ActivityAuthLoginBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.konsumen.MainActivityK
import d3ifcool.bisapetcah.mamierus.presenter.ui.pemilik.MainActivityP
import d3ifcool.bisapetcah.mamierus.presenter.viewmodel.auth.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAuthLoginBinding
    private lateinit var model : LoginViewModel
    private lateinit var sharedPref : PreferencesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = PreferencesData(this@LoginActivity)

        model = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[LoginViewModel::class.java]
        model.getValue().observe(this) {
            val username = it.data?.user?.username.toString()
            val role = it.data?.user?.role.toString()
            val token = it.data?.accessToken.toString()
            when (it != null) {
                 true -> {

                sharedPref.put(TemporaryObject.PREFS_STATUS, true)
                sharedPref.put(TemporaryObject.PREFS_USERNAME, username)
                sharedPref.put(TemporaryObject.PREFS_ROLE, role)
                sharedPref.put(TemporaryObject.PREFS_TOKEN, token)

                     Toast.makeText(this@LoginActivity, "Selamat Datang Pengguna!", Toast.LENGTH_SHORT).show()
                     when(role) {
                         "konsumen" -> startActivity(Intent(this@LoginActivity, MainActivityK::class.java))
                         "pemilik" -> startActivity(Intent(this@LoginActivity, MainActivityP::class.java))
                         else ->    Toast.makeText(this@LoginActivity, "Akun Tidak Ditemukan!", Toast.LENGTH_LONG).show()
                     }
                 }
                false -> Toast.makeText(this@LoginActivity, "Tidak Dapat Menampilkan!", Toast.LENGTH_LONG).show()
            }

        }

        binding.apply {
            btnForget.setOnClickListener {
                Intent(this@LoginActivity, ForgetPasswordActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnDaftar.setOnClickListener {
                Intent(this@LoginActivity, RegisterActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnLogin.setOnClickListener {
                val username = edtNamaPengguna.text.toString()
                val password = edtKataSandi.text.toString()
                model.loginUser(username, password)
            }
        }
    }
}
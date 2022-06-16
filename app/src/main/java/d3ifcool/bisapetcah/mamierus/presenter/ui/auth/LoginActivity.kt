package d3ifcool.bisapetcah.mamierus.presenter.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import d3ifcool.bisapetcah.mamierus.core.datastore.PreferencesData
import d3ifcool.bisapetcah.mamierus.core.helper.Constant
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PUT
import d3ifcool.bisapetcah.mamierus.core.helper.NOTIF
import d3ifcool.bisapetcah.mamierus.databinding.ActivityAuthLoginBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.costumer.MainActivityK
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
                sharedPref.put(Constant.PREFS_STATUS, true)
                sharedPref.put(Constant.PREFS_USERNAME, username)
                sharedPref.put(Constant.PREFS_ROLE, role)
                sharedPref.put(Constant.PREFS_TOKEN, token)

                     alert("Selamat Datang Pengguna!")
                     when(role) {
                         "konsumen" -> startActivity(Intent(this@LoginActivity, MainActivityK::class.java))
//                       "pemilik" -> startActivity(Intent(this@LoginActivity, MainActivityP::class.java))
                         else ->  alert("Role Tidak Terdata!")
                     }
                 }
                else -> Toast.makeText(this@LoginActivity, "Data Ada Tidak Ditemukan", Toast.LENGTH_LONG).show()
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
                when {
                    username.isEmpty() -> {
                        alert("Mohon masukkan nama pengguna!")
                        edtNamaPengguna.requestFocus()
                    }
                    password.isEmpty() -> {
                        alert("Mohon masukkan kata sandi!")
                        edtKataSandi.requestFocus()
                    }
                    else -> {
                        model.login(username, password)
                    }
                }
            }
        }
    }

    private fun alert (str : String) {
        val snack : Snackbar = Snackbar.make(
            binding.root,
            str,
            Snackbar.LENGTH_LONG
        )
        val view : View = snack.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snack.show()
    }
}
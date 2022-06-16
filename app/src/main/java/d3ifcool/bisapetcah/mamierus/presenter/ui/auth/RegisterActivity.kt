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
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PRESENTER
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PUT
import d3ifcool.bisapetcah.mamierus.core.helper.NOTIF
import d3ifcool.bisapetcah.mamierus.core.model.costumer.KonsumenRegisterResponses
import d3ifcool.bisapetcah.mamierus.databinding.ActivityAuthRegisterBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.costumer.MainActivityK
import d3ifcool.bisapetcah.mamierus.presenter.viewmodel.auth.LoginViewModel
import d3ifcool.bisapetcah.mamierus.presenter.viewmodel.auth.RegisterViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAuthRegisterBinding
    private lateinit var model : RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[RegisterViewModel::class.java]

        binding.apply {
            btnMasuk.setOnClickListener {
                Intent(this@RegisterActivity, LoginActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnDaftar.setOnClickListener {
                val username = edtNamaPengguna.text.toString()
                val password = edtKataSandi.text.toString()
                val phone = edtNoHP.text.toString()

                when {
                    edtNamaPengguna.text.length < 5 -> {
                        edtNamaPengguna.requestFocus()
                        alert("Nama pengguna harus lebih dari 5")
                    }
                    edtNoHP.text.length <= 12 -> {
                        edtNamaPengguna.requestFocus()
                        alert("No Hp harus > 12")
                    }
                    edtKataSandi.text.length < 8 -> {
                        edtNamaPengguna.requestFocus()
                        alert("Kata Sandi harus lebih dari 8")
                    }
                    else -> {
                        object : Thread() {
                            override fun run() {
                                try {
                                    model.register(username, password, phone)
                                    alert("Akun Anda Telah Dibuat!")
                                    sleep(NOTIF)
                                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
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
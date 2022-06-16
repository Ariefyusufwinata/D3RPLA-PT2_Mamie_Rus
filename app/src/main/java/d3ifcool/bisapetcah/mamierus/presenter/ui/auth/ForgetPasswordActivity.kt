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
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.helper.INTERNAL_SERVER
import d3ifcool.bisapetcah.mamierus.core.model.auth.ResetPasswordResponses
import d3ifcool.bisapetcah.mamierus.databinding.ActivityAuthForgetPasswordBinding
import d3ifcool.bisapetcah.mamierus.presenter.viewmodel.auth.ForgetPasswordViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAuthForgetPasswordBinding
    private lateinit var model : ForgetPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ForgetPasswordViewModel::class.java]

        binding.apply {
            btnMasuk.setOnClickListener {
                Intent(this@ForgetPasswordActivity, LoginActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnUbah.setOnClickListener {
                val username = edtNamaPengguna.text.toString()
                val password = edtKataSandi.text.toString()

                when {
                    edtNamaPengguna.text.length < 5 -> {
                        edtNamaPengguna.requestFocus()
                        alert("Nama pengguna harus lebih dari 5")
                    }
                    edtKataSandi.text.length < 8 -> {
                        edtNamaPengguna.requestFocus()
                        alert("Kata Sandi harus lebih dari 8")
                    }
                    else -> {
                        model.forgetPassword(username, password)
                        Toast.makeText(this@ForgetPasswordActivity, "Kata Sandi Berhasil Diubah!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@ForgetPasswordActivity, LoginActivity::class.java))
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
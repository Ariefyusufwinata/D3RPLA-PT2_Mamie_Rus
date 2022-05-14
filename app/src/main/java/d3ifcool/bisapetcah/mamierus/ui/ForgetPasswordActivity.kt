package d3ifcool.bisapetcah.mamierus.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.model.ResetPasswordResponses
import d3ifcool.bisapetcah.mamierus.databinding.ActivityForgetPasswordBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var binding : ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnMasuk.setOnClickListener {
                Intent(this@ForgetPasswordActivity, LoginActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnUbah.setOnClickListener {
                val username = edtNamaPengguna.text.toString()
                val password = edtKataSandi.text.toString()
                forgetPassword(username, password)
            }
        }
    }

    private fun forgetPassword(username: String, password: String) {
        Client.instance.reset(
            username,
            password
        ).enqueue(object : Callback<ResetPasswordResponses>{
            override fun onResponse(
                call: Call<ResetPasswordResponses>,
                response: Response<ResetPasswordResponses>
            ) {
                if(response.isSuccessful) {
                    Toast.makeText(this@ForgetPasswordActivity, "Kata Sandi Berhasil Diubah!", Toast.LENGTH_LONG).show()
                    Intent(this@ForgetPasswordActivity, LoginActivity::class.java).also {
                        startActivity(it)
                    }
                } else {
                    Toast.makeText(this@ForgetPasswordActivity, "Mohon Cek Kembali Data Yang Anda Masukkan!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResetPasswordResponses>, t: Throwable) {
                Log.e("Fail Internal Error", t.message.toString())
            }

        })
    }
}
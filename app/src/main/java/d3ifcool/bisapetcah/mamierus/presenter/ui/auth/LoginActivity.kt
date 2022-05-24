package d3ifcool.bisapetcah.mamierus.presenter.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import d3ifcool.bisapetcah.mamierus.core.helper.INTERNAL_SERVER
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.model.auth.LoginResponses
import d3ifcool.bisapetcah.mamierus.databinding.ActivityLoginBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.konsumen.MainActivityK
import d3ifcool.bisapetcah.mamierus.presenter.ui.pemilik.MainActivityP
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                login(username, password)
            }
        }
    }

    private fun login(username : String, password : String) {
        Client.instance.login(
            username,
            password
        ).enqueue(object : Callback<LoginResponses> {
            override fun onResponse(
                call: Call<LoginResponses>,
                server: Response<LoginResponses>
            ) {
                if(server.isSuccessful) {
                    Toast.makeText(this@LoginActivity, "Selamat Datang Pengguna!", Toast.LENGTH_SHORT).show()
                    if("${server.body()?.data?.user?.role}" == "konsumen") {
                        Intent(this@LoginActivity, MainActivityK::class.java).also {
                            startActivity(it)
                        }
                    }
                    if("${server.body()?.data?.user?.role}" == "pemilik") {
                        Intent(this@LoginActivity, MainActivityP::class.java).also {
                            startActivity(it)
                        }
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Akun Tidak Ditemukan!", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<LoginResponses>, t: Throwable) {
                Log.e(INTERNAL_SERVER, t.message.toString())
            }
        })
    }
}
package d3ifcool.bisapetcah.mamierus.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.model.LoginResponses
import d3ifcool.bisapetcah.mamierus.databinding.ActivityLoginBinding
import d3ifcool.bisapetcah.mamierus.ui.konsumen.MainActivityK
import d3ifcool.bisapetcah.mamierus.ui.pemilik.MainActivityP
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


            val username = edtNamaPengguna.text
            val password = edtKataSandi.text

            btnLogin.setOnClickListener {
//                Log.d("account", "$username , $password")
                loginUser(username.toString(), password.toString())
            }
        }
    }

    private fun loginUser(username : String, password : String) {
        Log.d("account", "$username , $password")
        Client.instance.login(
            username,
            password
        ).enqueue(object : Callback<LoginResponses> {
            override fun onResponse(
                call: Call<LoginResponses>,
                server: Response<LoginResponses>
            ) {
                if(server.isSuccessful && "${server.body()?.data?.user?.role}" == "konsumen") {
                    Intent(this@LoginActivity, MainActivityK::class.java).also {
                        startActivity(it)
                    }
                }
                if(server.isSuccessful && "${server.body()?.data?.user?.role}" == "pemilik") {
                    Intent(this@LoginActivity, MainActivityP::class.java).also {
                        startActivity(it)
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponses>, t: Throwable) {
                Log.e("Fail Internal Error", t.message.toString())
            }
        })
    }
}
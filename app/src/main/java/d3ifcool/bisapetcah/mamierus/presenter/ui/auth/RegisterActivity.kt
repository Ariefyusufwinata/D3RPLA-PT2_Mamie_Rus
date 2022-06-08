package d3ifcool.bisapetcah.mamierus.presenter.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PRESENTER
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.model.konsumen.KonsumenRegisterResponses
import d3ifcool.bisapetcah.mamierus.databinding.ActivityAuthRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAuthRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                register(username, password, phone)
            }
        }
    }

    private fun register(username : String, password : String, phone : String) {
        Log.d("account", "$username, $password, $phone")
        Client.instance.register(
            username,
            password,
            phone
        ).enqueue(object  : Callback<KonsumenRegisterResponses> {
            override fun onResponse(
                call: Call<KonsumenRegisterResponses>,
                server: Response<KonsumenRegisterResponses>
            ) {
                if(server.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "Akun Anda Telah Dibuat!", Toast.LENGTH_LONG).show()
                    Intent(this@RegisterActivity, LoginActivity::class.java).also {
                        startActivity(it)
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "Periksa Kembali Data Anda!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<KonsumenRegisterResponses>, t: Throwable) {
                Log.e(FAILURE_PRESENTER, t.message.toString())
            }

        })
    }

}
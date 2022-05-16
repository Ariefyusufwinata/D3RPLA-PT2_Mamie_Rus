package d3ifcool.bisapetcah.mamierus.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.model.LoginResponses
import d3ifcool.bisapetcah.mamierus.core.model.RegisterKonsumenResponses
import d3ifcool.bisapetcah.mamierus.databinding.ActivityRegisterBinding
import d3ifcool.bisapetcah.mamierus.ui.konsumen.MainActivityK
import d3ifcool.bisapetcah.mamierus.ui.pemilik.MainActivityP
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
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
        ).enqueue(object  : Callback<RegisterKonsumenResponses> {
            override fun onResponse(
                call: Call<RegisterKonsumenResponses>,
                server: Response<RegisterKonsumenResponses>
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

            override fun onFailure(call: Call<RegisterKonsumenResponses>, t: Throwable) {
                Log.e("Fail Internal Error", t.message.toString())
            }

        })
    }

}
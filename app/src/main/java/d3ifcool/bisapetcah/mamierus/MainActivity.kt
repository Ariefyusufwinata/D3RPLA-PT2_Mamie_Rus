package d3ifcool.bisapetcah.mamierus

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import d3ifcool.bisapetcah.mamierus.adapter.MainAdapter
import d3ifcool.bisapetcah.mamierus.adapter.PublicGetMenuAdapter
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.model.PublicGetProductResponses
import d3ifcool.bisapetcah.mamierus.core.viewmodel.MainViewModel
import d3ifcool.bisapetcah.mamierus.databinding.ActivityMainBinding
import d3ifcool.bisapetcah.mamierus.ui.AboutAppActivity
import d3ifcool.bisapetcah.mamierus.ui.AddressActivity
import d3ifcool.bisapetcah.mamierus.ui.DetailMenuActivity
import d3ifcool.bisapetcah.mamierus.ui.LoginActivity
import okhttp3.internal.notify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
//    private lateinit var model : MainViewModel
//    private lateinit var connector : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = resources.getString(R.string.dashboard)
        val actionBar = supportActionBar
        actionBar?.title = title

//        connector = MainAdapter()
//        connector.notifyDataSetChanged()

        //From API
        apiItemFood()
//        model = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java] // .get()ViewModel
    }

    private fun apiItemFood() {
        binding.apply {
            rvItem.setHasFixedSize(true)
            rvItem.layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
        Client.instance.getPublicProduct().enqueue(object : Callback<PublicGetProductResponses> {
            override fun onResponse(
                call: Call<PublicGetProductResponses>,
                response: Response<PublicGetProductResponses>
            ) {
                if(response.isSuccessful){
                    val adapter = response.body()?.dataAllMenu?.dataItem?.let { PublicGetMenuAdapter(it) }
                    binding.rvItem.adapter = adapter
                } else {
                    Toast.makeText(this@MainActivity, "Gagal Menampilkan Data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PublicGetProductResponses>, t: Throwable) {
                Log.d("Error Internal", t.toString())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(Menu.NONE, 1, Menu.NONE, "Masuk")
        menu?.add(Menu.NONE, 2, Menu.NONE, "Alamat Warung")
        menu?.add(Menu.NONE, 3, Menu.NONE, "Tentang Aplikasi")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            1 -> Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
            2 -> Intent(this, AddressActivity::class.java).also {
                startActivity(it)
            }
            3 -> Intent(this, AboutAppActivity::class.java).also {
                startActivity(it)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
package d3ifcool.bisapetcah.mamierus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import d3ifcool.bisapetcah.mamierus.adapter.PublicGetMenuAdapter
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.model.PublicGetProductResponses
import d3ifcool.bisapetcah.mamierus.databinding.ActivityMainBinding
import d3ifcool.bisapetcah.mamierus.ui.AboutAppActivity
import d3ifcool.bisapetcah.mamierus.ui.AddressActivity
import d3ifcool.bisapetcah.mamierus.ui.DetailMenuActivity
import d3ifcool.bisapetcah.mamierus.ui.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val list = ArrayList<PublicGetProductResponses>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = resources.getString(R.string.dashboard)
        val actionBar = supportActionBar
        actionBar?.title = title

        //From Dummy Item Food
//        includedItemFood()

        //From API
        apiItemFood()
    }

    private fun apiItemFood() {
        binding.rvItem.setHasFixedSize(true)
        binding.rvItem.layoutManager = GridLayoutManager(this@MainActivity, 2)
//        Client.instance.getPublicProduct().enqueue(object : Callback<ArrayList<PublicGetProductResponses>>{
//            override fun onResponse(
//                call: Call<ArrayList<PublicGetProductResponses>>,
//                response: Response<ArrayList<PublicGetProductResponses>>
//            ) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onFailure(call: Call<ArrayList<PublicGetProductResponses>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
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

//    private fun includedItemFood() {
//        binding.rvItem.itemOne.setOnClickListener{
//            Intent(this, DetailMenuActivity::class.java).also {
//                startActivity(it)
//            }
//        }
//        binding.rvItem.itemTwo.setOnClickListener{
//            Intent(this, DetailMenuActivity::class.java).also {
//                startActivity(it)
//            }
//        }
//        binding.rvItem.itemThree.setOnClickListener{
//            Intent(this, DetailMenuActivity::class.java).also {
//                startActivity(it)
//            }
//        }
//        binding.rvItem.itemFour.setOnClickListener{
//            Intent(this, DetailMenuActivity::class.java).also {
//                startActivity(it)
//            }
//        }
//        binding.rvItem.itemFive.setOnClickListener{
//            Intent(this, DetailMenuActivity::class.java).also {
//                startActivity(it)
//            }
//        }
//        binding.rvItem.itemSix.setOnClickListener{
//            Intent(this, DetailMenuActivity::class.java).also {
//                startActivity(it)
//            }
//        }
//        binding.rvItem.itemSeven.setOnClickListener{
//            Intent(this, DetailMenuActivity::class.java).also {
//                startActivity(it)
//            }
//        }
//        binding.rvItem.itemEight.setOnClickListener{
//            Intent(this, DetailMenuActivity::class.java).also {
//                startActivity(it)
//            }
//        }
//    }
}
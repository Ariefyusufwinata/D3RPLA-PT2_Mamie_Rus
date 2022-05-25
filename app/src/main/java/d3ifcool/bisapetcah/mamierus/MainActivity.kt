package d3ifcool.bisapetcah.mamierus

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import d3ifcool.bisapetcah.mamierus.core.helper.TemporaryObject
import d3ifcool.bisapetcah.mamierus.presenter.adapter.publik.MainAdapter
import d3ifcool.bisapetcah.mamierus.core.model.publik.DataItem
import d3ifcool.bisapetcah.mamierus.databinding.ActivityMainBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.publik.AboutAppActivity
import d3ifcool.bisapetcah.mamierus.presenter.ui.publik.AddressActivity
import d3ifcool.bisapetcah.mamierus.presenter.ui.auth.LoginActivity
import d3ifcool.bisapetcah.mamierus.presenter.ui.publik.DetailMenuActivity
import d3ifcool.bisapetcah.mamierus.presenter.viewmodel.publik.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var model : MainViewModel
    private lateinit var connector : MainAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = resources.getString(R.string.dashboard)
        val actionBar = supportActionBar
        actionBar?.title = title

        connector = MainAdapter()
        connector.notifyDataSetChanged()

        connector.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DataItem) {
                Intent(this@MainActivity, DetailMenuActivity::class.java).also {
                    it.putExtra(TemporaryObject.EXTRA_MSG, data.id)
                    startActivity(it)
                }
            }
        })

        model = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
        model.getAllMenu()
        loadingTime(true)
        model.getValue().observe(this) {
            when(it != null) {
                true -> {
                    connector.setList(it)
                    loadingTime(false)
                }
                false -> {
                    Toast.makeText(this, "Tidak Dapat Menampilkan!", Toast.LENGTH_LONG).show()
                    loadingTime(false)
                }
            }
        }

        binding.apply {
            rvItem.setHasFixedSize(true)
            rvItem.adapter = connector
            rvItem.layoutManager = GridLayoutManager(this@MainActivity, 2)

            edtCari.setOnKeyListener { _, i, keyEvent ->
                return@setOnKeyListener keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER
            }

            edtCari.setOnEditorActionListener { _, i, _ ->
                if(i == EditorInfo.IME_ACTION_SEARCH) {
                    searchMenu()
                }
                true
            }
        }
    }

    private fun searchMenu() {
        binding.apply {
            val search = edtCari.text.toString()
            when {
                search.isEmpty() -> {
                    model.getAllMenu()
                    loadingTime(true)
                }
                search.isNotEmpty() -> {
                    model.getSearchMenu(search)
                    loadingTime(true)
                }                }
        }
    }

    private fun loadingTime(isTrue : Boolean) {
        binding.apply {
            when(isTrue) {
                true -> {
                    progressBar.visibility = View.VISIBLE
                }
                false -> {
                    progressBar.visibility = View.GONE
                }
            }
        }
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
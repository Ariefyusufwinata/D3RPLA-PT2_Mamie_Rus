package d3ifcool.bisapetcah.mamierus.presenter.ui.general

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
import d3ifcool.bisapetcah.mamierus.R
import d3ifcool.bisapetcah.mamierus.core.helper.Constant
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PRESENTER
import d3ifcool.bisapetcah.mamierus.presenter.adapter.general.MainAdapter
import d3ifcool.bisapetcah.mamierus.core.model.general.DataItem
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPublicMainBinding
import d3ifcool.bisapetcah.mamierus.presenter.fragment.BottomSheetFragmentPublic
import d3ifcool.bisapetcah.mamierus.presenter.ui.auth.LoginActivity
import d3ifcool.bisapetcah.mamierus.presenter.viewmodel.general.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPublicMainBinding
    private lateinit var model : MainViewModel
    private lateinit var connector : MainAdapter
    private lateinit var bottomSheetFragment : BottomSheetFragmentPublic

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPublicMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = resources.getString(R.string.dashboard)
        val actionBar = supportActionBar
        actionBar?.title = title

        connector = MainAdapter()
        connector.notifyDataSetChanged()
        connector.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DataItem?) {
                Intent(this@MainActivity, DetailMenuActivity::class.java).also {
                    it.putExtra(Constant.EXTRA_ID, data?.id)
                    startActivity(it)
                }
            }
        })

        model = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

//        //Sorting Companion Object For Chossing In Filtering Menu
        val msg = intent.getStringExtra(Constant.EXTRA_MSG)
        when (msg) {
            "Paling Sesuai" -> {
                model.getSortMost()
            }
            "Paling Murah" -> {
                model.getSortPriceASC()
            }
            "Paling Mahal" -> {
                model.getSortPriceDESC()
            }
            else -> {
                model.getAllMenu()
            }
        }
        loadingTime(true)
        model.getValue().observe(this) {
            when(it != null) {
                true -> {
                    connector.setList(it)
                    loadingTime(false)
                }
                false -> {
                    Toast.makeText(this, FAILURE_PRESENTER, Toast.LENGTH_LONG).show()
                    loadingTime(false)
                }
            }
        }

        binding.apply {
            rvItem.setHasFixedSize(true)
            rvItem.adapter = connector
            rvItem.layoutManager = GridLayoutManager(this@MainActivity, 2)

            edtCari.apply {
                setOnKeyListener { _, i, keyEvent ->
                    return@setOnKeyListener keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER
                }

                setOnEditorActionListener { _, i, _ ->
                    if(i == EditorInfo.IME_ACTION_SEARCH) {
                        searchMenu()
                    }
                    true
                }
            }

            btnFilter.setOnClickListener {
                bottomSheetFragment = BottomSheetFragmentPublic()
                bottomSheetFragment.show(supportFragmentManager, "Show Fragment")
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
            1 -> startActivity(Intent(this, LoginActivity::class.java))
            2 -> startActivity(Intent(this, AddressActivity::class.java))
            3 -> startActivity(Intent(this, AboutAppActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
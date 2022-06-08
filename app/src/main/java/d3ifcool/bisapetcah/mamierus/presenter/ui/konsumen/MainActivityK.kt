package d3ifcool.bisapetcah.mamierus.presenter.ui.konsumen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import d3ifcool.bisapetcah.mamierus.R
import d3ifcool.bisapetcah.mamierus.core.datastore.PreferencesData
import d3ifcool.bisapetcah.mamierus.core.helper.BOTTOM_SHEET
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PRESENTER
import d3ifcool.bisapetcah.mamierus.core.helper.TemporaryObject
import d3ifcool.bisapetcah.mamierus.core.model.publik.DataItem
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenMainBinding
import d3ifcool.bisapetcah.mamierus.presenter.adapter.konsumen.MainKAdapter
import d3ifcool.bisapetcah.mamierus.presenter.fragment.BottomSheetFragmentPublic
import d3ifcool.bisapetcah.mamierus.presenter.viewmodel.publik.MainViewModel

class MainActivityK : AppCompatActivity() {

    private lateinit var binding : ActivityKonsumenMainBinding
    private lateinit var model : MainViewModel
    private lateinit var connector : MainKAdapter
    private lateinit var bottomSheetFragment : BottomSheetFragmentPublic
    private lateinit var sharedPref : PreferencesData

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsumenMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = resources.getString(R.string.dashboard)
        val actionBar = supportActionBar
        actionBar?.title = title

        sharedPref = PreferencesData(this@MainActivityK)
        Log.d("bearer-token", sharedPref.getString(TemporaryObject.PREFS_TOKEN).toString())

        connector = MainKAdapter()
        connector.notifyDataSetChanged()
        connector.setOnItemClickCallback(object : MainKAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DataItem?) {
                Intent(this@MainActivityK, DetailMenuActivityK::class.java).also {
                    it.putExtra(TemporaryObject.EXTRA_MSG, data?.id)
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
                    Toast.makeText(this, FAILURE_PRESENTER, Toast.LENGTH_LONG).show()
                    loadingTime(false)
                }
            }
        }

        binding.apply {
            rvItem.setHasFixedSize(true)
            rvItem.adapter = connector
            rvItem.layoutManager = GridLayoutManager(this@MainActivityK, 2)

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
                bottomSheetFragment.show(supportFragmentManager, BOTTOM_SHEET)
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
        menu?.add(Menu.NONE, 1, Menu.NONE, "Alamat Warung")
        menu?.add(Menu.NONE, 2, Menu.NONE, "Pengaturan")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            1 -> Intent(this, AddressActivityK::class.java).also {
                startActivity(it)
            }
            2 -> Intent(this, SettingsActivityK::class.java).also {
                startActivity(it)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
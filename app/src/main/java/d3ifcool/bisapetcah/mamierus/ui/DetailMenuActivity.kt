package d3ifcool.bisapetcah.mamierus.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.R
import d3ifcool.bisapetcah.mamierus.databinding.ActivityDetailMenuBinding

class DetailMenuActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tittle = resources.getString(R.string.detail_menu)
        val actionBar = supportActionBar
        actionBar?.title = tittle
        actionBar?.setDisplayHomeAsUpEnabled(true)


        binding = ActivityDetailMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
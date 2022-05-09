package d3ifcool.bisapetcah.mamierus.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityMainBinding
import d3ifcool.bisapetcah.mamierus.view.ui.DetailMenuActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvItem.itemOne.setOnClickListener{
            Intent(this, DetailMenuActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}
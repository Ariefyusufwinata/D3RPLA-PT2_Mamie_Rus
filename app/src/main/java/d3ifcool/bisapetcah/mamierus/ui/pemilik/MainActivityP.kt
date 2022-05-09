package d3ifcool.bisapetcah.mamierus.view.ui.pemilik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPemilikMainBinding

class MainActivityP : AppCompatActivity() {
    private lateinit var binding: ActivityPemilikMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPemilikMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
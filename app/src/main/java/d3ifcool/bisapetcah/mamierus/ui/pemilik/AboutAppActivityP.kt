package d3ifcool.bisapetcah.mamierus.ui.pemilik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPemilikAboutAppBinding

class AboutAppActivityP : AppCompatActivity() {

    private lateinit var binding : ActivityPemilikAboutAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemilikAboutAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
package d3ifcool.bisapetcah.mamierus.presenter.ui.pemilik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPemilikDetailMenuBinding

class DetailMenuActivityP : AppCompatActivity() {

    private lateinit var binding : ActivityPemilikDetailMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemilikDetailMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
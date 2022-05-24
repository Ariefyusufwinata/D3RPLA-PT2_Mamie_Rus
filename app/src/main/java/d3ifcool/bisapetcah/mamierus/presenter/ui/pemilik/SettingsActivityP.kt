package d3ifcool.bisapetcah.mamierus.presenter.ui.pemilik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPemilikSettingsBinding

class SettingsActivityP : AppCompatActivity() {

    private lateinit var binding : ActivityPemilikSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemilikSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
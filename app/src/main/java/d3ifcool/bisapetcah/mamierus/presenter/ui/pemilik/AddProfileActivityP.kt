package d3ifcool.bisapetcah.mamierus.presenter.ui.pemilik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPemilikAddProfileBinding

class AddProfileActivityP : AppCompatActivity() {

    private lateinit var binding : ActivityPemilikAddProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemilikAddProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
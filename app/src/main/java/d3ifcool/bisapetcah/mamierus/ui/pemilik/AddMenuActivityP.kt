package d3ifcool.bisapetcah.mamierus.ui.pemilik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPemilikAddMenuBinding

class AddMenuActivityP : AppCompatActivity() {

    private lateinit var binding : ActivityPemilikAddMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemilikAddMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
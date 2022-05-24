package d3ifcool.bisapetcah.mamierus.presenter.ui.pemilik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPemilikGetCommentBinding

class GetCommentActivityP : AppCompatActivity() {

    private lateinit var binding : ActivityPemilikGetCommentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemilikGetCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
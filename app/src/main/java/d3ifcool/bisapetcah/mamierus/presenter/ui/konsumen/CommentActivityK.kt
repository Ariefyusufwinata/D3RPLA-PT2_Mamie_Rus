package d3ifcool.bisapetcah.mamierus.presenter.ui.konsumen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenCommentBinding

class CommentActivityK : AppCompatActivity() {

    private lateinit var binding : ActivityKonsumenCommentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsumenCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
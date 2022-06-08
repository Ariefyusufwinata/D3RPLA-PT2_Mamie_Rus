package d3ifcool.bisapetcah.mamierus.presenter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import d3ifcool.bisapetcah.mamierus.databinding.FragmentPublicBottomSheetBinding

class BottomSheetFragmentKonsumen : Fragment() {

    private var _binding : FragmentPublicBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPublicBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.apply {
//            btnHapusFilter.setOnClickListener {
//                radioGroup.clearCheck()
//            }
//
//            btnPasang.setOnClickListener {
//                when (radioGroup.checkedRadioButtonId) {
//                    R.id.cPalingSesuai -> {
//                        Intent(requireActivity(), MainActivity::class.java).also {
//                            it.putExtra(TemporaryObject.EXTRA_MSG, "Paling Sesuai")
//                            startActivity(it)
//                        }
//                    }
//                    R.id.cPalingMurah -> {
//                        Intent(requireActivity(), MainActivity::class.java).also {
//                            it.putExtra(TemporaryObject.EXTRA_MSG, "Paling Murah")
//                            startActivity(it)
//                        }
//                    }
//                    R.id.cPalingMahal -> {
//                        Intent(requireActivity(), MainActivity::class.java).also {
//                            it.putExtra(TemporaryObject.EXTRA_MSG, "Paling Mahal")
//                            startActivity(it)
//                        }
//                    }
//                    -1 -> {
//                        Intent(requireActivity(), MainActivity::class.java).also {
//                            startActivity(it)
//                        }
//                        Toast.makeText(requireActivity(), "No Value", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
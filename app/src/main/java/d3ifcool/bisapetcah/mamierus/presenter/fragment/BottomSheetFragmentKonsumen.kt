package d3ifcool.bisapetcah.mamierus.presenter.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import d3ifcool.bisapetcah.mamierus.R
import d3ifcool.bisapetcah.mamierus.core.helper.Constant
import d3ifcool.bisapetcah.mamierus.databinding.FragmentPublicBottomSheetBinding
import d3ifcool.bisapetcah.mamierus.presenter.ui.costumer.MainActivityK

class BottomSheetFragmentKonsumen : BottomSheetDialogFragment() {

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

        binding.apply {
            btnHapusFilter.setOnClickListener {
                radioGroup.clearCheck()
            }

            btnPasang.setOnClickListener {
                when (radioGroup.checkedRadioButtonId) {
                    R.id.cPalingSesuai -> {
                        Intent(requireActivity(), MainActivityK::class.java).also {
                            it.putExtra(Constant.EXTRA_MSG, "Paling Sesuai")
                            startActivity(it)
                        }
                    }
                    R.id.cPalingMurah -> {
                        Intent(requireActivity(), MainActivityK::class.java).also {
                            it.putExtra(Constant.EXTRA_MSG, "Paling Murah")
                            startActivity(it)
                        }
                    }
                    R.id.cPalingMahal -> {
                        Intent(requireActivity(), MainActivityK::class.java).also {
                            it.putExtra(Constant.EXTRA_MSG, "Paling Mahal")
                            startActivity(it)
                        }
                    }
                    -1 -> {
                        Intent(requireActivity(), MainActivityK::class.java).also {
                            startActivity(it)
                        }
//                        Toast.makeText(requireActivity(), "No Value", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
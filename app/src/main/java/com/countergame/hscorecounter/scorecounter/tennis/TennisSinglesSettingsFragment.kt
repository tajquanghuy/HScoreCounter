package com.countergame.hscorecounter.scorecounter.tennis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.countergame.hscorecounter.R
import com.countergame.hscorecounter.databinding.FragmentTennisSinglesSettingsBinding
import com.countergame.hscorecounter.scorecounter.MainMenuFragment

class TennisSinglesSettingsFragment : Fragment() {

    private val TAG: String = "TENNIS SINGLES SETTINGS"

    private var _binding: FragmentTennisSinglesSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTennisSinglesSettingsBinding.inflate(inflater, container, false)

        binding.btnTsSettingsStart.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("p1name", binding.tiTsPlayer1.text.toString())
            bundle.putString("p2name", binding.tiTsPlayer2.text.toString())
            var rB = activity?.findViewById<RadioButton>(binding.rgTsSetsToWin.checkedRadioButtonId)
            bundle.putString("numberOfSets", (rB?.text as String?))
            var rB2 =
                activity?.findViewById<RadioButton>(binding.rgTsFirstserve.checkedRadioButtonId)
            bundle.putString("firstServe", (rB2?.text as String?))
            bundle.putBoolean("matchTieBreak", binding.swMatchTieBreak.isChecked)

            val tsgf = TennisSinglesGameFragment()
            tsgf.arguments = bundle

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.linearLayout, tsgf)
                .addToBackStack(null)
                .commit()
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.linearLayout, MainMenuFragment())
            .addToBackStack(null)
            .commit()
    }
}
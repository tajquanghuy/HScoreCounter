package com.countergame.hscorecounter.scorecounter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.countergame.hscorecounter.R
import com.countergame.hscorecounter.databinding.FragmentMainMenuBinding
import com.countergame.hscorecounter.scorecounter.tennis.GameHistoryFragment
import com.countergame.hscorecounter.scorecounter.tennis.TennisDoublesSettingsFragment
import com.countergame.hscorecounter.scorecounter.tennis.TennisSinglesSettingsFragment

class MainMenuFragment : Fragment() {
    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnMmTennisSingles.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.linearLayout, TennisSinglesSettingsFragment())
                .addToBackStack("TennisSinglesSettingsFragment")
                .commit()
        }

        binding.btnMmTennisDoubles.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.linearLayout, TennisDoublesSettingsFragment())
                .addToBackStack("TennisDoublesSettingsFragment")
                .commit()
        }

        binding.btnMmTennisHistory.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.linearLayout, GameHistoryFragment())
                .addToBackStack("GameHistoryFragment")
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
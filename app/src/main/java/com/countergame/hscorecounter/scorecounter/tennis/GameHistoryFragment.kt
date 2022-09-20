package com.countergame.hscorecounter.scorecounter.tennis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.countergame.hscorecounter.R
import com.countergame.hscorecounter.databinding.FragmentGameHistoryBinding
import com.countergame.hscorecounter.scorecounter.MainActivity
import com.countergame.hscorecounter.scorecounter.MainMenuFragment
import com.countergame.hscorecounter.scorecounter.database.Match
import com.countergame.hscorecounter.scorecounter.helpers.HistoryAdapter
import com.countergame.hscorecounter.scorecounter.helpers.HistoryItemViewModel
import kotlinx.coroutines.runBlocking

class GameHistoryFragment : Fragment() {

    private var _binding: FragmentGameHistoryBinding? = null
    private val binding get() = _binding!!

    var scoreDao = MainActivity.scoreDao
    private var allMatches: List<Match>? = null
    lateinit var data: ArrayList<HistoryItemViewModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGameHistoryBinding.inflate(inflater, container, false)

        binding.btnBack2Menu.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.linearLayout, MainMenuFragment())
                .addToBackStack(null)
                .commit()
        }

//        GlobalScope.launch {
//            allMatches = scoreDao.getAllMatches()
//        }

        runBlocking {
            allMatches = scoreDao.getAllMatches()
        }

        val recyclerview = binding.rvGameHistory
        recyclerview.layoutManager = LinearLayoutManager(context)
        data = ArrayList()
        for (match in allMatches ?: emptyList()) {
            if (match.type == "Tennis-Doubles") {
                data.add(
                    HistoryItemViewModel(
                        match.id,
                        "${match.p1name}/${match.p12name}",
                        "${match.p2name}/${match.p22name}",
                        match.p1Sets,
                        match.p2Sets,
                        match.date
                    )
                )
            } else {
                data.add(
                    HistoryItemViewModel(
                        match.id,
                        match.p1name,
                        match.p2name,
                        match.p1Sets,
                        match.p2Sets,
                        match.date
                    )
                )
            }
        }
        val adapter =
            HistoryAdapter(requireContext(), data) { position -> onListItemClick(position) }
        recyclerview.adapter = adapter

        return binding.root
    }

    private fun onListItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putLong("match_id", data[position].id)

        val ghdf = GameHistoryDetailsFragment()
        ghdf.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.linearLayout, ghdf)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
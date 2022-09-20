package com.countergame.hscorecounter.scorecounter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.countergame.hscorecounter.databinding.FragmentAppInfoBinding


class AppInfoFragment : Fragment() {

    private var _binding: FragmentAppInfoBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAppInfoBinding.inflate(inflater, container, false)

        binding.aboutBtnEmail.setOnClickListener() {
        }

        binding.aboutBtnGithub.setOnClickListener() {
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
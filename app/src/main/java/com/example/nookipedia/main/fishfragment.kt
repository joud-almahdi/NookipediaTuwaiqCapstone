package com.example.nookipedia.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nookipedia.R
import com.example.nookipedia.databinding.FragmentFishfragmentBinding


class fishfragment : Fragment() {



    private lateinit var binding: FragmentFishfragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fishfragment, container, false)
    }


}
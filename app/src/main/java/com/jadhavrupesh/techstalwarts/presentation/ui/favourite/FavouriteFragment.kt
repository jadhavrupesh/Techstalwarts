package com.jadhavrupesh.techstalwarts.presentation.ui.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.jadhavrupesh.techstalwarts.R
import com.jadhavrupesh.techstalwarts.databinding.FragmentFavouriteBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private lateinit var viewModel: FavouriteViewModel
    private lateinit var binding: FragmentFavouriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate called in FavouriteFragment")
        viewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        println("onCreateView called in FavouriteFragment")
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("onViewCreated called in FavouriteFragment")

    }

    companion object {

    }
}
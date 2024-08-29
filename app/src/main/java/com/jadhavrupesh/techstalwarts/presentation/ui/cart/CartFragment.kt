package com.jadhavrupesh.techstalwarts.presentation.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.jadhavrupesh.techstalwarts.R
import com.jadhavrupesh.techstalwarts.databinding.FragmentCartBinding
import com.jadhavrupesh.techstalwarts.databinding.FragmentFavouriteBinding
import com.jadhavrupesh.techstalwarts.presentation.ui.favourite.FavouriteViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var viewModel: CartViewModel
    private lateinit var binding: FragmentCartBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate called in CartFragment")
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        println("onCreateView called in CartFragment")
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("onViewCreated called in CartFragment")

    }

    companion object {
    }
}
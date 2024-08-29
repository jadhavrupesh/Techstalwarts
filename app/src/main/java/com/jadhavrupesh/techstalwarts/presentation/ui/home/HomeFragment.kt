package com.jadhavrupesh.techstalwarts.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jadhavrupesh.techstalwarts.R
import com.jadhavrupesh.techstalwarts.adapter.FoodAdapter
import com.jadhavrupesh.techstalwarts.databinding.FragmentFavouriteBinding
import com.jadhavrupesh.techstalwarts.databinding.FragmentHomeBinding
import com.jadhavrupesh.techstalwarts.model.FoodDetailsEntity
import com.jadhavrupesh.techstalwarts.presentation.ui.favourite.FavouriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var adapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate called in HomeFragment")
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        println("onCreateView called in HomeFragment")
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("onViewCreated called in HomeFragment")
        navController = findNavController()
        setupRecyclerView()
        viewModel.foodListLiveData.observe(viewLifecycleOwner) { result ->
            println("onViewCreated called in HomeFragment foodListLiveData = ${result}")
            adapter.items = result.toMutableList()
            adapter.notifyItemChanged(0)
        }


        adapter.notifyItemChanged(0)
    }

    private fun setupRecyclerView() {
        binding.rvListView.adapter = adapter
    }

    companion object {

    }
}
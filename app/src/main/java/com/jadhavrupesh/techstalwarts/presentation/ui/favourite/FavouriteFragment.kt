package com.jadhavrupesh.techstalwarts.presentation.ui.favourite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jadhavrupesh.techstalwarts.R
import com.jadhavrupesh.techstalwarts.adapter.FoodAdapter
import com.jadhavrupesh.techstalwarts.databinding.FragmentFavouriteBinding
import com.jadhavrupesh.techstalwarts.presentation.ui.foodDetails.FoodDetailsActivity
import com.jadhavrupesh.techstalwarts.presentation.ui.home.HomeFragment.Companion.FOOD_DETAILS_ID
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private lateinit var viewModel: FavouriteViewModel
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var adapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("FavouriteFragment onCreate called in FavouriteFragment")
        viewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        println("FavouriteFragment onCreateView called in FavouriteFragment")
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        viewModel.getFevItem()
        setupRecyclerView()
        viewModel.foodListLiveData.observe(viewLifecycleOwner) { result ->
            adapter.items = result.toMutableList()
            adapter.notifyItemChanged(0)
        }
        adapter.notifyItemChanged(0)

        adapter.onPostClick = { item ->
//            val intent = Intent(
//                requireContext(), FoodDetailsActivity::class.java
//            )
//            intent.putExtra(FOOD_DETAILS_ID, item.id)
//            startActivity(intent)
        }

    }

    private fun setupRecyclerView() {
        binding.rvListView.adapter = adapter
    }

    companion object {

    }
}
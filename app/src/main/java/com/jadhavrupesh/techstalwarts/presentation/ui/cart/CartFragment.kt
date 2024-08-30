package com.jadhavrupesh.techstalwarts.presentation.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.transition.Visibility
import com.jadhavrupesh.techstalwarts.adapter.CartAdapter
import com.jadhavrupesh.techstalwarts.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var viewModel: CartViewModel
    private lateinit var binding: FragmentCartBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        viewModel.getCartItem()
        setupRecyclerView()
        viewModel.cartListLiveData.observe(viewLifecycleOwner) { result ->
            if (result.isEmpty()) {
                binding.completeOrder.setVisibility(View.GONE)
            } else {
                binding.completeOrder.visibility = View.VISIBLE
            }
            adapter.items = result.toMutableList()
            adapter.notifyItemChanged(0)
        }
        adapter.onUpdate = { item ->
            viewModel.updateCount(item)
        }


        binding.completeOrder.setOnClickListener {
            viewModel.clearCart()
        }

    }

    private fun setupRecyclerView() {
        binding.rvListView.adapter = adapter
    }
}
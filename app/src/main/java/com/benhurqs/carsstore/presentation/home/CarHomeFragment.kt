package com.benhurqs.carsstore.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.benhurqs.carsstore.R
import com.benhurqs.carsstore.databinding.FragmentCarHomeBinding
import com.benhurqs.carsstore.presentation.home.adapter.CarHomeAdapter
import com.benhurqs.carsstore.util.extensions.disabled
import com.benhurqs.carsstore.util.extensions.enabled
import com.benhurqs.carsstore.util.extensions.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarHomeFragment : Fragment(R.layout.fragment_car_home) {

    private var _binding: FragmentCarHomeBinding? = null
    private val binding get() = _binding

    private lateinit var adapter: CarHomeAdapter
    private val viewModel by viewModel<CarHomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        updateUi()
        toLeadFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUi() {
        lifecycleScope.launchWhenStarted {
            viewModel.carListState.collect { state ->
                when (state) {

                    is CarListState.Error -> {
                        binding?.progressBar?.disabled()
                        requireContext().toast(state.errorMessage)
                    }

                    is CarListState.Success -> {
                        binding?.progressBar?.disabled()
                        binding?.homeRecyclerview?.enabled()
                        adapter.setDataList(state.data)
                    }

                    CarListState.Loading -> {
                        binding?.homeRecyclerview?.disabled()
                        binding?.progressBar?.enabled()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = CarHomeAdapter()
        binding?.homeRecyclerview?.adapter = this.adapter
    }

    private fun toLeadFragment() {
        adapter.onItemClicked = { car ->
            val action = CarHomeFragmentDirections.actionHomeFragmentToLeadFragment(car)
            findNavController().navigate(action)
        }
    }
}
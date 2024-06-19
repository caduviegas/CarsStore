package com.benhurqs.carsstore.presentation.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.benhurqs.carsstore.R
import com.benhurqs.carsstore.databinding.FragmentCarSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarSplashFragment: Fragment(R.layout.fragment_car_splash) {

        private lateinit var _binding: FragmentCarSplashBinding
        private val viewModel by viewModel<CarSplashViewModel>()

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {

            leadRoutine()

            _binding = FragmentCarSplashBinding.inflate(inflater)
            return _binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            toHomeFragment()
        }
        private fun toHomeFragment() {
            lifecycleScope.launch {
                delay(2000)
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }

        private fun leadRoutine() {
            lifecycleScope.launchWhenStarted {
                Log.i("SplashFragment", "leadRoutine: leadRoutine")
                viewModel
            }
        }
}
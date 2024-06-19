package com.benhurqs.carsstore.presentation.lead

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.benhurqs.carsstore.R
import com.benhurqs.carsstore.databinding.FragmentLeadBinding
import com.benhurqs.carsstore.util.extensions.invisible
import com.benhurqs.carsstore.util.extensions.show
import com.benhurqs.carsstore.util.extensions.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeadFragment : Fragment(R.layout.fragment_lead) {

    private var _binding: FragmentLeadBinding? = null
    private val binding get() = _binding!!

    private val args: LeadFragmentArgs by navArgs()
    private val viewModel by viewModel<LeadViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularDadoCarros()
        toolbar()
        submitButton()
        updateEmail()
        updateName()
        updateLead()
    }

    private fun updateEmail() {
        lifecycleScope.launchWhenStarted {
            viewModel.emailState.collect { state ->
                when (state) {

                    is LeadState.Error -> {
                        binding.emailErrorText.text = state.errorMessage
                        binding.emailErrorText.show()
                    }

                    is LeadState.Success -> {
                        binding.emailErrorText.invisible()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun updateName() {
        lifecycleScope.launchWhenStarted {
            viewModel.nameState.collect { state ->
                when (state) {

                    is LeadState.Error -> {
                        binding.nameErrorText.text = state.errorMessage
                        binding.nameErrorText.show()
                    }

                    is LeadState.Success -> {
                        binding.nameErrorText.invisible()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun updateLead() {
        lifecycleScope.launchWhenStarted {
            viewModel.leadState.collect { state ->
                when (state) {

                    is LeadState.Success -> {
                        requireContext().toast(state.message ?: "")
                        toHomeFragment()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun submitButton() {
        binding.acceptButton.setOnClickListener {
            val carId = args.car.id
            val nomeLead = binding.nameEditText.text.toString().trim()
            val emailLead = binding.emailEditText.text.toString().trim()
            viewModel.saveLead(carId, nomeLead, emailLead)
        }
    }


    private fun popularDadoCarros() {
        with(binding) {
            carModel.text = args.car.nomeModelo
            carBrand.text = args.car.marcaNome
            carValue.text = args.car.valorFipe
        }
    }

    private fun toolbar() {
        val navController = findNavController()
        val appBarConfig = AppBarConfiguration(navController.graph)
        val toolbar = binding.toolbar
        toolbar.setupWithNavController(navController, appBarConfig)
    }

    private fun toHomeFragment() {
        val action = LeadFragmentDirections.actionLeadFragmentToHomeFragment()
        findNavController().navigate(action)
    }
}
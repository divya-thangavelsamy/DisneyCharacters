package com.codewithdivya.disneycharacters.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.codewithdivya.disneycharacters.databinding.FragmentDisneyCharactersListBinding
import com.codewithdivya.disneycharacters.ui.adapters.DisneyCharacterListAdapter
import com.codewithdivya.disneycharacters.ui.viewModels.DisneyCharactersListViewModel
import com.codewithdivya.disneycharacters.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DisneyCharactersListFragment : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentDisneyCharactersListBinding
    private val viewModel: DisneyCharactersListViewModel by viewModels()

    private val disneyListAdapter = DisneyCharacterListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDisneyCharactersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.getDisneyCharactersList()
        }
        collectEmittedDisneyCharacterList()
    }

    private fun collectEmittedDisneyCharacterList() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { resultState ->
                    renderUI(resultState)
                }
            }
        }
    }

    private fun renderUI(resultState: UIState) {
        with(binding) {
            when (resultState) {
                is UIState.Success -> {
                    with(disneyListAdapter) {
                        disneyListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                        disneyListRecyclerView.adapter = this
                        dataSet = resultState.news
                    }
                }
                is UIState.Loading -> disneyProgressBar.visibility = View.VISIBLE
                is UIState.Error -> Toast.makeText(context, "Something went Wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

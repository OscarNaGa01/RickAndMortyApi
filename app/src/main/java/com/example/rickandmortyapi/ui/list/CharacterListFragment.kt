package com.example.rickandmortyapi.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapi.data.domain.usecase.GetCharacterListUseCase
import com.example.rickandmortyapi.databinding.FragmentCharacterListBinding
import com.example.rickandmortyapi.injection.InjectionSingleton
import com.example.rickandmortyapi.ui.list.adapter.CharacterListAdapter
import kotlinx.coroutines.launch

class CharacterListFragment : Fragment(), CharacterListAdapter.OnItemCharacterClickListener {

    private lateinit var binding: FragmentCharacterListBinding
    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var linearLayout: LinearLayoutManager
    private val characterListViewModel: CharacterListViewModel =
        CharacterListViewModel(GetCharacterListUseCase(InjectionSingleton.provideDataSource()))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        initRecyclerView()
        observeViewModel()
        characterListViewModel.getCharacterList()
        return binding.root
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            characterListViewModel.characterListStateFlow.collect { names ->
                characterListAdapter.refreshData(names)
            }
        }
        lifecycleScope.launch {
            characterListViewModel.characterListSharedFlow.collect { error ->
                Log.d("TAG", "l> Hemos tenido un error: ${error.message}")
                Toast.makeText(requireActivity(), error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRecyclerView() {
        characterListAdapter = CharacterListAdapter(arrayListOf(), this)
        linearLayout = LinearLayoutManager(context)

        binding.recyclerView.apply {
            adapter = characterListAdapter
            layoutManager = linearLayout
        }
    }

    override fun onCharacterClick(character: String) {
        findNavController().navigate(CharacterListFragmentDirections.actionListToDetail(character))
    }

    override fun onButtonPageClick(page: String) {

    }
}
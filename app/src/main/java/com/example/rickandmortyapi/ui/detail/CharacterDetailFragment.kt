package com.example.rickandmortyapi.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.rickandmortyapi.data.domain.model.character.CharacterModel
import com.example.rickandmortyapi.data.domain.usecase.GetCharacterDetailUseCase
import com.example.rickandmortyapi.databinding.FragmentCharacterDetailBinding
import com.example.rickandmortyapi.injection.InjectionSingleton
import kotlinx.coroutines.launch

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private val characterDetailViewModel =
        CharacterDetailViewModel(GetCharacterDetailUseCase(InjectionSingleton.provideDataSource()))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        characterDetailViewModel.getCharacterDetail(arguments?.getString("Character"))
        initListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            characterDetailViewModel.characterDetailStateFlow.collect { character ->
                showCharacter(character)
            }
        }
        lifecycleScope.launch {
            characterDetailViewModel.characterErrorSharedFlow.collect { error ->
                Log.d("TAG", "l> Hemos tenido un error: ${error.message}")
                Toast.makeText(requireActivity(), error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showCharacter(character: CharacterModel) {
        with(binding) {
            tvName.text = character.name
            Glide.with(this@CharacterDetailFragment)
                .load(character.image)
                .into(ivCharacter)
            tvGender.text = character.gender
            tvSpecies.text = character.species
            tvType.text = character.type
            tvStatus.text = character.status
        }
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
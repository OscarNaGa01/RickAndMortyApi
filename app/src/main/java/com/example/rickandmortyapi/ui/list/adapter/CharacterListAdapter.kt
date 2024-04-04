package com.example.rickandmortyapi.ui.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.data.domain.model.character.CharacterModel
import com.example.rickandmortyapi.databinding.ItemCharacterBinding

class CharacterListAdapter(
    private val characterList: ArrayList<CharacterModel>,
    private val listener: OnItemCharacterClickListener
) :
    RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    interface OnItemCharacterClickListener {
        fun onCharacterClick(character: String)
        fun onButtonPageClick(page: String)
    }

    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCharacterBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterList[position]
        with(holder.binding) {
            tvCharacter.text = character.name
            cvCharacter.setOnClickListener {
                listener.onCharacterClick(character.url)
            }
        }
    }

    fun refreshData(names: ArrayList<CharacterModel>) {
        characterList.clear()
        characterList.addAll(names)
        notifyItemInserted(names.size - 1)
    }
}
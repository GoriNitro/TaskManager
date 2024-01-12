package com.example.taskmanager.ui.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.model.Film

class FilmAdapter : Adapter<FilmAdapter.FilmViewHolder>() {
    private val data = arrayListOf<Film>()

    fun addFilm(newData: List<Film>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent , false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(data[position])
    }
    inner class FilmViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(film: Film) {
            binding.tvTitle.text = film.name
            binding.tvDesc.text = film.director
        }
    }


}
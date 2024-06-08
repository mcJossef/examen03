package com.munoz.jossef.examen03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.munoz.jossef.examen03.databinding.ItemTeacherBinding

class TeacherAdapter(private val teachers: List<Teacher>) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: Teacher) {
            binding.nombre.text = teacher.name
            binding.apellido.text = teacher.last_name
            Glide.with(binding.foto.context)
                .load(teacher.imageUrl)
                .into(binding.foto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind(teachers[position])
    }

    override fun getItemCount(): Int = teachers.size
}

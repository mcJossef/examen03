package com.munoz.jossef.examen03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.munoz.jossef.examen03.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TeacherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        fetchTeachers()
    }

    private fun fetchTeachers() {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getTeachers()
                val teachers = response.teachers
                Log.d("MainActivity", "Maestro encontrado: $teachers")
                adapter = TeacherAdapter(this@MainActivity, teachers, ::onItemClick, ::onItemLongClick)
                binding.recyclerView.adapter = adapter
            } catch (e: Exception) {
                Log.e("MainActivity", "Error al buscar profesoreser", e)
            }
        }
    }

    private fun onItemClick(teacher: Teacher) {
        Toast.makeText(this, "Llamando al profesor ${teacher.name}", Toast.LENGTH_SHORT).show()
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:${teacher.phone}")
        }
        startActivity(intent)
    }

    private fun onItemLongClick(teacher: Teacher) {
        Toast.makeText(this, "Mandando correo al profesor ${teacher.name}", Toast.LENGTH_SHORT).show()
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:${teacher.email}")
            putExtra(Intent.EXTRA_SUBJECT, "Contacto")
            putExtra(Intent.EXTRA_TEXT, "Hola ${teacher.name},")
        }
        startActivity(intent)
    }
}

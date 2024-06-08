package com.munoz.jossef.examen03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
                Log.d("MainActivity", "Teachers fetched: $teachers")
                adapter = TeacherAdapter(this@MainActivity, teachers, ::onItemClick, ::onItemLongClick)
                binding.recyclerView.adapter = adapter
            } catch (e: Exception) {
                Log.e("MainActivity", "Error fetching teachers", e)
            }
        }
    }

    private fun onItemClick(teacher: Teacher) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:${teacher.phone}")
        }
        startActivity(intent)
    }

    private fun onItemLongClick(teacher: Teacher) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:${teacher.email}")
            putExtra(Intent.EXTRA_SUBJECT, "Contacto")
            putExtra(Intent.EXTRA_TEXT, "Hola ${teacher.name},")
        }
        startActivity(intent)
    }
}

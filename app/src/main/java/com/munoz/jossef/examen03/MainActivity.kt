package com.munoz.jossef.examen03

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
                adapter = TeacherAdapter(teachers)
                binding.recyclerView.adapter = adapter
            } catch (e: Exception) {
                Log.e("MainActivity", "Error fetching teachers", e)
            }
        }
    }
}

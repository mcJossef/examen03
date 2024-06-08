package com.munoz.jossef.examen03

data class Teacher(
    val name: String,
    val last_name: String,
    val phone: String,
    val email: String,
    val imageUrl: String
)

data class TeacherResponse(
    val teachers: List<Teacher>
)

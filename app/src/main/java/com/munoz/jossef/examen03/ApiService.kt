package com.munoz.jossef.examen03

import retrofit2.http.GET

interface ApiService {
    @GET("list/teacher")
    suspend fun getTeachers(): TeacherResponse
}

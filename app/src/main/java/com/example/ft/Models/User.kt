package com.example.ft.Models

import java.time.LocalDate
import java.util.*

data class User(
    val surname:String,
    val name:String,
    val gender:String,
    val email:String,
    val login:String,
    val birthday: String,
    val password: String,
    val phone:String
)
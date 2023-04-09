package com.example.ft.Models

import java.util.UUID

data class Users (
    val id: UUID,
    val surname:String,
    val name:String,
    val gender:String,
    val email:String,
    val login:String,
    val birthday: String,
    val password: String,
    val phone:String
)
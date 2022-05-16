package com.example.githubapp.models.failiure

data class Error(
    val code: String,
    val `field`: String,
    val resource: String
)
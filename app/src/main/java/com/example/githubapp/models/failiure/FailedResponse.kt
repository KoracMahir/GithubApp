package com.example.githubapp.models.failiure

data class FailedResponse(
    val documentation_url: String,
    val errors: List<Error>,
    val message: String
)
package org.example.integration.models

import kotlinx.serialization.Serializable

@Serializable
data class NominalTinkoffResponse(
    val currency: String,
    val nano: Int,
    val units: String,
)

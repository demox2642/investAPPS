package org.example.integration.models

import kotlinx.serialization.Serializable

@Serializable
data class MinPriceIncrementTinkoffResponse(
    val nano: Int,
    val units: String,
)

package org.example.integration.models

import kotlinx.serialization.Serializable

@Serializable
data class Instruments<T : Any>(
    val instruments: List<T>,
)

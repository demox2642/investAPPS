package org.example.integration.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ServerCall(
    val instrumentStatus: String,
)

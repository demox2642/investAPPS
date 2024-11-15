package org.example.integration.dataBase.utils

import kotlinx.serialization.Serializable

@Serializable
data class ResponseError(
    val errorCode: Int,
    val errorMessage: String,
    val errorData: Map<String, String> = emptyMap(),
) : Exception(errorMessage)

package org.example.integration.utils

interface DoubleData {
    val nano: Long
    val units: String

    fun convert(): Double
}

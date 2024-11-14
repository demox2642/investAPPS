package org.example.integration.utils

fun DoubleData.convertToDouble(): Double =
    try {
        (this.units + "." + this.nano.toString()).toDouble()
    } catch (e: Exception) {
        println("DoubleData.convert() ERROR: $e convert is $this")
        0.0
    }

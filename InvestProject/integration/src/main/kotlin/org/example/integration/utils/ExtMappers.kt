package utils

fun DoubleData.convertToDouble(): Double  {
    return try {
        (this.units + "." + this.nano.toString()).toDouble()
    } catch (e: Exception) {
        println("DoubleData.convert() ERROR: $e convert is $this")
        0.0
    }
}

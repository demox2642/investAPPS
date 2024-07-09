package utils

import kotlinx.serialization.Serializable
import ru.demox_bank.utils.ResponseError

@Serializable
data class Response<T : Any>(
    val success: Boolean,
    val response: T? = null,
    val error: ResponseError? = null,
) {
    val isSuccess
        get() = success
}

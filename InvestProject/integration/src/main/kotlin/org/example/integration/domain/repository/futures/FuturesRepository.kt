package org.example.integration.domain.repository.futures

import org.example.integration.dataBase.futures.FuturesDTO
import org.example.integration.models.Instruments
import org.example.integration.models.futures.FuturesTinkoffResponse
import ru.demox_bank.utils.ResponseDB
import utils.Response

interface FuturesRepository {
    suspend fun getFromFunctionTinkoffServer(): Instruments<FuturesTinkoffResponse>

    suspend fun insertFutures(futuresDTO: FuturesDTO): ResponseDB

    suspend fun updateFutures(futuresDTO: FuturesDTO): ResponseDB

    suspend fun getFutures(uid: String): Response<FuturesDTO>

    suspend fun equalsFuturesDTO(futuresDTO: FuturesDTO)

    suspend fun getFuturesList(): Response<List<FuturesDTO>>
}

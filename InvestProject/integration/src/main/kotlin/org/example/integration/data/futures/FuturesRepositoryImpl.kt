package org.example.integration.data.futures

import org.example.integration.dataBase.futures.Futures
import org.example.integration.dataBase.futures.FuturesDTO
import org.example.integration.domain.repository.futures.FuturesRepository
import org.example.integration.models.Instruments
import org.example.integration.models.futures.FuturesTinkoffResponse
import ru.demox_bank.utils.ResponseDB
import utils.Response

class FuturesRepositoryImpl(
    private val futuresApi: FuturesApi,
) : FuturesRepository {
    override suspend fun getFromFunctionTinkoffServer(): Instruments<FuturesTinkoffResponse> = futuresApi.getFuturesFromTinkoffServer()

    override suspend fun insertFutures(futuresDTO: FuturesDTO): ResponseDB = Futures.insertFutures(futuresDTO)

    override suspend fun updateFutures(futuresDTO: FuturesDTO): ResponseDB = Futures.insertFutures(futuresDTO)

    override suspend fun getFutures(uid: String): Response<FuturesDTO> = Futures.getFutures(uid)

    override suspend fun equalsFuturesDTO(futuresDTO: FuturesDTO) = Futures.equalsFuturesDTO(futuresDTO)

    override suspend fun getFuturesList(): Response<List<FuturesDTO>> = Futures.getFuturesList()
}

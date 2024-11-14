package org.example.integration.domain.repository.realexchange

import org.example.integration.dataBase.realexchange.RealexchangeDTO
import org.example.integration.dataBase.realexchange.RealexchangeDTOResponse
import ru.demox_bank.utils.ResponseDB
import utils.Response

interface RealExchangeRepository {
    suspend fun insertRealExchange(realExchangeDTO: RealexchangeDTO): ResponseDB

    suspend fun getRealExchangeByValue(value: String): Response<RealexchangeDTOResponse>

    suspend fun getRealExchangeList(): Response<List<RealexchangeDTOResponse>>

    suspend fun insertRealExchangeList(list: List<String>)
}

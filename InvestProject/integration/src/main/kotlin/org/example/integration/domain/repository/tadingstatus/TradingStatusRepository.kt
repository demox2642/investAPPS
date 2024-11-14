package org.example.integration.domain.repository.tadingstatus

import org.example.integration.dataBase.tadingstatus.TradingStatusDTO
import org.example.integration.dataBase.tadingstatus.TradingStatusDTOResponse
import ru.demox_bank.utils.ResponseDB
import utils.Response

interface TradingStatusRepository {
    suspend fun insertTradingStatus(tradingStatusDTO: TradingStatusDTO): ResponseDB

    suspend fun getTradingStatusByValue(value: String): Response<TradingStatusDTOResponse>

    suspend fun getTradingStatusList(): Response<List<TradingStatusDTOResponse>>

    suspend fun insertTradingStatusList(list: List<String>)
}

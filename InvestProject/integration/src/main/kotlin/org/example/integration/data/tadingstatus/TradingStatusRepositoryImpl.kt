package org.example.integration.data.tadingstatus

import org.example.integration.dataBase.error_log.ErrorLog
import org.example.integration.dataBase.tadingstatus.TradingStatus
import org.example.integration.dataBase.tadingstatus.TradingStatusDTO
import org.example.integration.dataBase.tadingstatus.TradingStatusDTOResponse
import org.example.integration.dataBase.update_log.UpdateLog
import org.example.integration.domain.repository.tadingstatus.TradingStatusRepository
import ru.demox_bank.utils.ResponseDB
import utils.Response

class TradingStatusRepositoryImpl : TradingStatusRepository {
    override suspend fun insertTradingStatus(tradingStatusDTO: TradingStatusDTO): ResponseDB =
        TradingStatus.insertTradingStatus(tradingStatusDTO)

    override suspend fun getTradingStatusByValue(value: String): Response<TradingStatusDTOResponse> =
        TradingStatus.getTradingStatusByValue(value)

    override suspend fun getTradingStatusList(): Response<List<TradingStatusDTOResponse>> = TradingStatus.getTradingStatusList()

    override suspend fun insertTradingStatusList(list: List<String>) {
        val tradingStatusResponse = list.toSet()
        val tradingStatusDB = TradingStatus.getTradingStatusList()

        if (tradingStatusDB.isSuccess) {
            val tradingStatusDBList = tradingStatusDB.response?.map { it.name }
            if (tradingStatusDBList!!.containsAll(tradingStatusResponse)) {
                println("UPDATE TradingStatus - no new value")
            } else {
                tradingStatusResponse.forEach { tradingStatus ->
                    if (tradingStatusDBList.contains(tradingStatus).not()) {
                        val insertesult =
                            TradingStatus.insertTradingStatus(
                                tradingStatusDTO = TradingStatusDTO(name = tradingStatus),
                            )
                        println(insertesult)
                    }
                }
            }

            UpdateLog.insertUpdateLog("TradingStatusUpdate COMPLETE")
        } else {
            ErrorLog.insertErrorLog(errorLog = "TradingStatusUpdate ERROR: ${tradingStatusDB.error}")
        }
    }
}

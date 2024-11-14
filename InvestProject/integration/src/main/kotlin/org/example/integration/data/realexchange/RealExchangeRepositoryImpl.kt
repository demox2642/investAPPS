package org.example.integration.data.realexchange

import org.example.integration.dataBase.error_log.ErrorLog
import org.example.integration.dataBase.realexchange.Realexchange
import org.example.integration.dataBase.realexchange.RealexchangeDTO
import org.example.integration.dataBase.realexchange.RealexchangeDTOResponse
import org.example.integration.domain.repository.realexchange.RealExchangeRepository
import ru.demox_bank.utils.ResponseDB
import utils.Response

class RealExchangeRepositoryImpl : RealExchangeRepository {
    override suspend fun insertRealExchange(realExchangeDTO: RealexchangeDTO): ResponseDB = Realexchange.insertRealexchange(realExchangeDTO)

    override suspend fun getRealExchangeByValue(value: String): Response<RealexchangeDTOResponse> =
        Realexchange.getRealexchangeByValue(value)

    override suspend fun getRealExchangeList(): Response<List<RealexchangeDTOResponse>> = Realexchange.getRealexchangeList()

    override suspend fun insertRealExchangeList(list: List<String>) {
        val realExchangeResponse = list.toSet()
        val realExchangeDB = Realexchange.getRealexchangeList()
        if (realExchangeDB.isSuccess) {
            val realExchangeDBList = realExchangeDB.response?.map { it.realexchangeValue }

            if (realExchangeDBList!!.containsAll(realExchangeResponse)) {
                println("UPDATE RealExchange- no new value")
            } else {
                realExchangeResponse.forEach { realExchange ->
                    if (realExchangeDBList.contains(realExchange).not()) {
                        val insertResult =
                            Realexchange.insertRealexchange(
                                realexchangeDTO = RealexchangeDTO(realexchangeValue = realExchange),
                            )
                        println(insertResult)
                    }
                }
            }
            "RealExchangeUpdate COMPLETE"
        } else {
            ErrorLog.insertErrorLog(errorLog = "RealExchangeUpdate ERROR: ${realExchangeDB.error}")
        }
    }
}

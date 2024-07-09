package org.example.integration.domain.repository.currency

import org.example.integration.dataBase.currency.CurrencyDTO
import org.example.integration.models.Instruments
import org.example.integration.models.currency.CurrencyTinkoffResponse
import ru.demox_bank.utils.ResponseDB
import utils.Response

interface CurrencyRepository {
    suspend fun getCurrencyFromTinkoffServer(): Instruments<CurrencyTinkoffResponse>

    suspend fun insertCurrency(currencyDTO: CurrencyDTO): ResponseDB

    suspend fun updateCurrency(currencyDTO: CurrencyDTO): ResponseDB

    suspend fun getCurrency(uid: String): Response<CurrencyDTO>

    suspend fun equalsCurrencyDTO(currencyDTO: CurrencyDTO)

    suspend fun getCurrencyList(): Response<List<CurrencyDTO>>
}

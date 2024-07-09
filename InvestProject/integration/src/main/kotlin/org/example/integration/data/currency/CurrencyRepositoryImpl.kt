package org.example.integration.data.currency

import org.example.integration.dataBase.currency.Currency
import org.example.integration.dataBase.currency.CurrencyDTO
import org.example.integration.domain.repository.currency.CurrencyRepository
import org.example.integration.models.Instruments
import org.example.integration.models.currency.CurrencyTinkoffResponse
import ru.demox_bank.utils.ResponseDB
import utils.Response

class CurrencyRepositoryImpl(
    private val currencyApi: CurrencyApi,
) : CurrencyRepository {
    override suspend fun getCurrencyFromTinkoffServer(): Instruments<CurrencyTinkoffResponse> = currencyApi.getCurrencyFromTinkoffServer()

    override suspend fun insertCurrency(currencyDTO: CurrencyDTO): ResponseDB = Currency.insertCurrency(currencyDTO)

    override suspend fun updateCurrency(currencyDTO: CurrencyDTO): ResponseDB = Currency.updateCurrency(currencyDTO)

    override suspend fun getCurrency(uid: String): Response<CurrencyDTO> = Currency.getCurrency(uid)

    override suspend fun equalsCurrencyDTO(currencyDTO: CurrencyDTO) = Currency.equalsCurrencyDTO(currencyDTO)

    override suspend fun getCurrencyList(): Response<List<CurrencyDTO>> = Currency.getCurrencyList()
}

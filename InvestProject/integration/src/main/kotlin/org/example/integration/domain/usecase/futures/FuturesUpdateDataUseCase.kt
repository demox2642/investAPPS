package org.example.integration.domain.usecase.futures

import org.example.integration.dataBase.brand.Brand
import org.example.integration.dataBase.currency.Currency
import org.example.integration.dataBase.error_log.ErrorLog
import org.example.integration.dataBase.futures.FuturesDTO
import org.example.integration.dataBase.realexchange.Realexchange
import org.example.integration.dataBase.tadingstatus.TradingStatus
import org.example.integration.domain.repository.brand.BrandRepository
import org.example.integration.domain.repository.futures.FuturesRepository
import org.example.integration.domain.repository.realexchange.RealExchangeRepository
import org.example.integration.domain.repository.tadingstatus.TradingStatusRepository
import org.example.integration.models.futures.toFuturesDTO
import org.example.integration.models.toBrandDTO
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync

class FuturesUpdateDataUseCase(
    private val brandRepository: BrandRepository,
    private val realExchangeRepository: RealExchangeRepository,
    private val tradingStatusRepository: TradingStatusRepository,
    private val futuresRepository: FuturesRepository,
) {
    suspend fun updateFuturesData() {
        val serverResponse = futuresRepository.getFromFunctionTinkoffServer()

        realExchangeRepository.insertRealExchangeList(serverResponse.instruments.map { it.realExchange })

        suspendedTransactionAsync {
            tradingStatusRepository.insertTradingStatusList(serverResponse.instruments.map { it.tradingStatus })
        }.await()

        suspendedTransactionAsync {
            brandRepository.updateBrandList(serverResponse.instruments.map { it.brand.toBrandDTO() })
        }.await()

        val futuresResponse = mutableSetOf<FuturesDTO>()

        serverResponse.instruments.map { FuturesTinkoff ->
            val brandId = Brand.getBrandByLogoName(FuturesTinkoff.brand.logoName).response?.id
            val realExchange = Realexchange.getRealexchangeByValue(FuturesTinkoff.realExchange).response?.id
            val tradingStatus = TradingStatus.getTradingStatusByValue(FuturesTinkoff.tradingStatus).response?.id
            val currencyUid = Currency.getCurrencyName(FuturesTinkoff.currency).response

            if (brandId != null && realExchange != null && tradingStatus != null && currencyUid != null) {
                futuresResponse.add(
                    FuturesTinkoff.toFuturesDTO(
                        currency = currencyUid.uid,
                        brand_id = brandId,
                        tradingStatusId = tradingStatus,
                        realExchangeId = realExchange,
                    ),
                )
            } else {
                ErrorLog.insertErrorLog(
                    errorLog = "ERROR IMPORT FuturesTinkoff KEY VALUE IS EMPTY. Data: brandId=$brandId realExchange=$realExchange tradingStatus=$tradingStatus  currency_uid=$currencyUid",
                )
            }
        }

        futuresResponse.forEach {
            it.let { Futures ->
                futuresRepository.equalsFuturesDTO(Futures)
            }
        }
    }
}

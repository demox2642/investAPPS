package org.example.integration.domain.usecase.shares

import org.example.integration.dataBase.brand.Brand
import org.example.integration.dataBase.currency.Currency
import org.example.integration.dataBase.error_log.ErrorLog
import org.example.integration.dataBase.realexchange.Realexchange
import org.example.integration.dataBase.shares.SharesDTO
import org.example.integration.dataBase.tadingstatus.TradingStatus
import org.example.integration.domain.repository.brand.BrandRepository
import org.example.integration.domain.repository.realexchange.RealExchangeRepository
import org.example.integration.domain.repository.shares.SharesRepository
import org.example.integration.domain.repository.tadingstatus.TradingStatusRepository
import org.example.integration.models.shares.sharesTinkoffResponseToSharesDTO
import org.example.integration.models.toBrandDTO
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync

class SharesUpdateDataUseCase(
    private val brandRepository: BrandRepository,
    private val realExchangeRepository: RealExchangeRepository,
    private val tradingStatusRepository: TradingStatusRepository,
    private val sharesRepository: SharesRepository,
) {
    suspend fun updateSharesData() {
        val serverResponse = sharesRepository.getSharesFromTinkoffServer()

        realExchangeRepository.insertRealExchangeList(serverResponse.instruments.map { it.realExchange })

        suspendedTransactionAsync {
            tradingStatusRepository.insertTradingStatusList(serverResponse.instruments.map { it.tradingStatus })
        }.await()

        suspendedTransactionAsync {
            brandRepository.updateBrandList(serverResponse.instruments.map { it.brand.toBrandDTO() })
        }.await()

        val sharesResponse = mutableSetOf<SharesDTO>()

        serverResponse.instruments.map { SharesTinkoff ->
            val brandId = Brand.getBrandByLogoName(SharesTinkoff.brand.logoName).response?.id
            val realExchange = Realexchange.getRealexchangeByValue(SharesTinkoff.realExchange).response?.id
            val tradingStatus = TradingStatus.getTradingStatusByValue(SharesTinkoff.tradingStatus).response?.id
            val currencyUid = Currency.getCurrencyName(SharesTinkoff.currency).response

            if (brandId != null && realExchange != null && tradingStatus != null && currencyUid != null) {
                sharesResponse.add(
                    SharesTinkoff.sharesTinkoffResponseToSharesDTO(
                        brand_id = brandId,
                        tradingStatusId = tradingStatus,
                        realExchangeId = realExchange,
                    ),
                )
            } else {
                ErrorLog.insertErrorLog(
                    errorLog =
                        "ERROR IMPORT SharesTinkoff KEY VALUE IS EMPTY. Data: brandId=$brandId " +
                            "realExchange=$realExchange tradingStatus=$tradingStatus  currency_uid=$currencyUid",
                )
            }
        }

        sharesResponse.forEach {
            it.let { shares ->
                sharesRepository.equalsSharesDTO(shares)
            }
        }
    }
}

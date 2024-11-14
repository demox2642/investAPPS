package org.example.integration.domain.usecase.etf

import org.example.integration.dataBase.brand.Brand
import org.example.integration.dataBase.etf.EtfDTO
import org.example.integration.dataBase.realexchange.Realexchange
import org.example.integration.dataBase.tadingstatus.TradingStatus
import org.example.integration.domain.repository.brand.BrandRepository
import org.example.integration.domain.repository.etf.EtfRepository
import org.example.integration.domain.repository.realexchange.RealExchangeRepository
import org.example.integration.domain.repository.tadingstatus.TradingStatusRepository
import org.example.integration.models.etf.toEtfDTO
import org.example.integration.models.toBrandDTO
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync

class EtfUpdateDataUseCase(
    private val brandRepository: BrandRepository,
    private val realExchangeRepository: RealExchangeRepository,
    private val tradingStatusRepository: TradingStatusRepository,
    private val etfRepository: EtfRepository,
) {
    suspend fun updateEtfData() {
        val serverResponse = etfRepository.getEtfFromTinkoffServer()

        realExchangeRepository.insertRealExchangeList(serverResponse.instruments.map { it.realExchange })

        suspendedTransactionAsync {
            tradingStatusRepository.insertTradingStatusList(serverResponse.instruments.map { it.tradingStatus })
        }.await()

        suspendedTransactionAsync {
            brandRepository.updateBrandList(serverResponse.instruments.map { it.brand.toBrandDTO() })
        }.await()

        val etfResponse = mutableSetOf<EtfDTO>()

        serverResponse.instruments.map { etfTinkoff ->
            val brandId = Brand.getBrandByLogoName(etfTinkoff.brand.logoName).response?.id
            val realExchange = Realexchange.getRealexchangeByValue(etfTinkoff.realExchange).response?.id
            val tradingStatus = TradingStatus.getTradingStatusByValue(etfTinkoff.tradingStatus).response?.id
            if (brandId != null && realExchange != null && tradingStatus != null) {
                etfResponse.add(
                    etfTinkoff.toEtfDTO(
                        brand_id = brandId,
                        tradingStatusId = tradingStatus,
                        realExchangeId = realExchange,
                    ),
                )
            }
        }

        etfResponse.forEach {
            it.let { etf ->
                etfRepository.equalsEtfDTO(etf)
            }
        }
    }
}

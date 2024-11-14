package org.example.integration.domain.usecase.currency

import org.example.integration.dataBase.brand.Brand
import org.example.integration.domain.repository.brand.BrandRepository
import org.example.integration.domain.repository.currency.CurrencyRepository
import org.example.integration.domain.repository.realexchange.RealExchangeRepository
import org.example.integration.domain.repository.tadingstatus.TradingStatusRepository
import org.example.integration.models.currency.toCurrencyDTO
import org.example.integration.models.toBrandDTO

class CurrencyUpdateDataUseCase(
    private val brandRepository: BrandRepository,
    private val realExchangeRepository: RealExchangeRepository,
    private val tradingStatusRepository: TradingStatusRepository,
    private val currencyRepository: CurrencyRepository,
) {
    suspend fun updateCurrencyData() {
        val serverResponse = currencyRepository.getCurrencyFromTinkoffServer()

        realExchangeRepository.insertRealExchangeList(serverResponse.instruments.map { it.realExchange })

        tradingStatusRepository.insertTradingStatusList(serverResponse.instruments.map { it.tradingStatus })

        brandRepository.updateBrandList(serverResponse.instruments.map { it.brand.toBrandDTO() })

        val currencyResponse =
            serverResponse.instruments
                .map { currencyTinkoff ->
                    Brand.getBrandByLogoName(currencyTinkoff.brand.logoName).response?.id?.let {
                        currencyTinkoff.toCurrencyDTO(
                            it,
                        )
                    }
                }.toSet()

        currencyResponse.forEach {
            it?.let { currency ->
                currencyRepository.equalsCurrencyDTO(currency)
            }
        }
    }
}

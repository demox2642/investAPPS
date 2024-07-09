package org.example.integration

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.example.integration.data.brand.BrandRepositoryImpl
import org.example.integration.data.currency.CurrencyApi
import org.example.integration.data.currency.CurrencyRepositoryImpl
import org.example.integration.data.etf.EtfApi
import org.example.integration.data.etf.EtfRepositoryImpl
import org.example.integration.data.futures.FuturesApi
import org.example.integration.data.futures.FuturesRepositoryImpl
import org.example.integration.data.realexchange.RealExchangeRepositoryImpl
import org.example.integration.data.tadingstatus.TradingStatusRepositoryImpl
import org.example.integration.domain.usecase.currency.CurrencyUpdateDataUseCase
import org.example.integration.domain.usecase.etf.EtfUpdateDataUseCase
import org.example.integration.domain.usecase.futures.FuturesUpdateDataUseCase
import org.jetbrains.exposed.sql.Database
import java.util.Calendar

val calendar = Calendar.getInstance()

fun main() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/demox_invest",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "postgres",
    )

    runBlocking {
//        val brandRepository = BrandRepositoryImpl()
//        val realExchangeRepository = RealExchangeRepositoryImpl()
//        val tradingStatusRepository = TradingStatusRepositoryImpl()
//        val currencyApi = CurrencyApi()
//        val currencyRepository = CurrencyRepositoryImpl(currencyApi)
//        val etfApi = EtfApi()
//        val etfRepository = EtfRepositoryImpl(etfApi)
//        val futuresApi = FuturesApi()
//        val futuresRepository = FuturesRepositoryImpl(futuresApi)

//        CurrencyUpdateDataUseCase(
//            brandRepository = brandRepository,
//            realExchangeRepository = realExchangeRepository,
//            tradingStatusRepository = tradingStatusRepository,
//            currencyRepository = currencyRepository,
//        ).updateCurrencyData()
//
//        EtfUpdateDataUseCase(
//            brandRepository = brandRepository,
//            realExchangeRepository = realExchangeRepository,
//            tradingStatusRepository = tradingStatusRepository,
//            etfRepository = etfRepository,
//        ).updateEtfData()
//
//        FuturesUpdateDataUseCase(
//            brandRepository = brandRepository,
//            realExchangeRepository = realExchangeRepository,
//            tradingStatusRepository = tradingStatusRepository,
//            futuresRepository = futuresRepository,
//        ).updateFuturesData()
        startUpdate()
    }
}

private suspend fun startUpdate() {
    val brandRepository = BrandRepositoryImpl()
    val realExchangeRepository = RealExchangeRepositoryImpl()
    val tradingStatusRepository = TradingStatusRepositoryImpl()
    val currencyApi = CurrencyApi()
    val currencyRepository = CurrencyRepositoryImpl(currencyApi)
    val etfApi = EtfApi()
    val etfRepository = EtfRepositoryImpl(etfApi)
    val futuresApi = FuturesApi()
    val futuresRepository = FuturesRepositoryImpl(futuresApi)

    CurrencyUpdateDataUseCase(
        brandRepository = brandRepository,
        realExchangeRepository = realExchangeRepository,
        tradingStatusRepository = tradingStatusRepository,
        currencyRepository = currencyRepository,
    ).updateCurrencyData()

    EtfUpdateDataUseCase(
        brandRepository = brandRepository,
        realExchangeRepository = realExchangeRepository,
        tradingStatusRepository = tradingStatusRepository,
        etfRepository = etfRepository,
    ).updateEtfData()

    FuturesUpdateDataUseCase(
        brandRepository = brandRepository,
        realExchangeRepository = realExchangeRepository,
        tradingStatusRepository = tradingStatusRepository,
        futuresRepository = futuresRepository,
    ).updateFuturesData()

    println("update Fin ${calendar.time}")
    timeOut()
}

private suspend fun timeOut() {
    delay(50000)
    println("timeOut Fin ${calendar.time}")
    startUpdate()
}

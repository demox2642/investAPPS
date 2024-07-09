package org.example.integration.data.futures

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.example.integration.data.models.ServerCall
import org.example.integration.domain.repository.HttpClient
import org.example.integration.models.Instruments
import org.example.integration.models.futures.FuturesTinkoffResponse

class FuturesApi {
    suspend fun getFuturesFromTinkoffServer(): Instruments<FuturesTinkoffResponse> {
        val response: Instruments<FuturesTinkoffResponse> =
            HttpClient.client
                .post(
                    "https://sandbox-invest-public-api.tinkoff.ru/rest/tinkoff.public.invest.api.contract.v1.InstrumentsService/Futures",
                ) {
                    contentType(ContentType.Application.Json)
                    setBody(ServerCall("INSTRUMENT_STATUS_ALL"))
                }.body()
        return response
    }
}

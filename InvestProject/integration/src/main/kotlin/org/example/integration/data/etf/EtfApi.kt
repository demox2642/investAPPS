@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.example.integration.data.etf

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.example.integration.data.models.ServerCall
import org.example.integration.domain.repository.HttpClient
import org.example.integration.models.Instruments
import org.example.integration.models.etf.EtfTinkoffResponse

class EtfApi {
    suspend fun getEtfFromTinkoffServer(): Instruments<EtfTinkoffResponse> {
        val response: Instruments<EtfTinkoffResponse> =
            HttpClient.client
                .post(
                    "https://sandbox-invest-public-api.tinkoff.ru/rest/tinkoff.public.invest.api.contract.v1.InstrumentsService/Etfs",
                ) {
                    contentType(ContentType.Application.Json)
                    setBody(ServerCall("INSTRUMENT_STATUS_ALL"))
                }.body()
        return response
    }
}

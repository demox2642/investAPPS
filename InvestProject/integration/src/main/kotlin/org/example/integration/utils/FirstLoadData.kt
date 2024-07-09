package utils

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class FirstLoadData(
    private val httpClient: HttpClient,
) {
    suspend fun getCurrency(): String {
        val string =
            httpClient
                .get(
                    "https://sandbox-invest-public-api.tinkoff.ru/rest/tinkoff.public.invest.api.contract.v1.InstrumentsService/Currencies",
                ) {
                    header("Content-Type", "application/json")
                    // header("Authorization", "Bearer t.KJ5zavb2mWx-xFU9fwEPMnwr6czSkQNE4V4845E5RWSxbnFUQU8bTf9Fek7ibbZu07HeuBxhuSIMGuWIfYg-Tw")
                }.bodyAsText()
        println("response string: $string")
        //  val projectCollection = Json.decodeFromString<List<Valute>>(string)
        //  println("response projectCollection: $projectCollection")
        // val t = XML.decodeFromString(HelloWorld.serializer(), "<HelloWorld user='You!' />")
        // println("response t: $t")
        return string
    }
}

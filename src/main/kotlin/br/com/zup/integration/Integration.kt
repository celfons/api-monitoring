package br.com.zup.integration

import br.com.zup.model.StatusCode
import br.com.zup.model.Service as ServiceModel
import br.com.zup.model.Token
import br.com.zup.repository.StatusMongoRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import khttp.responses.Response
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import khttp.post as POST
import khttp.get as GET

@Service
class Integration(
        private var statusMongoRepository: StatusMongoRepository
) {

    fun getToken(): Token {

        val response: Response = POST(
                url = "https://wsdesenv.hdi.com.br/rest/HDIOAuth/v2/token",
                params = mapOf(
                        Pair("grant_type","client_credentials"),
                        Pair("client_id","887133a1-31ed-498a-885e-a34077b2fa0a"),
                        Pair("client_secret","59ef5595-0231-40a0-a1fc-48245cdcac65")
                )
        )

        statusMongoRepository.save(StatusCode(response.statusCode, "token", ZonedDateTime.now().toString()))

        return jacksonObjectMapper().readValue(response.text)
    }

    fun integrationService(service: ServiceModel, token: Token){

        val response = when {
            service.method == ServiceModel.Method.GET -> GET(
                    url = service.url,
                    headers = mapOf(Pair("Content-Type", "application/json")).plus(service.headers!!),
                    params = mapOf(Pair("access_token", token.access_token!!)).plus(service.queryParam!!),
                    json = service.data
            )
            else -> POST(
                    url = service.url,
                    headers = mapOf(Pair("Content-Type", "application/json")).plus(service.headers!!),
                    params = mapOf(Pair("access_token", token.access_token!!)).plus(service.queryParam!!),
                    json = service.data
            )
        }

        statusMongoRepository.save(StatusCode(response.statusCode, service.name, ZonedDateTime.now().toString()))

    }

}


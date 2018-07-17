package br.com.zup.integration

import br.com.zup.model.StatusCode
import br.com.zup.model.Service as ServiceModel
import br.com.zup.model.Token
import br.com.zup.repository.StatusMongoRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import khttp.responses.Response
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import khttp.post as POST
import khttp.get as GET

@Service
class Integration(
        private var statusMongoRepository: StatusMongoRepository
) {

    private fun getToken(): Token {

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

    private fun getLogin(access_token: String) {

        val response: Response = POST(
                url = "https://wsdesenv.hdi.com.br/rest/WebAppExecutivo.hdi/v1/login",
                headers = mapOf(Pair("Content-Type", "application/json")),
                params = mapOf(
                        Pair("access_token", access_token)
                ),
                json = JSONObject("""
                    {
                        "usuario": "marcelr",
                        "senha": "dSRiejY0TSt4"
                    }
                """)
        )

        statusMongoRepository.save(StatusCode(response.statusCode, "login", ZonedDateTime.now().toString()))

    }

    fun integrationService(service: ServiceModel){

        val token = getToken()

        getLogin(token.access_token!!)

        val response = if(service.method == ServiceModel.Method.GET){

            GET(
                    url = service.url,
                    headers = mapOf(Pair("Content-Type", "application/json")),
                    params = mapOf(
                            Pair("access_token", token.access_token!!)
                    )
            )

        }

        else {

            POST(
                    url = service.url,
                    headers = mapOf(Pair("Content-Type", "application/json")),
                    params = mapOf(
                            Pair("access_token", token.access_token!!)
                    ),
                    json = service.data
            )

        }

        statusMongoRepository.save(StatusCode(response.statusCode, service.name, ZonedDateTime.now().toString()))

    }

}


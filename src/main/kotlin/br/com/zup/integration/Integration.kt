package br.com.zup.integration

import br.com.zup.model.LoginResponse
import br.com.zup.model.Token
import br.com.zup.model.LoginRequest
import br.com.zup.model.Profile
import br.com.zup.model.StatusCode
import br.com.zup.repository.StatusMongoRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import khttp.responses.Response
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import khttp.post as POST
import khttp.get as GET

@Service
class Integration(
        private var statusMongoRepository: StatusMongoRepository
) {

    @Value("\${tokenUrl}")
    private lateinit var tokenUrl: String

    @Value("\${loginUrl}")
    private lateinit var loginUrl: String

    @Value("\${profileUrl}")
    private lateinit var profileUrl: String

    fun getToken(): Token {

        val response: Response = POST(
                url = tokenUrl,
                params = mapOf(
                    Pair("grant_type","client_credentials"),
                    Pair("client_id","887133a1-31ed-498a-885e-a34077b2fa0a"),
                    Pair("client_secret","59ef5595-0231-40a0-a1fc-48245cdcac65")
                )
        )

        statusMongoRepository.save(StatusCode(response.statusCode, "token", ZonedDateTime.now().toString()))

        return jacksonObjectMapper().readValue(response.text)

    }

    fun getLogin(loginRequest: LoginRequest, tokenRequest: String? = null): LoginResponse {

        val token = if(tokenRequest.isNullOrEmpty()) {
             getToken().access_token
        }
        else{
            tokenRequest
        }

        val response: Response = POST(
                url = loginUrl,
                headers = mapOf(Pair("Content-Type", "application/json")),
                params = mapOf(
                        Pair("access_token", token!!)
                ),
                json = JSONObject(loginRequest)
        )

        statusMongoRepository.save(StatusCode(response.statusCode, "login", ZonedDateTime.now().toString()))

        return jacksonObjectMapper().readValue(response.text)
    }

    fun getProfile(loginRequest: LoginRequest): Profile {

        val token = getToken()

        getLogin(loginRequest, token.access_token)

        val response: Response = GET(
                url = profileUrl,
                params = mapOf(
                        Pair("access_token", token.access_token!!)
                )
        )

        statusMongoRepository.save(StatusCode(response.statusCode, "profile", ZonedDateTime.now().toString()))

        return jacksonObjectMapper().readValue(response.text)

    }

}

